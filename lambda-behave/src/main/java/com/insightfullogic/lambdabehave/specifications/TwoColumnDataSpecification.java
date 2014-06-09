package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * Implement this interface to specify a behaviour that requires
 * two data values.
 */
@FunctionalInterface
public interface TwoColumnDataSpecification<F, S> {

    /**
     * Callback method which specifies the actual behaviour.
     *
     * @param expect
     * @param first
     * @param second
     */
    public void specifyBehaviour(Expect expect, F first, S second);

}
