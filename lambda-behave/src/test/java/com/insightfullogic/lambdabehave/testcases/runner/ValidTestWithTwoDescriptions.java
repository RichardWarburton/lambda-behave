package com.insightfullogic.lambdabehave.testcases.runner;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class ValidTestWithTwoDescriptions {{

    describe("a thing", it -> {
        it.should("pass)", expect -> {
        });
    });

    describe("another thing", it -> {
        it.should("pass)", expect -> {
        });
    });

}}
