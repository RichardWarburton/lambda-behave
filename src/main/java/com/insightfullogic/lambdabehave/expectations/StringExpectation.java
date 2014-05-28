package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matchers;

/**
 * .
 */
public class StringExpectation extends BoundExpectation<String> {
    public StringExpectation(String str) {
        super(str);
    }

    public void isEmptyString() {
        expect(Matchers.isEmptyString());
    }

    public void isEmptyOrNullString() {
        expect(Matchers.isEmptyOrNullString());
    }

    public void equalToIgnoringCase(String expectedString) {
        expect(Matchers.equalToIgnoringCase(expectedString));
    }

    public void equalToIgnoringWhiteSpace(String expectedString) {
        expect(Matchers.equalToIgnoringWhiteSpace(expectedString));
    }

    public void containsString(String substring) {
        expect(Matchers.containsString(substring));
    }

    public void endsWith(String suffix) {
        expect(Matchers.endsWith(suffix));
    }

    public void startsWith(String prefix) {
        expect(Matchers.startsWith(prefix));
    }
}
