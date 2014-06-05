package com.insightfullogic.lambdabehave.specifications;

/**
 * .
 */
public interface TwoColumns<F, S> {

    TwoColumns<F, S> and(F first, S second);

    void toShow(String description, TwoColumnDataSpecification<F, S> specification);
}
