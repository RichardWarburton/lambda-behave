package com.insightfullogic.lambdabehave.example.random_trees;

import java.util.stream.IntStream;

public abstract class Node<T> {

    private final T value;

    Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void print(int depth) {
        tabIn(depth);
        System.out.println(getValue());
    }

    protected void tabIn(int depth) {
        IntStream.range(0, depth).forEach(i -> System.out.print('\t'));
    }

}
