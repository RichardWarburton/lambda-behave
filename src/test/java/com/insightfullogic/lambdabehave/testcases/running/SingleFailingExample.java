package com.insightfullogic.lambdabehave.testcases.running;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class SingleFailingExample {{
    describe("a one spec1 suite that fails", it -> {
        it.should("have a single failing spec1", expect -> {
            expect.that(true).is(false);
        });
    });
}}
