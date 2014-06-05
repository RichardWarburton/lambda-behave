package com.insightfullogic.lambdabehave.specifications;

/**
 * .
 */
public interface ThreeColumns<F, S, T> {

    ThreeColumns<F, S, T> and(F first, S second, T third);

    void toShow(String description, ThreeColumnDataSpecification<F, S, T> specification);

}
