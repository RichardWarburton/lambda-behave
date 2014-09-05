package com.insightfullogic.lambdabehave.generated;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.generators.Generator.integersUpTo;
import static com.insightfullogic.lambdabehave.generators.Generator.of;

@RunWith(JunitSuiteRunner.class)
public class GeneratorFactoryExample {{

    describe("an example of testcase generation", it -> {
        it.requires(5)
          .example(of((x, y) -> x + y, integersUpTo(5), integersUpTo(5)))
          .toShow("%d ", (expect, value) -> {
              expect.that(value).isLessThan(10)
                                .isGreaterThanOrEqualTo(0);
          });

        // For comparison
        it.requires(5)
          .example(integersUpTo(5), integersUpTo(5))
          .toShow("%d %d", (expect, x, y) -> {
              int value = x + y;
              expect.that(value).isLessThan(10)
                    .isGreaterThanOrEqualTo(0);
          });
    });

}}
