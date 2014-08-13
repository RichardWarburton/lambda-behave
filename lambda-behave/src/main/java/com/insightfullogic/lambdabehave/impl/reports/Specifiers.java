package com.insightfullogic.lambdabehave.impl.reports;

import com.insightfullogic.lambdabehave.impl.Specifier;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Maintains a stack of Specifier instances to allow nested runners;
 *
 * This is the one block of global state, which we push Specifiers into
 */
public final class Specifiers {

    private static Deque<Specifier> specifiers;

    static {
        specifiers = new ArrayDeque<>();
    }

    public static int mark() {
        return specifiers.size();
    }

    public static void push(final Specifier specifier) {
        specifiers.push(specifier);
    }

    public static List<Specifier> popSince(int mark) {
        return IntStream.range(mark, specifiers.size())
                        .mapToObj(i -> specifiers.pop())
                        .collect(toList());
    }

}
