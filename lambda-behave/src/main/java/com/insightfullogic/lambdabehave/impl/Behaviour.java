package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.specifications.Specification;

/**
 * A behaviour is a specification with its associated description.
 */
public final class Behaviour {

    private final String description;
    private final Specification specification;

    public Behaviour(final String description, final Specification specification) {
        this.description = description;
        this.specification = specification;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasDescription(final String description) {
        return this.description.equals(description);
    }

    public Specification getSpecification() {
        return specification;
    }
}
