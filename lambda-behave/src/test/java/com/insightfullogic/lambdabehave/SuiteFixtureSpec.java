package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import com.insightfullogic.lambdabehave.testcases.fixtures.AllFixturesMulti;
import com.insightfullogic.lambdabehave.testcases.fixtures.AllFixturesNone;
import com.insightfullogic.lambdabehave.testcases.running.FailureCase;
import org.junit.runner.RunWith;
import org.mockito.InOrder;

import static com.insightfullogic.lambdabehave.BehaveRunner.runOnly;
import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(JunitSuiteRunner.class)
public class SuiteFixtureSpec {{

    describe("lambda behave suite fixtures", it -> {

        it.should("not execute initializesWith() or completesWith() when there are no specs", expect -> {
            runOnly(AllFixturesNone.class);

            verifyZeroInteractions(AllFixturesNone.initializer, AllFixturesNone.completer);
        });

        it.should("execute initializesWith() before a suite and completesWith() after a suite", expect -> {
            runOnly(AllFixturesMulti.class);

            InOrder inOrder = inOrder(AllFixturesMulti.setup, AllFixturesMulti.spec1, AllFixturesMulti.spec2,
                    AllFixturesMulti.tearDown, AllFixturesMulti.initializer, AllFixturesMulti.completer);

            inOrder.verify(AllFixturesMulti.initializer).run();

            inOrder.verify(AllFixturesMulti.setup).run();
            inOrder.verify(AllFixturesMulti.spec1).accept(any());
            inOrder.verify(AllFixturesMulti.tearDown).run();

            inOrder.verify(AllFixturesMulti.setup).run();
            inOrder.verify(AllFixturesMulti.spec2).accept(any());
            inOrder.verify(AllFixturesMulti.tearDown).run();

            inOrder.verify(AllFixturesMulti.completer).run();

            inOrder.verifyNoMoreInteractions();
        });

        it.should("Fail a spec when failure is called", expect -> {
            Report report = runOnly(FailureCase.class);

            SpecificationReport spec = SpecificationReport.failure("fail", new AssertionError("FAIL"));
            expect.that(report.getSuites().get(0).getSpecifications()).contains(spec);
        });

    });

}}
