package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.ReportFactory;

public final class Lets {

    public static void describe(String name, Suite behavior) {
        Report report = ReportFactory.getReport();
        Specifier specifier = new Specifier(name, report);
        Description description = new Description(specifier);
        behavior.specifySuite(description);
    }

    public static void given(Runnable runnable) {
        // TODO: re-run block for each test
    }

}
