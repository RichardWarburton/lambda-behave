package com.insightfullogic.lambdabehave.specifications;

/**
 * TwoColumns represents a two column table of data.
 */
public interface TwoColumns<F, S> {

    /**
     * Specify the actual behaviour.
     *
     * @param description a human readable description of the behaviour you're expecting.
     * @param specification a function which describes in code the expected behaviour.
     * @return this
     */
    TwoColumns<F, S> toShow(String description, TwoColumnDataSpecification<F, S> specification);

    /**
     * Add another pair of elements to the column.
     *
     * @param first the first element to with.
     * @param second the second element to with.
     * @return this the fluent builder object
     */
    TwoColumns<F, S> and(F first, S second);

}
