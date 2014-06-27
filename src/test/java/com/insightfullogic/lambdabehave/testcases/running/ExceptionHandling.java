package com.insightfullogic.lambdabehave.testcases.running;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class ExceptionHandling {{
    describe("a suite full of exceptions", it -> {
        it.should("pass if exceptions thrown are expected", expect -> {
            expect.exception(Exception.class, () -> {
                throw new Exception();
            });
        });

        it.should("fail if exceptions are expected but not thrown", expect -> {
            expect.exception(Exception.class, () -> {

            });
        });

        it.should("pass if exceptions of a sub class are expected", expect -> {
            expect.exception(Exception.class, () -> {
                throw new RuntimeException();
            });
        });

        it.should("fail if exceptions of a different type are expected", expect -> {
            expect.exception(RuntimeException.class, () -> {
                throw new Exception();
            });
        });
    });
}}
