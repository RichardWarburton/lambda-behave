package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Block;
import com.insightfullogic.lambdabehave.Description;
import com.insightfullogic.lambdabehave.SpecificationDeclarationException;
import com.insightfullogic.lambdabehave.generators.GeneratedDescription;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import com.insightfullogic.lambdabehave.impl.generators.GeneratedDescriptionBuilder;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.Specifiers;
import com.insightfullogic.lambdabehave.impl.specifications.*;
import com.insightfullogic.lambdabehave.specifications.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

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
@SuppressWarnings("unchecked")
public class Specifier implements Description {

    private final String suiteName;
    private final Blocks initializers;
    private final List<CompleteBehaviour> behaviours;
    private final Blocks completers;
    private final SourceGenerator sourceGenerator;

    public Specifier(final String suite, final SourceGenerator sourceGenerator) {
        this.suiteName = suite;
        this.sourceGenerator = sourceGenerator;

        initializers = new Blocks(suiteName);
        behaviours = new ArrayList<>();
        completers = new Blocks(suiteName);
    }

    public <T> void recordDataDrivenSpecification(String descriptionFormat, List<T> values) {
        DescriptionRecorder recorder = Specifiers.recordCode(sourceGenerator, suiteName, descriptionFormat);
        ColumnDataSpecification<T> specification = (ColumnDataSpecification<T>) recorder.getColumnDataSpecification();
        Objects.requireNonNull(specification);
        for (int i = 0; i < values.size(); i++) {
            T value = values.get(i);
            String description = makeDescription(descriptionFormat, i, "(%s)", value);
            newBehaviour(description, recorder, expect -> specification.specifyBehaviour(expect, value));
        }
    }

    public <F, S> void recordDataDrivenSpecification2(String descriptionFormat, List<PairRow<F, S>> values) {
        DescriptionRecorder recorder = Specifiers.recordCode(sourceGenerator, suiteName, descriptionFormat);
        TwoColumnDataSpecification<F, S> specification = (TwoColumnDataSpecification<F, S>) recorder.getTwoColumnDataSpecification();
        Objects.requireNonNull(specification);
        for (int i = 0; i < values.size(); i++) {
            PairRow<F,S> pairRow = values.get(i);
            String description = makeDescription(descriptionFormat, i, "(%s, %s)", pairRow.first, pairRow.second);
            newBehaviour(description, recorder, expect -> specification.specifyBehaviour(expect, pairRow.first, pairRow.second));
        }
    }

    public <F, S, T> void recordDataDrivenSpecification3(String descriptionFormat, List<TripletRow<F, S, T>> values) {
        DescriptionRecorder recorder = Specifiers.recordCode(sourceGenerator, suiteName, descriptionFormat);
        ThreeColumnDataSpecification<F, S, T> specification = (ThreeColumnDataSpecification<F, S, T>) recorder.getThreeColumnDataSpecification();
        Objects.requireNonNull(specification);
        for (int i = 0; i < values.size(); i++) {
            TripletRow<F, S, T> tripletRow = values.get(i);
            String description = makeDescription(descriptionFormat, i, "(%s, %s, %s)", tripletRow.first, tripletRow.second, tripletRow.third);
            newBehaviour(description, recorder, expect -> specification.specifyBehaviour(expect, tripletRow.first, tripletRow.second, tripletRow.third));
        }
    }

    private <F, S> String makeDescription(String descriptionFormat, int i, String format, Object... args) {
        String description = String.format(descriptionFormat, args);
        if (description.equals(descriptionFormat)) {
            description += String.format(format, args);
        }
        description = String.valueOf(i) + ": " + description + " (seed: " + getSeed() + ")";
        return description;
    }

    @Override
    public void should(final String description, final Specification unused) {
        Objects.nonNull(description);

        if (behaviours.removeIf(behaviour -> behaviour.hasDescription(description))) {

            behaviours.add(new CompleteSpecification(expect -> {

                throw new SpecificationDeclarationException(
                        "You can't declare multiple specifications with the same name. Name: '" + description + "'");

            }, description, suiteName));
        } else {
            DescriptionRecorder recorder = Specifiers.recordCode(sourceGenerator, suiteName, description);
            Specification specification = recorder.getSpecification();
            Objects.requireNonNull(specification);
            newBehaviour(description, recorder, specification);
        }
    }

    private void newBehaviour(String description, DescriptionRecorder recorder, Specification specification) {
        behaviours.add(new CompleteSpecification(recorder.getPrefixes(), specification, description,
                recorder.getPostfixes(), suiteName));
    }

    @Override
    public <T> Column<T> uses(final T value) {
        return new ValueRecorder<>(value, this);
    }

    @Override
    public <T> Column<T> uses(final List<T> values) {
        // Additional arraylist required to ensure
        // we can with more values
        return new ValueRecorder<>(new ArrayList<>(values), this);
    }

    @Override
    public <T> Column<T> uses(final Stream<T> values) {
        return uses(values.collect(toList()));
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(final F first, final S second) {
        return new PairRecorder<>(first, second, this);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(final List<F> first, final List<S> second) {
        return new PairRecorder<F, S>(new ArrayList<>(first), new ArrayList<>(second), this);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(final Stream<F> first, final Stream<S> second) {
        return new PairRecorder<F, S>(first.collect(toList()), second.collect(toList()), this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(final F first, final S second, final T third) {
        return new TripletRecorder<>(first, second, third, this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(final List<F> first, final List<S> second, final List<T> third) {
        return new TripletRecorder<F, S, T>(new ArrayList<>(first),
                                           new ArrayList<>(second),
                                           new ArrayList<>(third),
                                           this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(final Stream<F> first, final Stream<S> second, final Stream<T> third) {
        return new TripletRecorder<F, S, T>(first.collect(toList()),
                                           second.collect(toList()),
                                           third.collect(toList()),
                                           this);
    }

    @Override
    public GeneratedDescription requires(final int exampleCount) {
        return new GeneratedDescriptionBuilder(sourceGenerator, exampleCount, this);
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
    public void initializesWith(final Block block) {
        initializers.add(block);
    }

    @Override
    public void completesWith(final Block block) {
        completers.add(block);
    }

    public void playbackSpecifications(final Report report) {
        report.onSuiteName(suiteName);
        completeBehaviours().forEach(behaviour -> report.recordSpecification(suiteName, behaviour.playbackBehaviour()));
    }

    public Stream<CompleteBehaviour> completeBehaviours() {
        if (behaviours.isEmpty())
            return Stream.empty();

        return concat(
                concat(initializers.completeFixtures("initializer"),
                       behaviours.stream()),
                       completers.completeFixtures("completer"));
    }

    public String getSuiteName() {
        return suiteName;
    }

    @Override
    public long getSeed() {
        return sourceGenerator.getSeed();
    }

    // ---------------- Unimplemented Methods ----------------

    @Override
    public void isSetupWith(final Block block) {
    }

    @Override
    public void isConcludedWith(final Block block) {
    }
}
