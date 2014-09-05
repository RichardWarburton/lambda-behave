package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.Description;
import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.ThreeColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;

import java.util.ArrayList;
import java.util.List;

public final class TripletRecorder<F, S, T> implements ThreeColumns<F,S,T> {

    private final List<TripletRow<F, S, T>> values;
    private final Specifier specifier;

    public TripletRecorder(final F first, final S second, final T third, final Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        and(first, second, third);
    }

    public TripletRecorder(final List<F> first, final List<S> second, final List<T> third, final Specifier specifier) {
        this.specifier = specifier;
        final int size = first.size();
        if (size != second.size() || size != third.size()) {
            throw new IllegalArgumentException("Lengths not identical: " + size + ", " + second.size() + ", " + third.size());
        }

        values = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            and(first.get(i), second.get(i), third.get(i));
        }
    }

    @Override
    public TripletRecorder<F, S, T> and(final F first, final S second, final T third) {
        values.add(new TripletRow<>(first, second, third));
        return this;
    }

    @Override
    public ThreeColumns<F, S, T> toShow(final String descriptionFormat, final ThreeColumnDataSpecification<F, S, T> specification) {
        specifier.recordDataDrivenSpecification3(descriptionFormat, values);
        return this;
    }

}
