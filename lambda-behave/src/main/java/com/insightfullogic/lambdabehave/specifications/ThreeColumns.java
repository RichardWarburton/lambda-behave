package com.insightfullogic.lambdabehave.specifications;

/**
 * ThreeColumns represents a three column table of data.
 */
public interface ThreeColumns<F, S, T> {

    /**
     * Add another triple of elements to the column.
     *
     * @param first the first element to add.
     * @param second the second element to add.
     * @param third the third element to add.
     * @return this
     */
    ThreeColumns<F, S, T> and(F first, S second, T third);

    /**
     * Specify the actual behaviour.
     *
     * @param description
     * @param specification
     */
    void toShow(String description, ThreeColumnDataSpecification<F, S, T> specification);

}
