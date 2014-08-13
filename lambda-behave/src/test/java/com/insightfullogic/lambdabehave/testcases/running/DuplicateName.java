package com.insightfullogic.lambdabehave.testcases.running;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class DuplicateName {{

    describe("a suite with duplicate names", it -> {

        it.should("have a named spec", expect -> {
        });

        it.should("have a named spec", expect -> {
        });

    });

}}
