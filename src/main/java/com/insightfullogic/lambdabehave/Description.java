package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.expectations.Expect;

public final class Description {

    private final String suite;

    Description(String suite) {
        this.suite = suite;
    }

    public void should(String description, Specification specification) {
        try {
            Expect expect = new Expect();
            specification.specifyBehaviour(expect);
            Runner.current.recordSuccess(suite, description);
        } catch (AssertionError cause) {
            Runner.current.recordFailure(suite, description, cause);
        } catch (Throwable cause) {
            Runner.current.recordError(suite, description, cause);
        }
    }

}
