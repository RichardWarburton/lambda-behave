package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.*;
import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.specifications.PairBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.TripletBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.ValueBuilder;

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
    public GeneratedDescription numberedBy(SourceGenerator sourceGenerator) {
        this.sourceGenerator = sourceGenerator;
        return this;
    }

    @Override
    public <T> GeneratedColumn<T> example(final Generator<T> generator) {
        return new ValueBuilder<T>(generateValues(generator), specifier);
    }

    @Override
    public <F, S> GeneratedTwoColumns<F, S> example(
            Generator<F> firstGenerator,
            Generator<S> secondGenerator) {

        return new PairBuilder<F, S>(
                generateValues(firstGenerator),
                generateValues(secondGenerator),
                specifier);
    }

    @Override
    public <F, S, T> GeneratedThreeColumns<F, S, T> example(
            Generator<F> firstGenerator,
            Generator<S> secondGenerator,
            Generator<T> thirdGenerator) {

        return new TripletBuilder<F, S, T>(
                generateValues(firstGenerator),
                generateValues(secondGenerator),
                generateValues(thirdGenerator),
                specifier
        );
    }

    private <T> List<T> generateValues(Generator<T> generator) {
        return Stream.generate(() -> generator.generate(sourceGenerator))
                     .limit(numberOfInstances)
                     .collect(toList());
    }

}
