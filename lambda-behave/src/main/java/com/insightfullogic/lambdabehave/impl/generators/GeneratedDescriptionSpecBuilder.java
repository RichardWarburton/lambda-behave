package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.GeneratedDescription;
import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import com.insightfullogic.lambdabehave.impl.DescriptionRecorder;
import com.insightfullogic.lambdabehave.impl.specifications.*;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GeneratedDescriptionSpecBuilder implements GeneratedDescription {

    private final int numberOfInstances;
    private final DescriptionRecorder description;

    private SourceGenerator sourceGenerator;

    public GeneratedDescriptionSpecBuilder(final SourceGenerator sourceGenerator, final int numberOfInstances, final DescriptionRecorder description) {
        this.sourceGenerator = sourceGenerator;
        this.numberOfInstances = numberOfInstances;
        this.description = description;
    }

    @Override
    public GeneratedDescription withSource(final SourceGenerator sourceGenerator) {
        this.sourceGenerator = sourceGenerator;
        return this;
    }

    @Override
    public <T> Column<T> example(final Generator<T> generator) {
        return new ValueSpecRecorder<>(description);
    }

    @Override
    public <F, S> TwoColumns<F, S> example(
            final Generator<F> firstGenerator,
            final Generator<S> secondGenerator) {

        return new PairSpecRecorder<>(description);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> example(
            final Generator<F> firstGenerator,
            final Generator<S> secondGenerator,
            final Generator<T> thirdGenerator) {

        return new TripletSpecRecorder<F, S, T>(description);
    }

    private <T> List<T> generateValues(final Generator<T> generator) {
        return Stream.generate(() -> generator.generate(sourceGenerator))
                     .limit(numberOfInstances)
                     .collect(toList());
    }

}
