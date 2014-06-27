package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.generators.GeneratedThreeColumns;
import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.ThreeColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;

import java.util.ArrayList;
import java.util.List;

public final class TripletBuilder<F, S, T> implements ThreeColumns<F,S,T>, GeneratedThreeColumns<F,S,T> {

    private final List<Row> values;
    private final Specifier specifier;

    private class Row {
        private final F first;
        private final S second;
        private final T third;
        private Row(F first, S second, T third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    public TripletBuilder(F first, S second, T third, Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        and(first, second, third);
    }

    public TripletBuilder(List<F> first, List<S> second, List<T> third, Specifier specifier) {
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
    public TripletBuilder<F, S, T> and(F first, S second, T third) {
        values.add(new Row(first, second, third));
        return this;
    }

    @Override
    public void toShow(String descriptionFormat, ThreeColumnDataSpecification<F, S, T> specification) {
        values.forEach(row -> {
            String description = String.format(descriptionFormat, row.first, row.second, row.third);
            specifier.specifyBehaviour(description, row.first, row.second, row.third, specification);
        });
    }
}
