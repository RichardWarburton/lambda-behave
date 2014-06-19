package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.testcases.running.*;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import com.insightfullogic.lambdabehave.impl.reports.SuiteReport;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.BehaveRunner.runOnly;
import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.impl.reports.Result.ERROR;
import static com.insightfullogic.lambdabehave.impl.reports.Result.FAILURE;

@RunWith(JunitSuiteRunner.class)
public class TestingRunningSpec {{

    describe("lambda behave specifications", it -> {

        it.should("run a suite that it is specified to run", expect -> {
            Report report = runOnly(EmptyExample.class);

            expect.that(report.getSuites()).contains(new SuiteReport("An empty suite"));
        });

        it.should("run a single spec that passes", expect -> {
            Report report = runOnly(SingleExample.class);

            SuiteReport suite = new SuiteReport("a one spec suite");
            suite.add(new SpecificationReport("have a single spec"));

            expect.that(report.getSuites()).contains(suite);
        });

        it.should("run a single spec that fails", expect -> {
            Report report = runOnly(SingleFailingExample.class);

            SuiteReport suite = new SuiteReport("a one spec suite that fails");
            suite.add(new SpecificationReport("have a single failing spec", FAILURE, "\n" +
                    "Expected: is <false>\n" +
                    "     but: was <true>"));

            expect.that(report.getSuites()).contains(suite);
        });

        it.should("run a single spec that errors", expect -> {
            Report report = runOnly(SingleErrorExample.class);

            SuiteReport suite = new SuiteReport("a one spec suite that errors");
            suite.add(new SpecificationReport("have a single erroring spec", ERROR, "EPIC FAIL"));

            expect.that(report.getSuites()).contains(suite);
        });

        it.should("describe a spec that errors after passing asserts as erroring", expect -> {
            Report report = runOnly(PassThenErrorExample.class);

            SuiteReport suite = new SuiteReport("a pass then error suite");
            suite.add(new SpecificationReport("pass then error", ERROR, "EPIC FAIL"));

            expect.that(report.getSuites()).contains(suite);
        });

    });

}}
