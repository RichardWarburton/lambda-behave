package com.insightfullogic.lambdabehave.testcases.running;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class MultipleDescribesInAClass {{

    describe("the first describe", it -> {
        it.should("have a single test", expect -> {
            expect.that(true).is(true);
        });
    });

    describe("the second describe", it -> {
        it.should("have a single test", expect -> {
            expect.that(true).is(true);
        });
    });

}}
