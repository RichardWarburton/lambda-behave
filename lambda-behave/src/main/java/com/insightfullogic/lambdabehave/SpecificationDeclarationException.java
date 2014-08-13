package com.insightfullogic.lambdabehave;

/**
 * Exception that gets thrown when there is an error declaring specifications. You
 * shouldn't need to worry about is exception unless you are directly
 * interacting with the runner API and not just running via maven/your IDE.
 *
 * @see com.insightfullogic.lambdabehave.BehaveRunner
 */
public class SpecificationDeclarationException extends IllegalArgumentException {

    public SpecificationDeclarationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SpecificationDeclarationException(final String message) {
        super(message);
    }

}
