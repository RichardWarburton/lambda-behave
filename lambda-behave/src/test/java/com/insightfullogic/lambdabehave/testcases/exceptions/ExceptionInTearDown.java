package com.insightfullogic.lambdabehave.testcases.exceptions;

import com.insightfullogic.lambdabehave.expectations.Expect;

import java.util.function.Consumer;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ExceptionInTearDown {

    public static final Runnable setup = mock(Runnable.class);
    public static final Consumer<Expect> spec = mock(Consumer.class);
    public static final Runnable tearDown = mock(Runnable.class);

{
    doThrow(RuntimeException.class).when(tearDown).run();

    describe("a one spec suite", it -> {
        it.isSetupWith(setup::run);
        it.should("have a single spec1", spec::accept);
        it.isConcludedWith(tearDown::run);
    });
}}
