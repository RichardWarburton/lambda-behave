package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.Specification;

public final class Description {

    private final Specifier specifier;

    public Description(Specifier specifier) {
        this.specifier = specifier;
    }

    public void should(String description, Specification specification) {
        specifier.specifyBehaviour(description, specification);
    }

    public <T> Column<T> uses(T value) {
        return new Column<>(value, specifier);
    }

}
