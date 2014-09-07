package com.insightfullogic.lambdabehave.testcases.exceptions;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.hamcrest.Matchers.is;

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

        it.should("pass if an AssertionError is expected", expect -> {
            expect.exception(AssertionError.class, () -> {
                throw new AssertionError();
            });
        });

        it.should("fail if exceptions of a different type are expected", expect -> {
            expect.exception(RuntimeException.class, () -> {
                throw new Exception();
            });
        });

        it.should("pass if exception message is the same as expected", expect -> {
            expect.exception(Exception.class, () -> {
                throw new Exception("exception message");
            }).hasProperty("message", is("exception message"));
        });

        it.should("fail if exception message is different than expected", expect -> {
            expect.exception(Exception.class, () -> {
                throw new Exception("exception message");
            }).hasProperty("message", is("different message"));
        });
    });
}}
