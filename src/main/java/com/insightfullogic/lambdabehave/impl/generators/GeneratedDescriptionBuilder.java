package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.specifications.ValueBuilder;
import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.generators.NumberGenerator;
import com.insightfullogic.lambdabehave.generators.GeneratedColumn;
import com.insightfullogic.lambdabehave.generators.GeneratedDescription;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GeneratedDescriptionBuilder implements GeneratedDescription {

    private final NumberGenerator rng;
    private final int numberOfInstances;
    private final Specifier specifier;

    public GeneratedDescriptionBuilder(final NumberGenerator rng, final int numberOfInstances, final Specifier specifier) {
        this.rng = rng;
        this.numberOfInstances = numberOfInstances;
        this.specifier = specifier;
    }

    @Override
    public <T> GeneratedColumn<T> example(final Generator<T> generator) {
        final List<T> values = Stream.generate(() -> generator.generate(rng))
                                     .limit(numberOfInstances)
                                     .collect(toList());
        return new ValueBuilder<T>(values, specifier);
    }

}
