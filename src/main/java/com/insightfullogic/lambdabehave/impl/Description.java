package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Specification;
import com.insightfullogic.lambdabehave.expectations.Expect;
import com.insightfullogic.lambdabehave.impl.reports.Report;

public final class Description {

    private final String suite;
    private final Report report;

    public Description(String suite, Report report) {
        this.suite = suite;
        this.report = report;
    }

    public void should(String description, Specification specification) {
        try {
            Expect expect = new Expect();
            specification.specifyBehaviour(expect);
            report.recordSuccess(suite, description);
        } catch (AssertionError cause) {
            report.recordFailure(suite, description, cause);
        } catch (Throwable cause) {
            report.recordError(suite, description, cause);
        }
    }

}
