package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.ThreeColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;

import java.util.ArrayList;
import java.util.List;

public final class TripletBuilder<F, S, T> implements ThreeColumns<F,S,T> {

    private final List<Row> values;
    private final Specifier specifier;

    private class Row {
        private final F first;
        private final S second;
        private final T third;
        private Row(final F first, final S second, final T third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    public TripletBuilder(final F first, final S second, final T third, final Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        and(first, second, third);
    }

    public TripletBuilder(final List<F> first, final List<S> second, final List<T> third, final Specifier specifier) {
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
    public TripletBuilder<F, S, T> and(final F first, final S second, final T third) {
        values.add(new Row(first, second, third));
        return this;
    }

    @Override
    public ThreeColumns<F, S, T> toShow(final String descriptionFormat, final ThreeColumnDataSpecification<F, S, T> specification) {
        for (int i = 0; i < values.size(); i++) {
            Row row = values.get(i);
            String description = String.format(descriptionFormat, row.first, row.second, row.third);
            if (description.equals(descriptionFormat)) {
                description += String.format("(%s, %s, %s)", row.first, row.second, row.third);
            }
            description += " (seed: " + specifier.getSeed() + ")";
            specifier.specifyBehaviour(String.valueOf(i) + ": " + description, row.first, row.second, row.third, specification);
        }
        return this;
    }
}
