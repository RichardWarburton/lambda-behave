package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.NumberGenerator;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    private final Random random;
    private final long seed;

    public RandomNumberGenerator() {
        seed = System.nanoTime();
        random = new Random(seed);
    }

    @Override
    public int generateInt(int maxValue) {
        return random.nextInt(maxValue);
    }

    @Override
    public String getSeed() {
        return String.valueOf(seed);
    }
}
