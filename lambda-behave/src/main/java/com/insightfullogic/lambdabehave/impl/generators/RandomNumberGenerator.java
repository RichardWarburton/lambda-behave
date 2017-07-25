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
    public int generateInt(final int maxValue) {
        return random.nextInt(maxValue);
    }

    @Override
    public int generateInt(int maxValue, int mod2) {
        if (mod2 == 0) {
            return random.nextInt(maxValue / 2) * 2;
        }
        return random.nextInt(maxValue / 2) * 2 + 1;
    }

    @Override
    public long getSeed() {
        return seed;
    }
}
