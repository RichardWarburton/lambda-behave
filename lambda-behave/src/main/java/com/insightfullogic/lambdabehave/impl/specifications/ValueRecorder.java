package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;

import java.util.ArrayList;
import java.util.List;

public final class ValueRecorder<T> implements Column<T> {

    private final List<T> values;
    private final Specifier specifier;

    public ValueRecorder(final T value, final Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        values.add(value);
    }

    public ValueRecorder(final List<T> values, final Specifier specifier) {
        this.specifier = specifier;
        this.values = values;
    }

    @Override
    public ValueRecorder<T> and(final T value) {
        values.add(value);
        return this;
    }

    @Override
    public Column<T> toShow(final String descriptionFormat, final ColumnDataSpecification<T> specification) {
        specifier.recordDataDrivenSpecification(descriptionFormat, values);
        return this;
    }

}
