package com.insightfullogic.lambdabehave.generated;

import com.insightfullogic.lambdabehave.BehaveRunner;
import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import com.insightfullogic.lambdabehave.testcases.generated.GeneratedExample;
import org.junit.runner.RunWith;

import java.util.List;

import static com.insightfullogic.lambdabehave.Suite.describe;

@RunWith(JunitSuiteRunner.class)
public class TestGenerationSpec {{

    describe("testcase generation", it -> {

        it.should("run both generated testcases and explicit values if both are specified", expect -> {
            Report report = BehaveRunner.runOnly(0L, GeneratedExample.class);

            List<SpecificationReport> spec = report.getSuite().getSpecifications();

            expect.that(spec).hasSize(2);
            expect.that(spec.get(0).getDescription()).is("0: 0 (seed: 0)");
            expect.that(spec.get(1).getDescription()).is("1: 20 (seed: 0)");
        });

    });

}}
