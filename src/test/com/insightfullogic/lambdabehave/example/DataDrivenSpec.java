package com.insightfullogic.lambdabehave.example;

import com.insightfullogic.lambdabehave.JunitRunner;
import com.insightfullogic.lambdabehave.Lets;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.Lets.describe;

/**
 * .
 */
@RunWith(JunitRunner.class)
public class DataDrivenSpec {{

    describe("a number", it -> {
        it.should("be even if its divisible by two", (expect, x) -> {
            expect.that(x % 2).is(0);
        });
    });

}}
