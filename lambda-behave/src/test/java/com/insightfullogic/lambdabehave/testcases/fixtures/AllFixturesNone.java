package com.insightfullogic.lambdabehave.testcases.fixtures;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.mock;

public class AllFixturesNone {

    public static final Runnable initializer = mock(Runnable.class);
    public static final Runnable completer = mock(Runnable.class);

{
    describe("a no spec suite", it -> {
        it.initializesWith(initializer::run);
        it.completesWith(completer::run);
    });
}}
