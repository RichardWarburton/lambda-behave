package com.insightfullogic.lambdabehave;

/**
 * Callback interface to allow you to describe a suite of
 * behaviours about some concept.
 */
@FunctionalInterface
public interface Suite {

    public void specifySuite(Description description);

}
