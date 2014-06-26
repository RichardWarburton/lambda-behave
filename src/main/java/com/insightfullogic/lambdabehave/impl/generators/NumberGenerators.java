package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.NumberGenerator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

public final class NumberGenerators {

    public static NumberGenerator makeDefault() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        return max -> random.nextInt(max);
    }

    private static final Deque<NumberGenerator> generators = new ArrayDeque<>();

    public static NumberGenerator peek() {
        return generators.peek();
    }

    public static void push(NumberGenerator generator) {
        generators.push(generator);
    }

    public static NumberGenerator pop() {
        return generators.pop();
    }

}
