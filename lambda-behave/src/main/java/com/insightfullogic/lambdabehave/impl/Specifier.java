package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Block;
import com.insightfullogic.lambdabehave.Description;
import com.insightfullogic.lambdabehave.SpecificationDeclarationException;
import com.insightfullogic.lambdabehave.generators.GeneratedDescription;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import com.insightfullogic.lambdabehave.impl.generators.GeneratedDescriptionBuilder;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.specifications.PairBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.TitledTable;
import com.insightfullogic.lambdabehave.impl.specifications.TripletBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.ValueBuilder;
import com.insightfullogic.lambdabehave.specifications.*;
import org.mockito.Mockito;
import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
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

    public Specifier(final String suite, final SourceGenerator sourceGenerator) {
        this.suiteName = suite;
        this.sourceGenerator = sourceGenerator;

        initializers = new Blocks(suiteName);
        prefixes = new Blocks(suiteName);
        behaviours = new ArrayList<>();
        postfixes = new Blocks(suiteName);
        completers = new Blocks(suiteName);
    }

    public <T> void specifyBehaviour(final String description, final T value, final ColumnDataSpecification<T> specification) {
        should(description, expect -> specification.specifyBehaviour(expect, value));
    }

    public <F, S> void specifyBehaviour(final String description, final F first, final S second, final TwoColumnDataSpecification<F, S> specification) {
        should(description, expect -> specification.specifyBehaviour(expect, first, second));
    }

    public <F, S, T> void specifyBehaviour(final String description, final F first, final S second, final T third, final ThreeColumnDataSpecification<F, S, T> specification) {
        should(description, expect -> specification.specifyBehaviour(expect, first, second, third));
    }

    @Override
    public void should(final String description, final Specification specification) {
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
    public <T> Column<T> uses(final T value) {
        return new ValueBuilder<>(value, this);
    }

    @Override
    public <T> Column<T> uses(final List<T> values) {
        // Additional arraylist required to ensure
        // we can with more values
        return new ValueBuilder<>(new ArrayList<>(values), this);
    }

    @Override
    public <T> Column<T> uses(final Stream<T> values) {
        return uses(values.collect(toList()));
    }

    public <T, F, S> TitledTable<T, F, S> usesTable(final Class<T> clazz, final Function<T, F> first, final Function<T, S> second) {
        List<Method> m = new ArrayList<>();
        final T mock = (T) Enhancer.create(clazz, (MethodInterceptor) (o, method, objects, methodProxy) -> {
            m.add(method);
            return null;
        });
        first.apply(mock);
        second.apply(mock);
        return new TitledTable<>(m, this, clazz);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(final F first, final S second) {
        return new PairBuilder<>(first, second, this);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(final List<F> first, final List<S> second) {
        return new PairBuilder<F, S>(new ArrayList<>(first), new ArrayList<>(second), this);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(final Stream<F> first, final Stream<S> second) {
        return new PairBuilder<F, S>(first.collect(toList()), second.collect(toList()), this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(final F first, final S second, final T third) {
        return new TripletBuilder<>(first, second, third, this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(final List<F> first, final List<S> second, final List<T> third) {
        return new TripletBuilder<F, S, T>(new ArrayList<>(first),
                                           new ArrayList<>(second),
                                           new ArrayList<>(third),
                                           this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(final Stream<F> first, final Stream<S> second, final Stream<T> third) {
        return new TripletBuilder<F, S, T>(first.collect(toList()),
                                           second.collect(toList()),
                                           third.collect(toList()),
                                           this);
    }

    @Override
    public GeneratedDescription requires(final int exampleCount) {
        return new GeneratedDescriptionBuilder(sourceGenerator, exampleCount, this);
    }

    @Override
    public void isSetupWith(final Block block) {
        prefixes.add(block);
    }

    @Override
    public void initializesWith(final Block block) {
        initializers.add(block);
    }

    @Override
    public void isConcludedWith(final Block block) {
        postfixes.add(block);
    }

    @Override
    public void completesWith(final Block block) {
        completers.add(block);
    }

    @Override
    public <T> T usesMock(final Class<T> classToMock) {
        final T mockObject = Mockito.mock(classToMock);
        postfixes.add(() -> {
            Mockito.reset(mockObject);
        });

        return mockObject;
    }

    public void checkSpecifications(final Report report) {
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

    private SourceGenerator getSourceGenerator() {
        return sourceGenerator;
    }

    public long getSeed() {
        return getSourceGenerator().getSeed();
    }

}
