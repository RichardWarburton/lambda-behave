package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.DescriptionRecorder;
import com.insightfullogic.lambdabehave.specifications.TwoColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

/**
 * .
 */
public class PairSpecRecorder<F, S> implements TwoColumns<F, S> {

    private final DescriptionRecorder descriptionRecorder;

    public PairSpecRecorder(final DescriptionRecorder descriptionRecorder) {
        this.descriptionRecorder = descriptionRecorder;
    }

    @Override
    public TwoColumns<F, S> toShow(String description, TwoColumnDataSpecification<F, S> specification) {
        descriptionRecorder.toShow(description, specification);
        return this;
    }

    @Override
    public TwoColumns<F, S> and(F first, S second) {
        return this;
    }

}
