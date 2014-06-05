package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.Specification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

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

    public <F, S> TwoColumns<F, S> uses(F first, S second) {
        return new TwoColumns<>(first, second, specifier);
    }

    public <F, S, T> ThreeColumns<F, S, T> uses(F first, S second, T third) {
        return new ThreeColumns<>(first, second, third, specifier);
    }

}
