package com.insightfullogic.lambdabehave.impl.specifications;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.ThreeColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 */
public final class TripletBuilder<F, S, T> implements ThreeColumns<F,S,T> {

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

    @Override
    public TripletBuilder<F, S, T> and(F first, S second, T third) {
        values.add(new Row(first, second, third));
        return this;
    }

    @Override
    public void toShow(String description, ThreeColumnDataSpecification<F, S, T> specification) {
        values.forEach(row ->
            specifier.specifyBehaviour(description, row.first, row.second, row.third, specification)
        );
    }
}
