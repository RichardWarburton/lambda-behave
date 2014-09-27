package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.codegen.Templated;

/**
 * A column represents a single series of data values.
 */
@Templated
public interface Column<T> {

    /**
     * Specify the actual behaviour.
     *
     * @param description a human readable description of the behaviour you're expecting.
     * @param specification a function which describes in code the expected behaviour.
     * @return this
     */
    Column<T> toShow(String description, ColumnDataSpecification<T> specification);

    /**
     * Add another element to the column.
     *
     * @param value the element to with.
     * @return this the fluent builder object
     */
    Column<T> and(T value);
}
