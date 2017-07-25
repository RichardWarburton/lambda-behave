package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.impl.generators.RandomNumberGenerator;
import com.insightfullogic.lambdabehave.impl.generators.ValueSourceGenerator;

/**
 * A source of information which generators use. Generators should
 * deterministically generate test cases based upon the source
 * generator.
 *
 * @see com.insightfullogic.lambdabehave.generators.Generator
 */
public interface SourceGenerator {

    /**
     * creates a source generator that returns random numbers.
     *
     * If you want to reproduce this sequence of numbers from a given seed
     * then see the {@link #deterministicNumbers(long) deterministicNumbers}
     * method.
     *
     * @return the source generator
     */
    public static SourceGenerator randomNumbers() {
        return new RandomNumberGenerator();
    }

    /**
     * creates a source generator that returns numbers deterministically from a seed.
     *
     * The number generation mechanism is guaranteed to reproduce the same sequence of numbers as
     * {@link #randomNumbers() randomNumbers} given a seed. This means that you can reproduce
     * errors in property based tests by looking at the generated seed in the log and using this
     * source generator.
     *
     * @param seed the seed for the random number generator
     * @return the source generator
     */
    public static SourceGenerator deterministicNumbers(final long seed) {
        return new RandomNumberGenerator(seed);
    }

    /**
     * Generates a source from a sequence of values
     *
     * @param values the values to use
     * @return the source generator
     */
    public static SourceGenerator values(final int ... values) {
        return new ValueSourceGenerator(values);
    }

    /**
     * Generate an int which is &lt;= maxValue.
     *
     * @param maxValue the maximum value of the int
     * @return the generated int
     */
    int generateInt(int maxValue);
    
    /**
     * Generate an odd/even int which is &lt;= maxValue.
     *
     * @param maxValue the maximum value of the int
     * @param mod2 suggests whether generated value should be odd/even
     * @return the generated int
     */
    int generateInt(int maxValue, int mod2);

    /**
     * Gets the seed state required to generate the sequence of numbers.
     *
     * @return the seed required to generate the sequence of numbers
     */
    long getSeed();

    /**
     * Generate a boolean value, based upon the current source
     * of information.
     *
     * @return either true or false
     */
    default boolean generateBoolean() {
        return generateInt(2) == 0;
    }

}
