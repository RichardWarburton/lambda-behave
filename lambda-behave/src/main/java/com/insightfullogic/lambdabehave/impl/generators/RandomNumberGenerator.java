package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.SourceGenerator;

import java.util.Random;

public class RandomNumberGenerator implements SourceGenerator {

    private final Random random;
    private final long seed;

    public RandomNumberGenerator() {
        // TODO: investigate more non-determinism in the seed
        this(System.nanoTime());
    }

    public RandomNumberGenerator(final long seed) {
        random = new Random(seed);
        this.seed = seed;
    }

    @Override
    public int generateInt(int maxValue) {
        return random.nextInt(maxValue);
    }

    @Override
    public long getSeed() {
        return seed;
    }
}
