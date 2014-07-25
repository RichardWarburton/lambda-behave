package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.TwoColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

import java.util.ArrayList;
import java.util.List;

public final class PairBuilder<F, S> implements TwoColumns<F,S> {

    private final List<Row> values;
    private final Specifier specifier;

    private class Row {
        private final F first;
        private final S second;

        public Row(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    public PairBuilder(F first, S second, Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        and(first, second);
    }

    public PairBuilder(List<F> first, List<S> second, Specifier specifier) {
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
    public PairBuilder<F, S> and(F first, S second) {
        values.add(new Row(first, second));
        return this;
    }

    @Override
    public TwoColumns<F, S> toShow(String descriptionFormat, TwoColumnDataSpecification<F, S> specification) {
        for (int i = 0; i < values.size(); i++) {
            Row row = values.get(i);
            String description = String.format(descriptionFormat, row.first, row.second);
            if (description.equals(descriptionFormat)) {
                description += String.format("(%s, %s)", row.first, row.second);
            }
            specifier.specifyBehaviour(String.valueOf(i) + ": " + description, row.first, row.second, specification);
        }
        return this;
    }
}
