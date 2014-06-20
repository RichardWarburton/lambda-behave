package com.insightfullogic.lambdabehave.testcases.exceptions;

import com.insightfullogic.lambdabehave.Block;
import com.insightfullogic.lambdabehave.expectations.Expect;

import java.util.function.Consumer;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExceptionInSetup {

    public static final Runnable setup = mock(Runnable.class);
    public static final Consumer<Expect> spec = mock(Consumer.class);

{
    doThrow(RuntimeException.class).when(setup).run();

    describe("a one spec suite", it -> {
        it.shouldSetup(setup::run);
        it.should("have a single spec1", spec::accept);
    });
}}
