package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.generators.CompleteColumn;

/**
 * A column represents a single series of data values.
 */
public interface Column<T> extends CompleteColumn<T> {

    /**
     * Add another element to the column.
     *
     * @param value the element to with.
     * @return this the fluent builder object
     */
    Column<T> and(T value);
}
