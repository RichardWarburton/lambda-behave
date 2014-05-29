package com.insightfullogic.lambdabehave.example;

import com.insightfullogic.lambdabehave.JunitBehaveRunner;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.Lets.describe;

/**
 * .
 */
@RunWith(JunitBehaveRunner.class)
public class DataDrivenSpec {{

    describe("a number", it -> {
        it.uses(2)
          .and(4)
          .toShow("%i is even if divisible by two", (expect, x) -> {
              expect.that(x % 2).is(0);
          });
    });

}}
