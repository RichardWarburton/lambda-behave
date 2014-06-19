package com.insightfullogic.lambdabehave.examples;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class SingleErrorExample {{
    describe("a one spec suite that errors", it -> {
        it.should("have a single erroring spec", expect -> {
            throw new Exception("EPIC FAIL");
        });
    });
}}
