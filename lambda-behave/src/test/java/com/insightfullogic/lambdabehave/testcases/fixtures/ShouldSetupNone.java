package com.insightfullogic.lambdabehave.testcases.fixtures;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.mock;

public class ShouldSetupNone {

    public static final Runnable setup = mock(Runnable.class);
    public static final Runnable tearDown = mock(Runnable.class);

{
    describe("a no spec suite", it -> {
        it.isSetupWith(setup::run);
        it.isConcludedWith(tearDown::run);
    });
}}
