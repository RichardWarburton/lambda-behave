package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * Implement this interface to specify a behaviour that requires
 * a single data value.
 */
@FunctionalInterface
public interface ColumnDataSpecification<T> {

    /**
     * Callback method which specifies the actual behaviour.
     *
     * @param expect the callback object used to describe expectations
     * @param value the parameter value
     */
    void specifyBehaviour(Expect expect, T value) throws Exception;

}
