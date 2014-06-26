package com.insightfullogic.lambdabehave.testcases.running;

import com.insightfullogic.lambdabehave.Suite;

public class FailureCase {{
    Suite.describe("A failing suite", it -> {
        it.should("fail", expect -> {
            expect.failure("FAIL");
        });
    });
}}
