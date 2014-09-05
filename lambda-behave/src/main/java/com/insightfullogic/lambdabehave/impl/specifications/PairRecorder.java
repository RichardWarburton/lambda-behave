package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.TwoColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

import java.util.ArrayList;
import java.util.List;

public final class PairRecorder<F, S> implements TwoColumns<F,S> {

    private final List<PairRow<F, S>> values;
    private final Specifier specifier;

    public PairRecorder(final F first, final S second, final Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        and(first, second);
    }

    public PairRecorder(final List<F> first, final List<S> second, final Specifier specifier) {
        this.specifier = specifier;
        final int size = first.size();
        if (size != second.size()) {
            throw new IllegalArgumentException("Lengths not identical: " + size + ", " + second.size());
        }

        values = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            and(first.get(i), second.get(i));
        }
    }

    @Override
    public PairRecorder<F, S> and(final F first, final S second) {
        values.add(new PairRow<>(first, second));
        return this;
    }

    @Override
    public TwoColumns<F, S> toShow(final String descriptionFormat, final TwoColumnDataSpecification<F, S> specification) {
        specifier.recordDataDrivenSpecification2(descriptionFormat, values);
        return this;
    }

}
