package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;

import java.util.ArrayList;
import java.util.List;

public final class ValueBuilder<T> implements Column<T> {

    private final List<T> values;
    private final Specifier specifier;

    public ValueBuilder(final T value, final Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        values.add(value);
    }

    public ValueBuilder(final List<T> values, final Specifier specifier) {
        this.specifier = specifier;
        this.values = values;
    }

    @Override
    public ValueBuilder<T> and(final T value) {
        values.add(value);
        return this;
    }

    @Override
    public Column<T> toShow(final String descriptionFormat, final ColumnDataSpecification<T> specification) {
        for (int i = 0; i < values.size(); i++) {
            T value = values.get(i);
            String description = String.format(descriptionFormat, value);
            if (description.equals(descriptionFormat)) {
                description += String.format("(%s)", value);
            }
            description += " (seed: " + specifier.getSeed() + ")";
            specifier.specifyBehaviour(String.valueOf(i) + ": " + description, value, specification);
        }
        return this;
    }
}
