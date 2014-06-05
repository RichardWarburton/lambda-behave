package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.ReportFactory;

/**
 * Entry point class to describing a specification.
 */
public final class Lets {

    /**
     * Describe a suite of behaviours.
     *
     * @param name the name of the concept you're specifying.
     * @param behaviours the suite of behaviours you're specifying.
     */
    public static void describe(String name, Suite behaviours) {
        Report report = ReportFactory.getReport();
        Specifier specifier = new Specifier(name, report);
        Description description = new Description(specifier);
        behaviours.specifySuite(description);
        specifier.checkSpecifications();
    }

}
