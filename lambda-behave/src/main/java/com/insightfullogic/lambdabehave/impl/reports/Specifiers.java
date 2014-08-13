package com.insightfullogic.lambdabehave.impl.reports;

import com.insightfullogic.lambdabehave.impl.Specifier;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Maintains a stack of Specifier instances to allow nested runners;
 */
public final class Specifiers {

    private static ThreadLocal<Deque<Specifier>> specifiers;

    static {
        specifiers = ThreadLocal.withInitial(ArrayDeque::new);
    }

    public static void push(final Specifier specifier) {
        specifiers().push(specifier);
    }

    public static Specifier pop() {
        return specifiers().pop();
    }

    private static Deque<Specifier> specifiers() {
        return specifiers.get();
    }

}
