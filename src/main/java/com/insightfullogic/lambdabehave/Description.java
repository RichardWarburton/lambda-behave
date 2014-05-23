package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.expectations.Expect;
import com.insightfullogic.lambdabehave.reporting.Reporter;

public final class Description {

    private final String suite;

    Description(String suite) {
        this.suite = suite;
    }

    public void should(String description, Specification specification) {
        try {
            Expect expect = new Expect();
            specification.specifyBehaviour(expect);
            Reporter.current.recordSuccess(suite, description);
        } catch (AssertionError cause) {
            Reporter.current.recordFailure(suite, description, cause);
        } catch (Throwable cause) {
            Reporter.current.recordError(suite, description, cause);
        }
    }

}
