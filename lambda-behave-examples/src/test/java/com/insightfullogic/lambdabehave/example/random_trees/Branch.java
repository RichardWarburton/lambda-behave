package com.insightfullogic.lambdabehave.example.random_trees;

public class Branch<T> extends Node<T> {

    private final Node<T> left;
    private final Node<T> right;

    public Branch(final T value, final Node<T> left, final Node<T> right) {
        super(value);
        this.left = left;
        this.right = right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    @Override
    public void print(final int depth) {
        super.print(depth);
        final int childDepth = depth + 1;
        left.print(childDepth);
        right.print(childDepth);
    }

}
