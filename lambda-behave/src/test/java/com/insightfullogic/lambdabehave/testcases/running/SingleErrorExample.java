package com.insightfullogic.lambdabehave.testcases.running;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class SingleErrorExample {{
    describe("a one spec1 suite that errors", it -> {
        it.should("have a single erroring spec1", expect -> {
            throw new Exception("EPIC FAIL");
        });
    });
}}
