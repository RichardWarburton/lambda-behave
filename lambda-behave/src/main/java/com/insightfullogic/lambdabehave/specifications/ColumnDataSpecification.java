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
     * @param expect
     * @param value
     */
    public void specifyBehaviour(Expect expect, T value);

}
