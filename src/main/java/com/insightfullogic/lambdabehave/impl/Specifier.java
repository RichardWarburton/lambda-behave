package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.expectations.Expect;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.Specification;

/**
 * .
 */
public class Specifier {

    private final String suite;
    private final Report report;

    public Specifier(String suite, Report report) {
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

    public <T> void specifyBehaviour(String description, T value, ColumnDataSpecification<T> specification) {
        specifyBehaviour(description, expect -> specification.specifyBehaviour(expect, value));
    }
}
