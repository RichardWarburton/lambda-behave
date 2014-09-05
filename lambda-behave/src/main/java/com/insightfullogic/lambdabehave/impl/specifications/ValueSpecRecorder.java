package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.DescriptionRecorder;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;

/**
 * .
 */
public class ValueSpecRecorder<T> implements Column<T> {

    private final DescriptionRecorder descriptionRecorder;

    public ValueSpecRecorder(final DescriptionRecorder descriptionRecorder) {
        this.descriptionRecorder = descriptionRecorder;
    }

    @Override
    public Column<T> toShow(final String description, final ColumnDataSpecification<T> specification) {
        descriptionRecorder.toShow(description, specification);
        return this;
    }

    @Override
    public Column<T> and(T value) {
        return this;
    }

}
