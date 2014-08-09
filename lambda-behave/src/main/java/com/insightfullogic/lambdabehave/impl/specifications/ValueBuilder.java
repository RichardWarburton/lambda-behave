package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;

import java.util.ArrayList;
import java.util.List;

public final class ValueBuilder<T> implements Column<T> {

    private final List<T> values;
    private final Specifier specifier;

    public ValueBuilder(T value, Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        values.add(value);
    }

    public ValueBuilder(List<T> values, Specifier specifier) {
        this.specifier = specifier;
        this.values = values;
    }

    @Override
    public ValueBuilder<T> and(T value) {
        values.add(value);
        return this;
    }

    @Override
    public Column<T> toShow(String descriptionFormat, ColumnDataSpecification<T> specification) {
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
