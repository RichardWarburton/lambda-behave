package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;

public class DoubleExpectation extends BoundExpectation<Double> {

    DoubleExpectation(Double value, boolean positive) {
        super(value, positive);
    }

    public DoubleExpectation closeTo(double operand, double error) {
        return matches(Matchers.closeTo(operand, error));
    }

    private DoubleExpectation matches(Matcher<Double> matcher) {
        assertThat(objectUnderTest, matcher);
        return this;
    }

}
