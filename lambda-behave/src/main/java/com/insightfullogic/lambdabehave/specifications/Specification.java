package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.expectations.Expect;

/**
 * Implement this interface to specify a behaviour.
 */
@FunctionalInterface
public interface Specification {

    public void specifyBehaviour(Expect expect) throws Exception;

}