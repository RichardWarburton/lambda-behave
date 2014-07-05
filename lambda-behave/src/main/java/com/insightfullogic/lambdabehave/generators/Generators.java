package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.impl.generators.StringGenerator;

import static java.lang.Double.longBitsToDouble;
import static java.lang.Float.intBitsToFloat;
import static java.lang.Integer.MAX_VALUE;

/**
 * Class to provide standard generators for core
 * Java types.
 *
 * @see com.insightfullogic.lambdabehave.generators.Generator
 */
public final class Generators {

    private static final char ASCII_CHAR_START = ' ';
    private static final char ASCII_CHAR_END = '~';
    private static final int GAP = ASCII_CHAR_END - ASCII_CHAR_START;

    private Generators() {}

    /**
     * Creates a generator that generates java.lang.String instances.
     *
     * @return a generator that generates java.lang.String instances.
     */
    public static Generator<String> strings() {
        return new StringGenerator(ASCII_CHAR_START, Character.MAX_VALUE);
    }

    /**
     * Creates a generator that generates java.lang.String instances
     * that only contain ascii text characters.
     *
     * @return a generator that generates ascii java.lang.String instances.
     */
    public static Generator<String> asciiStrings() {
        return new StringGenerator(ASCII_CHAR_START, ASCII_CHAR_END);
    }

    /**
     * Creates a generator that generates ascii characters
     *
     * @return a generator that generates ascii characters
     */
    public static Generator<Character> asciiCharacters() {
        return source -> {
            int index = source.generateInt(GAP);
            return (char) (ASCII_CHAR_START + index);
        };
    }

    /**
     * Creates a generator that generates integers that are &lt;= maxValue.
     *
     * @param maxValue the upper bound on produced integers
     * @return a generator that generates integers that are &lt;= maxValue
     */
    public static Generator<Integer> integersUpTo(int maxValue) {
        return ng -> ng.generateInt(maxValue);
    }

    /**
     * Creates a generator that generates long instances.
     *
     * @return a generator that generates long instances.
     */
    public static Generator<Long> longs() {
        return Generators::longs;
    }

    /**
     * Creates a generator that generates double instances.
     *
     * @return a generator that generates double instances.
     */
    public static Generator<Double> doubles() {
        return ng -> longBitsToDouble(longs(ng));
    }

    /**
     * Creates a generator that generates float instances.
     *
     * @return a generator that generates float instances.
     */
    public static Generator<Float> floats() {
        return ng -> intBitsToFloat(ng.generateInt(MAX_VALUE));
    }

    private static long longs(SourceGenerator ng) {
        return ((long) ng.generateInt(MAX_VALUE)) << 32 + ng.generateInt(MAX_VALUE);
    }

}
