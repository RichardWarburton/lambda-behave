package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * Implement this interface to specify a behaviour that requires
 * two data values.
 */
@FunctionalInterface
public interface TwoColumnDataSpecification<F, S> {

    public void specifyBehaviour(Expect expect, F first, S second);

}
