package com.insightfullogic.lambdabehave;

public class SpecificationError extends RuntimeException {
    private static final long serialVersionUID = -6117653029888070270L;

	public SpecificationError(String message, Throwable cause) {
        super(message, cause);
    }
}