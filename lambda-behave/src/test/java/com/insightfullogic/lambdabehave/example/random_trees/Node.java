package com.insightfullogic.lambdabehave.example.random_trees;

import java.util.stream.IntStream;

public abstract class Node<T> {

    private final T value;

    Node(final T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void print(final int depth) {
        tabIn(depth);
        System.out.println(getValue());
    }

    protected void tabIn(final int depth) {
        IntStream.range(0, depth).forEach(i -> System.out.print('\t'));
    }

}
