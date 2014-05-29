package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * .
 */
public interface ColumnDataSpecification<T> {

    public void specifyBehaviour(Expect expect, T value);

}
