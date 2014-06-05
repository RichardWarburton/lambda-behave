package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.specifications.PairBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.TripletBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.ValueBuilder;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.Specification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

/**
 * A Description is a fluent builder to describe a
 * complete specification.
 */
public final class Description {

    private final Specifier specifier;

    Description(Specifier specifier) {
        this.specifier = specifier;
    }

    public void should(String description, Specification specification) {
        specifier.specifyBehaviour(description, specification);
    }

    public <T> Column<T> uses(T value) {
        return new ValueBuilder<>(value, specifier);
    }

    public <F, S> TwoColumns<F, S> uses(F first, S second) {
        return new PairBuilder<>(first, second, specifier);
    }

    public <F, S, T> ThreeColumns<F, S, T> uses(F first, S second, T third) {
        return new TripletBuilder<>(first, second, third, specifier);
    }

    public void setsUp(Runnable block) {
        specifier.addPrefix(block);
    }

    public void tearsDown(Runnable block) {
        specifier.addPostfix(block);
    }
}
