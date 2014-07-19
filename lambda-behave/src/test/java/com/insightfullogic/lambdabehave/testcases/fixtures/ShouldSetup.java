package com.insightfullogic.lambdabehave.testcases.fixtures;

import com.insightfullogic.lambdabehave.expectations.Expect;

import java.util.function.Consumer;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.mock;

public class ShouldSetup {

    public static final Runnable setup = mock(Runnable.class);
    public static final Consumer<Expect> spec = mock(Consumer.class);
    public static final Runnable tearDown = mock(Runnable.class);

{
    describe("a one spec1 suite", it -> {
        it.isSetupWith(setup::run);
        it.should("have a single spec1", spec::accept);
        it.isConcludedWith(tearDown::run);
    });
}}
