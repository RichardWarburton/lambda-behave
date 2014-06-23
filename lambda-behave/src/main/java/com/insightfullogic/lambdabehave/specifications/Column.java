package com.insightfullogic.lambdabehave.specifications;

/**
 * A column represents a single series of data values.
 */
public interface Column<T> {

    /**
     * Add another element to the column.
     *
     * @param value the element to add.
     * @return this the fluent builder object
     */
    Column<T> and(T value);

    /**
     * Specify the actual behaviour.
     *
     * @param description a human readable description of the behaviour you're expecting.
     * @param specification a function which describes in code the expected behaviour.
     */
    void toShow(String description, ColumnDataSpecification<T> specification);
}
