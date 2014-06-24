package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.expectations.Expect;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import com.insightfullogic.lambdabehave.testcases.exceptions.ExceptionInCompleter;
import com.insightfullogic.lambdabehave.testcases.exceptions.ExceptionInInitializer;
import com.insightfullogic.lambdabehave.testcases.exceptions.ExceptionInSetup;
import com.insightfullogic.lambdabehave.testcases.exceptions.ExceptionInTearDown;
import org.junit.runner.RunWith;

import java.util.List;

import static com.insightfullogic.lambdabehave.BehaveRunner.runOnly;
import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.impl.reports.SpecificationReport.error;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(JunitSuiteRunner.class)
public class ExceptionSpec {{

    describe("lambda behave", it -> {

        it.should("not execute a spec when there were exceptions in its shouldSetup()", expect -> {
            Report report = runOnly(ExceptionInSetup.class);

            expectSingleSpecErrored(expect, report);
            verifyNoMoreInteractions(ExceptionInSetup.spec);
        });

        it.should("not execute a suite when there were exceptions in its shouldInitialize()", expect -> {
            Report report = runOnly(ExceptionInInitializer.class);

            expect.that(report.getSuites()).hasSize(1);

            List<SpecificationReport> specifications = report.getSuites().get(0).getSpecifications();
            expect.that(specifications).hasItem(error("initializer", new RuntimeException()));
        });

        it.should("fail a spec when there were exceptions in its shouldTearDown()", expect -> {
            Report report = runOnly(ExceptionInTearDown.class);

            expectSingleSpecErrored(expect, report);
        });

        it.should("fail a suite when there were exceptions in its shouldComplete()", expect -> {
            Report report = runOnly(ExceptionInCompleter.class);

            expect.that(report.getSuites()).hasSize(1);

            List<SpecificationReport> specifications = report.getSuites().get(0).getSpecifications();
            expect.that(specifications).hasItem(error("completer", new RuntimeException()));
        });

    });
}

    private void expectSingleSpecErrored(Expect expect, Report report) {
        expect.that(report.getSuites()).hasSize(1);
        List<SpecificationReport> specifications = report.getSuites().get(0).getSpecifications();
        expect.that(specifications).contains(error("have a single spec1", new RuntimeException()));
    }
}
