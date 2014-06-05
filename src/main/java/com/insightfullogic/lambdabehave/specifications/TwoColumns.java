package com.insightfullogic.lambdabehave.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 */
public final class TwoColumns<F, S> {

    private final List<Row> values;
    private final Specifier specifier;

    private class Row {
        private final F first;
        private final S second;
        private Row(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    // TODO: hide this
    public TwoColumns(F first, S second, Specifier specifier) {
        this.specifier = specifier;
        values = new ArrayList<>();
        and(first, second);
    }

    public TwoColumns<F, S> and(F first, S second) {
        values.add(new Row(first, second));
        return this;
    }

    public void toShow(String description, TwoColumnDataSpecification<F, S> specification) {
        values.forEach(row ->
            specifier.specifyBehaviour(description, row.first, row.second, specification)
        );
    }
}
