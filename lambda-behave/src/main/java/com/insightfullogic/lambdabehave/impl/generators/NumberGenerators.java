package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.SourceGenerator;

import java.util.ArrayDeque;
import java.util.Deque;

public final class NumberGenerators {

    private static final Deque<SourceGenerator> generators = new ArrayDeque<>();

    public static SourceGenerator peek() {
        return generators.peek();
    }

    public static void push(final SourceGenerator generator) {
        generators.push(generator);
    }

    public static SourceGenerator pop() {
        return generators.pop();
    }

}
