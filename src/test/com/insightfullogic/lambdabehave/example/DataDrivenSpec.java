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

    describe("a pair of numbers", it -> {
        it.uses(2, 4)
          .and(4, 8)
          .toShow("%i / %i is two", (expect, x, y) -> {
              expect.that(y / x).is(2);
          });
    });

    describe("three numbers", it -> {
        it.uses(1, 2, 3)
          .and(4, 5, 6)
          .toShow("%i %i and %i are a sequence", (expect, x, y, z) -> {
              expect.that(x + 1).is(y);
              expect.that(y + 1).is(z);
          });
    });

}}
