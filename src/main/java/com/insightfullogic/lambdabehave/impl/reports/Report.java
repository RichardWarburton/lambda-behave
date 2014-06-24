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

    public void recordSpecification(String suiteName, SpecificationReport report) {
        onSuiteName(suiteName);
        currentSuite.add(report);
    }

    public void onSuiteName(String suiteName) {
        if (noSuite() || seenNewSuite(suiteName)) {
            newSuite(suiteName);
        }
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

    public List<SuiteReport> getSuites() {
        return suites;
    }
}
