package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.generators.CompleteThreeColumns;

/**
 * ThreeColumns represents a three column table of data.
 */
public interface ThreeColumns<F, S, T> extends CompleteThreeColumns<F, S, T> {

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
