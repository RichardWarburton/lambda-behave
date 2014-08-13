package com.insightfullogic.lambdabehave.example.random_trees;

import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;

public class TreeGenerator<T> implements Generator<Node<T>> {

    private final Generator<T> valueGenerator;
    private final int maxTreeDepth;

    public TreeGenerator(final Generator<T> valueGenerator, final int maxTreeDepth) {
        if (maxTreeDepth < 1)
            throw new IllegalArgumentException("A tree needs at least one node");

        this.valueGenerator = valueGenerator;
        this.maxTreeDepth = maxTreeDepth;
    }

    @Override
    public Node<T> generate(final SourceGenerator source) {
        return generateNode(source, maxTreeDepth);
    }

    private Node<T> generateNode(final SourceGenerator source, final int height) {
        if (height == 1 || source.generateBoolean())
            return new Leaf<>(value(source));

        final int parentHeight = height - 1;
        return new Branch<>(value(source), generateNode(source, parentHeight), generateNode(source, parentHeight));
    }

    private T value(final SourceGenerator source) {
        return valueGenerator.generate(source);
    }

}
