package com.insightfullogic.lambdabehave.impl.reports;

import java.util.Stack;

/**
 * Maintains a stack of Report instances to allow nested runners;
 */
public class ReportStore {

    private static ThreadLocal<Stack<Report>> reports;

    static {
        reports = ThreadLocal.withInitial(Stack::new);
    }

    public static void pushReport(Report report) {
        reports().push(report);
    }

    public static Report getReport() {
        return reports().peek();
    }

    public static Report popReport() {
        return reports().pop();
    }

    private static Stack<Report> reports() {
        return reports.get();
    }
}
