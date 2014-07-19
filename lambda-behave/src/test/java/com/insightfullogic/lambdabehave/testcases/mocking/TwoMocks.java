package com.insightfullogic.lambdabehave.testcases.mocking;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TwoMocks {

    public static Runnable runnable;
    public static Runnable otherRunnable;

    {

    describe("a test with a single mock", it -> {

        runnable = it.usesMock(Runnable.class);
        otherRunnable = it.usesMock(Runnable.class);

        it.should("run only once ever", expect -> {
            runnable.run();
            otherRunnable.run();

            verify(runnable, times(1)).run();
            verify(otherRunnable, times(1)).run();
        });

        it.should("run only once ever, despite other calls", expect -> {
            runnable.run();
            otherRunnable.run();

            verify(runnable, times(1)).run();
            verify(otherRunnable, times(1)).run();
        });

    });

}}
