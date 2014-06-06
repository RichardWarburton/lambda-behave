package com.insightfullogic.lambdabehave.impl.reports;

/**
 * .
 */
public class ReportFactory {

    private static Report report;

    public static void init() {
        report = new Report();
    }

    public static Report getReport() {
        return report;
    }

}
