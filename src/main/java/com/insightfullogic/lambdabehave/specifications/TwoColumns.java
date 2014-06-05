package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.impl.specifications.PairBuilder;
import com.insightfullogic.lambdabehave.specifications.TwoColumnDataSpecification;

/**
 * .
 */
public interface TwoColumns<F, S> {

    TwoColumns<F, S> and(F first, S second);

    void toShow(String description, TwoColumnDataSpecification<F, S> specification);
}
