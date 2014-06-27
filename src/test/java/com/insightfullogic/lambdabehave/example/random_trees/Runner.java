package com.insightfullogic.lambdabehave.example.random_trees;

import com.insightfullogic.lambdabehave.impl.generators.RandomNumberGenerator;

import static com.insightfullogic.lambdabehave.generators.Example.integersUpTo;

public class Runner {

    public static void main(String[] args) {
        final TreeGenerator<Integer> generator = new TreeGenerator<Integer>(integersUpTo(10), 10);

        final Node<Integer> tree = generator.generate(new RandomNumberGenerator());

        tree.print(0);
    }
}
