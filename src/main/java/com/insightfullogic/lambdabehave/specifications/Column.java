package com.insightfullogic.lambdabehave.specifications;

/**
 * .
 */
public interface Column<T> {

    Column<T> and(T value);

    void toShow(String description, ColumnDataSpecification<T> specification);
}
