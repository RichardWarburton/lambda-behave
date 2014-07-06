package com.insightfullogic.lambdabehave;

/**
 * Exception that gets thrown when there is an error declaring specifications.
 */
public class SpecificationDeclarationException extends IllegalArgumentException {
    public SpecificationDeclarationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpecificationDeclarationException(String message) {
        super(message);
    }
}
