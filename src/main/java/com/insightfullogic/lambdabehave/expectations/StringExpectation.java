package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;

/**
 * .
 */
public final class StringExpectation extends BoundExpectation<String> {
    public StringExpectation(String str, boolean positive) {
        super(str, positive);
    }

    public StringExpectation isEmptyString() {
        return matches(Matchers.isEmptyString());
    }

    public StringExpectation isEmptyOrNullString() {
        return matches(Matchers.isEmptyOrNullString());
    }

    public StringExpectation equalToIgnoringCase(String expectedString) {
        return matches(Matchers.equalToIgnoringCase(expectedString));
    }

    public StringExpectation equalToIgnoringWhiteSpace(String expectedString) {
        return matches(Matchers.equalToIgnoringWhiteSpace(expectedString));
    }

    public StringExpectation containsString(String substring) {
        return matches(Matchers.containsString(substring));
    }

    public StringExpectation endsWith(String suffix) {
        return matches(Matchers.endsWith(suffix));
    }

    public StringExpectation startsWith(String prefix) {
        return matches(Matchers.startsWith(prefix));
    }

    private StringExpectation matches(Matcher<String> matcher) {
        assertThat(objectUnderTest, matcher);
        return this;
    }
}
