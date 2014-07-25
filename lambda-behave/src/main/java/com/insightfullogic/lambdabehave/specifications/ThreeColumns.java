package com.insightfullogic.lambdabehave.specifications;

/**
 * ThreeColumns represents a three column table of data.
 */
public interface ThreeColumns<F, S, T> {

    /**
     * Specify the actual behaviour.
     *
     * @param description a human readable description of the behaviour you're expecting.
     * @param specification a function which describes in code the expected behaviour.
     * @return this
     */
    ThreeColumns<F, S, T> toShow(String description, ThreeColumnDataSpecification<F, S, T> specification);

    /**
     * Add another triple of elements to the column.
     *
     * @param first the first element to with.
     * @param second the second element to with.
     * @param third the third element to with.
     * @return this the fluent builder object
     */
    ThreeColumns<F, S, T> and(F first, S second, T third);

}
