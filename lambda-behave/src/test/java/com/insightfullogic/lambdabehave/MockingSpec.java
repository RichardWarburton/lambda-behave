package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import com.insightfullogic.lambdabehave.testcases.mocking.TwoMocks;
import org.junit.runner.RunWith;

import java.util.List;

import static com.insightfullogic.lambdabehave.BehaveRunner.runOnly;
import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.impl.reports.Result.SUCCESS;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(JunitSuiteRunner.class)
public class MockingSpec {{

    describe("interaction between mocks and lambda behave", it -> {

        it.should("result in mocks created by usesMock() being reset between tests", expect -> {
            Report report = runOnly(TwoMocks.class);


            List<SpecificationReport> specifications = report.getSuite().getSpecifications();

            expect.that(specifications).hasSize(2);
            expect.that(specifications.get(0).getResult()).is(SUCCESS);
            expect.that(specifications.get(1).getResult()).is(SUCCESS);

            verifyNoMoreInteractions(TwoMocks.runnable, TwoMocks.otherRunnable);
        });

    });

}}
