package com.insightfullogic.lambdabehave.specifications;

/**
 * TwoColumns represents a two column table of data.
 */
public interface TwoColumns<F, S> {

    /**
     * Add another pair of elements to the column.
     *
     * @param first the first element to add.
     * @param second the second element to add.
     * @return this
     */
    TwoColumns<F, S> and(F first, S second);

    void toShow(String description, TwoColumnDataSpecification<F, S> specification);
}
