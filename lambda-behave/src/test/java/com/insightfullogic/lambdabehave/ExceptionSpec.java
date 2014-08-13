package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.expectations.Expect;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import com.insightfullogic.lambdabehave.testcases.exceptions.ExceptionInCompleter;
import com.insightfullogic.lambdabehave.testcases.exceptions.ExceptionInInitializer;
import com.insightfullogic.lambdabehave.testcases.exceptions.ExceptionInSetup;
import com.insightfullogic.lambdabehave.testcases.exceptions.ExceptionInTearDown;
import com.insightfullogic.lambdabehave.testcases.running.ExceptionHandling;
import org.junit.runner.RunWith;

import java.util.List;

import static com.insightfullogic.lambdabehave.BehaveRunner.runOnly;
import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.impl.reports.SpecificationReport.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(JunitSuiteRunner.class)
public class ExceptionSpec {{

    describe("lambda behave", it -> {

        it.should("not execute a spec when there were exceptions in its isSetupWith()", expect -> {
            Report report = runOnly(ExceptionInSetup.class);

            expectSingleSpecErrored(expect, report);
            verifyNoMoreInteractions(ExceptionInSetup.spec);
        });

        it.should("not execute a suite when there were exceptions in its initializesWith()", expect -> {
            Report report = runOnly(ExceptionInInitializer.class);

            List<SpecificationReport> specifications = report.getSuite().getSpecifications();
            expect.that(specifications).hasItem(error("initializer", new RuntimeException()));
        });

        it.should("fail a spec when there were exceptions in its isConcludedWith()", expect -> {
            Report report = runOnly(ExceptionInTearDown.class);

            expectSingleSpecErrored(expect, report);
        });

        it.should("fail a suite when there were exceptions in its completesWith()", expect -> {
            Report report = runOnly(ExceptionInCompleter.class);

            List<SpecificationReport> specifications = report.getSuite().getSpecifications();
            expect.that(specifications).hasItem(error("completer", new RuntimeException()));
        });

        it.should("be able to expect exceptions from specifications", expect -> {
            Report report = runOnly(ExceptionHandling.class);

            List<SpecificationReport> specifications = report.getSuite().getSpecifications();

            AssertionError noException = new AssertionError(
                "Expected exception: java.lang.Exception, but no exception was thrown");

            AssertionError wrongException = new AssertionError(
                "Expected exception: java.lang.RuntimeException, but java.lang.Exception was thrown");

            expect.that(specifications)
                .hasItem(success("pass if exceptions thrown are expected"))
                .hasItem(failure("fail if exceptions are expected but not thrown", noException))
                .hasItem(success("pass if an AssertionError is expected"))
                .hasItem(success("pass if exceptions of a sub class are expected"))
                .hasItem(failure("fail if exceptions of a different type are expected", wrongException));
        });
    });
}

    private void expectSingleSpecErrored(final Expect expect, final Report report) {
        List<SpecificationReport> specifications = report.getSuite().getSpecifications();
        expect.that(specifications).contains(error("have a single spec1", new RuntimeException()));
    }
}
