package com.insightfullogic.lambdabehave.fixtures;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class SingleFailingExample {{
    describe("a one spec suite that fails", it -> {
        it.should("have a single failing spec", expect -> {
            expect.that(true).is(false);
        });
    });
}}
