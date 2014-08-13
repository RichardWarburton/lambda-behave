package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;

public class DoubleExpectation extends BoundExpectation<Double> {

    DoubleExpectation(final Double value, final boolean positive) {
        super(value, positive);
    }

    public DoubleExpectation closeTo(final double operand, final double error) {
        return matches(Matchers.closeTo(operand, error));
    }

    private DoubleExpectation matches(final Matcher<Double> matcher) {
        assertThat(objectUnderTest, matcher);
        return this;
    }

}
