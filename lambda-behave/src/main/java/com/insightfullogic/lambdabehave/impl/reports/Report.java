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

    private void newSuite(final String name) {
        currentSuite = new SuiteReport(name);
        suites.add(currentSuite);
    }

    public void recordSpecification(final String suiteName, final SpecificationReport report) {
        onSuiteName(suiteName);
        currentSuite.with(report);
    }

    public void onSuiteName(final String suiteName) {
        if (noSuite() || seenNewSuite(suiteName)) {
            newSuite(suiteName);
        }
    }

    private boolean seenNewSuite(final String suite) {
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

    public SuiteReport getSuite() {
        final int size = suites.size();
        if (size != 1) {
            throw new IllegalStateException("Assumed there was only suite, but there were: " + size);
        }

        return suites.get(0);
    }

}
