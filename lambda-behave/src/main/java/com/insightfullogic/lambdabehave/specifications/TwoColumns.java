package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.generators.CompleteTwoColumns;

/**
 * TwoColumns represents a two column table of data.
 */
public interface TwoColumns<F, S> extends CompleteTwoColumns<F, S> {

    /**
     * Add another pair of elements to the column.
     *
     * @param first the first element to with.
     * @param second the second element to with.
     * @return this the fluent builder object
     */
    TwoColumns<F, S> and(F first, S second);

}
