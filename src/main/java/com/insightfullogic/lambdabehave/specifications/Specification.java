package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * Implement this interface to specify a behaviour.
 */
@FunctionalInterface
public interface Specification {

    /**
     * Callback method which specifies the actual behaviour.
     *
     * @param expect the callback object used to describe expectations
     * @throws Exception any thrown exception will cause the specification to error
     */
    public void specifyBehaviour(Expect expect) throws Exception;

}