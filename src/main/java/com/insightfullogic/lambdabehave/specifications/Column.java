package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 */
public final class Column<T> {

    private final List<T> values;
    private final Specifier specifier;

    // TODO: hide this
    public Column(T value, Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        values.add(value);
    }

    public Column<T> and(T value) {
        values.add(value);
        return this;
    }

    public void toShow(String description, ColumnDataSpecification<T> specification) {
        values.forEach(value ->
            specifier.specifyBehaviour(description, value, specification)
        );
    }
}
