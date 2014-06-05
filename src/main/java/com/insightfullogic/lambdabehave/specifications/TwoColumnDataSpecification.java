package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * .
 */
public interface TwoColumnDataSpecification<F, S> {

    public void specifyBehaviour(Expect expect, F first, S second);

}
