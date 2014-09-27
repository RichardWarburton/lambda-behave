package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * .
 */
public final class StringExpectation extends BoundExpectation<String> {
    public StringExpectation(final String str, final boolean positive) {
        super(str, positive);
    }

    public StringExpectation isEmptyString() {
        return matches(Matchers.isEmptyString());
    }

    public StringExpectation isEmptyOrNullString() {
        return matches(Matchers.isEmptyOrNullString());
    }

    public StringExpectation equalToIgnoringCase(final String expectedString) {
        return matches(Matchers.equalToIgnoringCase(expectedString));
    }

    public StringExpectation equalToIgnoringWhiteSpace(final String expectedString) {
        return matches(Matchers.equalToIgnoringWhiteSpace(expectedString));
    }

    public StringExpectation containsString(final String substring) {
        return matches(Matchers.containsString(substring));
    }

    public StringExpectation endsWith(final String suffix) {
        return matches(Matchers.endsWith(suffix));
    }

    public StringExpectation startsWith(final String prefix) {
        return matches(Matchers.startsWith(prefix));
    }

    private StringExpectation matches(final Matcher<String> matcher) {
        assertThat(objectUnderTest, matcher);
        return this;
    }

    public StringExpectation matches(String regex) {
        assertTrue("String " + objectUnderTest + " does not match regex " + regex, objectUnderTest.matches(regex));
        return this;
    }

}
