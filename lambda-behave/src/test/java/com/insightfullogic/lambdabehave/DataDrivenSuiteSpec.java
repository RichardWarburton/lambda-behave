package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.SuiteReport;
import com.insightfullogic.lambdabehave.testcases.data_driven.*;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.BehaveRunner.runOnly;
import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.impl.reports.SpecificationReport.success;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(JunitSuiteRunner.class)
public class DataDrivenSuiteSpec {{

    describe("data driven specifications", it -> {

        it.uses("streams", (Class) DataDrivenByStream.class, DataDrivenByStream.specification)
          .and("lists", DataDrivenByList.class, DataDrivenByList.specification)
          .toShow("accept %s as data columns", (expect, name, cls, spec) -> {
              Report report = runOnly(0L, cls);

              verify(spec).specifyBehaviour(any(), eq(1));
              verify(spec).specifyBehaviour(any(), eq(2));
              verify(spec).specifyBehaviour(any(), eq(3));

              SuiteReport suite = report.getSuites().get(0);
              expect.that(suite.getSpecifications()).contains(success("0: 1 (seed: 0)"), success("1: 2 (seed: 0)"), success("2: 3 (seed: 0)"));
          });

        it.uses("streams", (Class) TwoColDataDrivenByStream.class, TwoColDataDrivenByStream.specification)
          .and("lists", TwoColDataDrivenByList.class, TwoColDataDrivenByList.specification)
          .toShow("accept %s as a pair of data columns", (expect, name, cls, spec) -> {
              Report report = runOnly(0L, cls);

              verify(spec).specifyBehaviour(any(), eq(1), eq(2));
              verify(spec).specifyBehaviour(any(), eq(2), eq(4));
              verify(spec).specifyBehaviour(any(), eq(3), eq(6));

              SuiteReport suite = report.getSuites().get(0);
              expect.that(suite.getSpecifications()).contains(success("0: 1 2 (seed: 0)"), success("1: 2 4 (seed: 0)"), success("2: 3 6 (seed: 0)"));
          });

        it.uses("streams", (Class) ThreeColDataDrivenByStream.class, ThreeColDataDrivenByStream.specification)
          .and("lists", ThreeColDataDrivenByList.class, ThreeColDataDrivenByList.specification)
          .toShow("accept %s as a triple of data columns", (expect, name, cls, spec) -> {
              Report report = runOnly(0L, cls);

              verify(spec).specifyBehaviour(any(), eq(1), eq(2), eq(3));
              verify(spec).specifyBehaviour(any(), eq(2), eq(4), eq(6));
              verify(spec).specifyBehaviour(any(), eq(3), eq(6), eq(9));

              SuiteReport suite = report.getSuites().get(0);
              expect.that(suite.getSpecifications()).contains(success("0: 1 2 3 (seed: 0)"), success("1: 2 4 6 (seed: 0)"), success("2: 3 6 9 (seed: 0)"));
          });

        it.should("reject lists of different length as a pair of data columns", expect -> {
            try {
                runOnly(TwoColDataDrivenWrongLength.class);
                expect.failure("Should never reach here");
            } catch (IllegalArgumentException e) {
                // should reach here, deliberate fall through
            }

            verifyNoMoreInteractions(TwoColDataDrivenWrongLength.specification);
        });

        it.should("reject lists of different length as a triple of data columns", expect -> {
            try {
                runOnly(ThreeColDataDrivenWrongLength.class);
                expect.failure("Should never reach here");
            } catch (IllegalArgumentException e) {
                // should reach here, deliberate fall through
            }

            verifyNoMoreInteractions(ThreeColDataDrivenWrongLength.specification);
        });
    });

}}
