package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;

public class ComparableExpectation<T extends Comparable<T>> extends BoundExpectation<T> {

    ComparableExpectation(T value, boolean positive) {
        super(value, positive);
    }

    public ComparableExpectation<T> isGreaterThan(T value) {
        return matches(Matchers.greaterThan(value));
    }

    public ComparableExpectation<T> isGreaterThanOrEqualTo(T value) {
        return matches(Matchers.greaterThanOrEqualTo(value));
    }

    public ComparableExpectation<T> isLessThan(T value) {
        return matches(Matchers.lessThan(value));
    }

    public ComparableExpectation<T> isLessThanOrEqualTo(T value) {
        return matches(Matchers.lessThanOrEqualTo(value));
    }

    public ComparableExpectation<T> comparesEqualTo(T expected) {
        return matches(Matchers.comparesEqualTo(expected));
    }

    private ComparableExpectation<T> matches(Matcher<T> matcher) {
        assertThat(objectUnderTest, negatedIfNeeded(matcher));
        return this;
    }

}
