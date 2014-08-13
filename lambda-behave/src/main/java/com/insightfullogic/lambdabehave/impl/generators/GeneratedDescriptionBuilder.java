package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.GeneratedDescription;
import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.specifications.PairBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.TripletBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.ValueBuilder;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GeneratedDescriptionBuilder implements GeneratedDescription {

    private final int numberOfInstances;
    private final Specifier specifier;

    private SourceGenerator sourceGenerator;

    public GeneratedDescriptionBuilder(final SourceGenerator sourceGenerator, final int numberOfInstances, final Specifier specifier) {
        this.sourceGenerator = sourceGenerator;
        this.numberOfInstances = numberOfInstances;
        this.specifier = specifier;
    }

    @Override
    public GeneratedDescription withSource(final SourceGenerator sourceGenerator) {
        this.sourceGenerator = sourceGenerator;
        return this;
    }

    @Override
    public <T> Column<T> example(final Generator<T> generator) {
        return new ValueBuilder<T>(generateValues(generator), specifier);
    }

    @Override
    public <F, S> TwoColumns<F, S> example(
            final Generator<F> firstGenerator,
            final Generator<S> secondGenerator) {

        return new PairBuilder<F, S>(
                generateValues(firstGenerator),
                generateValues(secondGenerator),
                specifier);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> example(
            final Generator<F> firstGenerator,
            final Generator<S> secondGenerator,
            final Generator<T> thirdGenerator) {

        return new TripletBuilder<F, S, T>(
                generateValues(firstGenerator),
                generateValues(secondGenerator),
                generateValues(thirdGenerator),
                specifier
        );
    }

    private <T> List<T> generateValues(final Generator<T> generator) {
        return Stream.generate(() -> generator.generate(sourceGenerator))
                     .limit(numberOfInstances)
                     .collect(toList());
    }

}
