package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.Result;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import com.insightfullogic.lambdabehave.impl.reports.SuiteReport;
import com.insightfullogic.lambdabehave.testcases.running.*;
import org.junit.runner.RunWith;

import java.util.List;

import static com.insightfullogic.lambdabehave.BehaveRunner.runOnly;
import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.impl.reports.Result.ERROR;
import static com.insightfullogic.lambdabehave.impl.reports.Result.FAILURE;
import static com.insightfullogic.lambdabehave.impl.reports.Result.SUCCESS;

@RunWith(JunitSuiteRunner.class)
public class TestingRunningSpec {{

    describe("lambda behave specifications", it -> {

        it.should("run a suite that it is specified to run", expect -> {
            Report report = runOnly(EmptyExample.class);

            System.out.println("Print something out");

            expect.that(report.getSuites()).contains(new SuiteReport("An empty suite"));
        });

        it.should("run a single spec1 that passes", expect -> {
            Report report = runOnly(SingleExample.class);

            SuiteReport suite = new SuiteReport("a one spec1 suite");
            suite.with(SpecificationReport.success("have a single spec1"));

            expect.that(report.getSuites()).contains(suite);
        });

        it.should("run a single spec1 that fails", expect -> {
            Report report = runOnly(SingleFailingExample.class);

            SuiteReport suite = new SuiteReport("a one spec1 suite that fails");
            suite.with(new SpecificationReport("have a single failing spec1", FAILURE, "\n" +
                    "Expected: is <false>\n" +
                    "     but: was <true>", null));

            expect.that(report.getSuites()).contains(suite);
        });

        it.should("run a single spec1 that errors", expect -> {
            Report report = runOnly(SingleErrorExample.class);

            SuiteReport suite = new SuiteReport("a one spec1 suite that errors");
            suite.with(new SpecificationReport("have a single erroring spec1", ERROR, "EPIC FAIL", null));

            expect.that(report.getSuites()).contains(suite);
        });

        it.should("describe a spec1 that errors after passing asserts as erroring", expect -> {
            Report report = runOnly(PassThenErrorExample.class);

            SuiteReport suite = new SuiteReport("a pass then error suite");
            suite.with(new SpecificationReport("pass then error", ERROR, "EPIC FAIL", null));

            expect.that(report.getSuites()).contains(suite);
        });

        it.should("disallow tests with duplicate names", expect -> {
            Report report = runOnly(DuplicateName.class);

            List<SpecificationReport> specifications = report.getSuite().getSpecifications();

            expect.that(specifications).hasSize(1);
            expect.that(specifications.get(0).getResult()).is(ERROR);
        });

        it.should("allow multiple describe calls within a test class", expect -> {
            List<SuiteReport> suites = runOnly(MultipleDescribesInAClass.class).getSuites();

            expect.that(suites).hasSize(2);
        });

        it.should("reinstantiate classes every time in order to ensure clean state", expect -> {
            SuiteReport suite = runOnly(Reinstantiation.class).getSuite();

            List<SpecificationReport> specifications = suite.getSpecifications();
            expect.that(specifications.get(0).getResult()).is(SUCCESS);
            expect.that(specifications.get(1).getResult()).is(SUCCESS);
        });

    });

}}
