package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Block;
import com.insightfullogic.lambdabehave.Description;
import com.insightfullogic.lambdabehave.SpecificationDeclarationException;
import com.insightfullogic.lambdabehave.generators.GeneratedDescription;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import com.insightfullogic.lambdabehave.impl.generators.GeneratedDescriptionBuilder;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.specifications.PairBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.TripletBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.ValueBuilder;
import com.insightfullogic.lambdabehave.specifications.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

/**
 * A Specifier defines how .
 */
public class Specifier implements Description {

    private final String suiteName;

    private final Blocks initializers;
    private final Blocks prefixes;
    private final List<Behaviour> behaviours;
    private final Blocks postfixes;
    private final Blocks completers;
    private final SourceGenerator sourceGenerator;

    public Specifier(String suite, SourceGenerator sourceGenerator) {
        this.suiteName = suite;
        this.sourceGenerator = sourceGenerator;

        initializers = new Blocks();
        prefixes = new Blocks();
        behaviours = new ArrayList<>();
        postfixes = new Blocks();
        completers = new Blocks();
    }

    public <T> void specifyBehaviour(String description, T value, ColumnDataSpecification<T> specification) {
        should(description, expect -> specification.specifyBehaviour(expect, value));
    }

    public <F, S> void specifyBehaviour(String description, F first, S second, TwoColumnDataSpecification<F, S> specification) {
        should(description, expect -> specification.specifyBehaviour(expect, first, second));
    }

    public <F, S, T> void specifyBehaviour(String description, F first, S second, T third, ThreeColumnDataSpecification<F, S, T> specification) {
        should(description, expect -> specification.specifyBehaviour(expect, first, second, third));
    }

    @Override
    public void should(String description, Specification specification) {
        Objects.nonNull(description);
        Objects.nonNull(specification);

        if (behaviours.removeIf(behaviour -> behaviour.hasDescription(description))) {
            behaviours.add(new Behaviour(description, expect -> {

                throw new SpecificationDeclarationException(
                        "You can't declare multiple specifications with the same name. Name: '" + description + "'");

            }));
        } else {
            behaviours.add(new Behaviour(description, specification));
        }
    }

    @Override
    public <T> Column<T> uses(T value) {
        return new ValueBuilder<>(value, this);
    }

    @Override
    public <T> Column<T> uses(List<T> values) {
        // Additional arraylist required to ensure
        // we can with more values
        return new ValueBuilder<>(new ArrayList<>(values), this);
    }

    @Override
    public <T> Column<T> uses(Stream<T> values) {
        return uses(values.collect(toList()));
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(F first, S second) {
        return new PairBuilder<>(first, second, this);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(List<F> first, List<S> second) {
        return new PairBuilder<F, S>(new ArrayList<>(first), new ArrayList<>(second), this);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(Stream<F> first, Stream<S> second) {
        return new PairBuilder<F, S>(first.collect(toList()), second.collect(toList()), this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(F first, S second, T third) {
        return new TripletBuilder<>(first, second, third, this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(List<F> first, List<S> second, List<T> third) {
        return new TripletBuilder<F, S, T>(new ArrayList<>(first),
                                           new ArrayList<>(second),
                                           new ArrayList<>(third),
                                           this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(Stream<F> first, Stream<S> second, Stream<T> third) {
        return new TripletBuilder<F, S, T>(first.collect(toList()),
                                           second.collect(toList()),
                                           third.collect(toList()),
                                           this);
    }

    @Override
    public GeneratedDescription requires(int exampleCount) {
        return new GeneratedDescriptionBuilder(sourceGenerator, exampleCount, this);
    }

    @Override
    public void shouldSetup(Block block) {
        prefixes.add(block);
    }

    @Override
    public void shouldInitialize(Block block) {
        initializers.add(block);
    }

    @Override
    public void shouldTearDown(Block block) {
        postfixes.add(block);
    }

    @Override
    public void shouldComplete(Block block) {
        completers.add(block);
    }

    public void checkSpecifications(Report report) {
        report.onSuiteName(suiteName);
        completeBehaviours().forEach(behaviour -> report.recordSpecification(suiteName, behaviour.checkCompleteBehaviour()));
    }

    public Stream<CompleteBehaviour> completeBehaviours() {
        if (behaviours.isEmpty())
            return Stream.empty();

        return concat(
                concat(initializers.completeFixtures("initializer"),
                       completeSpecifications()),
                       completers.completeFixtures("completer"));
    }

    private Stream<CompleteBehaviour> completeSpecifications() {
        return behaviours.stream()
                         .map(behaviour -> new CompleteSpecification(prefixes, behaviour, postfixes, suiteName));
    }

    public String getSuiteName() {
        return suiteName;
    }

}
