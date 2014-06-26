package com.insightfullogic.lambdabehave.example.random_trees;

public class Branch<T> extends Node<T> {

    private final Node<T> left;
    private final Node<T> right;

    public Branch(T value, Node<T> left, Node<T> right) {
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
    public void print(int depth) {
        super.print(depth);
        depth++;
        left.print(depth);
        right.print(depth);
    }

}
