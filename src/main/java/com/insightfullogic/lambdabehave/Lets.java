package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Description;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.ReportFactory;

public final class Lets {

    public static void describe(String name, Suite behavior) {
        Report report = ReportFactory.getReport();
        Description description = new Description(name, report);
        behavior.specifySuite(description);
    }

}
