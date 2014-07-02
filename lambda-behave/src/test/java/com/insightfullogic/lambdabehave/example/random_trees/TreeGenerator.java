package com.insightfullogic.lambdabehave.example.random_trees;

import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;

public class TreeGenerator<T> implements Generator<Node<T>> {

    private final Generator<T> valueGenerator;
    private final int maxTreeDepth;

    public TreeGenerator(Generator<T> valueGenerator, int maxTreeDepth) {
        if (maxTreeDepth < 1)
            throw new IllegalArgumentException("A tree needs at least one node");

        this.valueGenerator = valueGenerator;
        this.maxTreeDepth = maxTreeDepth;
    }

    @Override
    public Node<T> generate(SourceGenerator source) {
        return generateNode(source, maxTreeDepth);
    }

    private Node<T> generateNode(SourceGenerator source, int height) {
        if (height == 1 || source.generateBoolean())
            return new Leaf<T>(value(source));

        height--;
        return new Branch<T>(value(source), generateNode(source, height), generateNode(source, height));
    }

    private T value(SourceGenerator source) {
        return valueGenerator.generate(source);
    }

}
