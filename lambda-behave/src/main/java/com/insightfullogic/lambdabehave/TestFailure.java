package com.insightfullogic.lambdabehave;

/**
 * The exception thrown by the BehaveRunner when a test fails. You
 * shouldn't need to worry about is exception unless you are directly
 * interacting with the runner API and not just running via maven/your IDE.
 *
 * @see com.insightfullogic.lambdabehave.BehaveRunner
 */
public class TestFailure extends RuntimeException {

    private static final long serialVersionUID = -4114485410147624963L;

	public TestFailure(final String message) {
        super(message);
    }

}
