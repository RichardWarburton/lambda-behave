package com.insightfullogic.lambdabehave.impl.output;

import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.Result;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;

import java.io.PrintStream;

public final class ConsoleFormatter implements ReportFormatter {

    @Override
    public void format(final Report report) {
        report.suites().forEach(suite -> {
            System.out.print(suite.getName());
            System.out.println();
            suite.specifications().forEach(this::printSpecification);
        });
    }

    private void printSpecification(final SpecificationReport specification) {
        boolean isSuccess = specification.getResult() == Result.SUCCESS;
        
        PrintStream out = isSuccess ? System.out : System.err;
        out.print("\tshould ");
        out.print(specification.getDescription());
        if (!isSuccess) {
            out.print("[");
            out.print(specification.getMessage());
            out.print("]");
        }
        out.println();
    }

}
