package com.insightfullogic.lambdabehave.specifications;

/**
 * A column represents a single series of data values.
 */
public interface Column<T> {

    /**
     * Add another element to the column.
     *
     * @param value the element to add.
     * @return this
     */
    Column<T> and(T value);

    void toShow(String description, ColumnDataSpecification<T> specification);
}
