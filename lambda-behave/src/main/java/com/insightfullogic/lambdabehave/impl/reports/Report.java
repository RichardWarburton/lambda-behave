package com.insightfullogic.lambdabehave.impl.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class Report {

    private final List<SuiteReport> suites;

    private SuiteReport currentSuite;

    public Report() {
        suites = new ArrayList<>();
    }

    private void newSuite(String name) {
        currentSuite = new SuiteReport(name);
        suites.add(currentSuite);
    }

    public void recordSuccess(String suite, String specification) {
        recordSpecification(suite, new SpecificationReport(specification));
    }

    public void recordFailure(String suite, String specification, AssertionError cause) {
        SpecificationReport specificationReport = new SpecificationReport(specification, Result.FAILURE, cause.getMessage());
        recordSpecification(suite, specificationReport);
    }

    public void recordError(String suite, String specification, Throwable cause) {
        cause.printStackTrace();
        SpecificationReport specificationReport = new SpecificationReport(specification, Result.ERROR, cause.getMessage());
        recordSpecification(suite, specificationReport);
    }

    public void recordSpecification(String suiteName, SpecificationReport report) {
        if (noSuite() || seenNewSuite(suiteName)) {
            newSuite(suiteName);
        }
        currentSuite.add(report);
    }

    private boolean seenNewSuite(String suite) {
        return !currentSuite.getName().equals(suite);
    }

    private boolean noSuite() {
        return currentSuite == null;
    }


    public Stream<SuiteReport> suites() {
        return suites.stream();
    }

}
