package com.insightfullogic.lambdabehave.testcases.running;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class PassThenErrorExample {{
    describe("a pass then error suite", it -> {
        it.should("pass then error", expect -> {
            expect.that(true).is(true);
            throw new Exception("EPIC FAIL");
        });
    });
}}
