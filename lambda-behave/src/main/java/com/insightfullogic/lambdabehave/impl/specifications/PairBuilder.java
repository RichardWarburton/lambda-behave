package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.TwoColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 */
public class PairBuilder<F, S> implements TwoColumns<F,S> {

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

    @Override
    public PairBuilder<F, S> and(F first, S second) {
        values.add(new Row(first, second));
        return this;
    }

    @Override
    public void toShow(String descriptionFormat, TwoColumnDataSpecification<F, S> specification) {
        values.forEach(row -> {
            String description = String.format(descriptionFormat, row.first, row.second);
            specifier.specifyBehaviour(description, row.first, row.second, specification);
        });
    }
}
