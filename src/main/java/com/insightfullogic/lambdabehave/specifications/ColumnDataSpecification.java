package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * Implement this interface to specify a behaviour that requires
 * a single data value.
 */
@FunctionalInterface
public interface ColumnDataSpecification<T> {

    public void specifyBehaviour(Expect expect, T value);

}
