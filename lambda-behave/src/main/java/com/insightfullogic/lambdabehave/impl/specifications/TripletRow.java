package com.insightfullogic.lambdabehave.impl.specifications;

/**
 * .
 */
public class TripletRow<F, S, T> {
    public final F first;
    public final S second;
    public final T third;

    TripletRow(final F first, final S second, final T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}