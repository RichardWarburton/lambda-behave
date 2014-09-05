package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.DescriptionRecorder;
import com.insightfullogic.lambdabehave.specifications.ThreeColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;

/**
 * .
 */
public class TripletSpecRecorder<F, S, T> implements ThreeColumns<F, S, T> {

    private final DescriptionRecorder descriptionRecorder;

    public TripletSpecRecorder(final DescriptionRecorder descriptionRecorder) {
        this.descriptionRecorder = descriptionRecorder;
    }

    @Override
    public ThreeColumns<F, S, T> toShow(String description, ThreeColumnDataSpecification<F, S, T> specification) {
        descriptionRecorder.toShow(description, specification);
        return this;
    }

    @Override
    public ThreeColumns<F, S, T> and(F first, S second, T third) {
        return this;
    }

}
