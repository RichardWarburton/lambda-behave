package com.insightfullogic.lambdabehave;

/**
 * The exception thrown by the BehaveRunner when a test fails. You
 * shouldn't need to worry about is exception unless you are directly
 * interacting with the runner API and not just running via maven/your IDE.
 *
 * @see com.insightfullogic.lambdabehave.BehaveRunner
 */
public class SpecificationError extends RuntimeException {

    private static final long serialVersionUID = -6117653029888070270L;

	public SpecificationError(final String message, final Throwable cause) {
        super(message, cause);
    }

}
