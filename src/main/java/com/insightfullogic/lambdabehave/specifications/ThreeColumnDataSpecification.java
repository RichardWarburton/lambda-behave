package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * .
 */
public interface ThreeColumnDataSpecification<F, S, T> {

    public void specifyBehaviour(Expect expect, F first, S second, T third);

}
