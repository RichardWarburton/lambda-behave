package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * Implement this interface to specify a behaviour that requires
 * three data values.
 */
@FunctionalInterface
public interface ThreeColumnDataSpecification<F, S, T> {

    /**
     * Callback method which specifies the actual behaviour.
     *
     * @param expect the callback object used to describe expectations
     * @param first the first parameter value
     * @param second the second parameter value
     * @param third the third parameter value
     */
    public void specifyBehaviour(Expect expect, F first, S second, T third) throws Exception;

}
