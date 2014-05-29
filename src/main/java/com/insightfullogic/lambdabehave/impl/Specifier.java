package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.expectations.Expect;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.specifications.OneColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.Specification;

/**
 * .
 */
public class Specifier {

    private final Report report;
    private final String suite;

    public Specifier(Report report, String suite) {
        this.report = report;
        this.suite = suite;
    }

    public void specifyBehaviour(String description, Specification specification) {
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

    public void specifyBehaviour(String description, T value, OneColumnDataSpecification<T> specification) {
        specifyBehaviour(description, expect -> specification.specifyBehaviour(expect, value));
    }
}
