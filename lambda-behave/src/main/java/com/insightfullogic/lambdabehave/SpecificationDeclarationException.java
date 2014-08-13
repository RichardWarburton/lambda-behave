package com.insightfullogic.lambdabehave;

/**
 * Exception that gets thrown when there is an error declaring specifications.
 */
public class SpecificationDeclarationException extends IllegalArgumentException {
    public SpecificationDeclarationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SpecificationDeclarationException(final String message) {
        super(message);
    }
}
