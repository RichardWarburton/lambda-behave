package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;

public class ComparableExpectation<T extends Comparable<T>> extends BoundExpectation<T> {

    ComparableExpectation(final T value, final boolean positive) {
        super(value, positive);
    }

    public ComparableExpectation<T> isGreaterThan(final T value) {
        return matches(Matchers.greaterThan(value));
    }

    public ComparableExpectation<T> isGreaterThanOrEqualTo(final T value) {
        return matches(Matchers.greaterThanOrEqualTo(value));
    }

    public ComparableExpectation<T> isLessThan(final T value) {
        return matches(Matchers.lessThan(value));
    }

    public ComparableExpectation<T> isLessThanOrEqualTo(final T value) {
        return matches(Matchers.lessThanOrEqualTo(value));
    }

    public ComparableExpectation<T> comparesEqualTo(final T expected) {
        return matches(Matchers.comparesEqualTo(expected));
    }

    private ComparableExpectation<T> matches(final Matcher<T> matcher) {
        assertThat(objectUnderTest, negatedIfNeeded(matcher));
        return this;
    }

}
