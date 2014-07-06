package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.impl.generators.Generators;
import com.insightfullogic.lambdabehave.impl.generators.StringGenerator;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.insightfullogic.lambdabehave.impl.generators.Generators.ASCII_CHAR_END;
import static com.insightfullogic.lambdabehave.impl.generators.Generators.ASCII_CHAR_START;
import static com.insightfullogic.lambdabehave.impl.generators.Generators.GAP;
import static java.lang.Double.longBitsToDouble;
import static java.lang.Float.intBitsToFloat;
import static java.lang.Integer.MAX_VALUE;

/**
 * A generator
 *
 * @see com.insightfullogic.lambdabehave.generators.SourceGenerator
 *
 * @param <T> the type of value generated
 */
@FunctionalInterface
public interface Generator<T> {

    public static final int MAX_TRIES = 100_000;

    /**
     * Creates a generator that generates java.lang.String instances.
     *
     * @return a generator that generates java.lang.String instances.
     */
    static Generator<String> strings() {
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
        return ng -> longBitsToDouble(Generators.longs(ng));
    }

    /**
     * Creates a generator that generates float instances.
     *
     * @return a generator that generates float instances.
     */
    public static Generator<Float> floats() {
        return ng -> intBitsToFloat(ng.generateInt(MAX_VALUE));
    }

    public T generate(SourceGenerator source);

    public default Generator<T> matching(Predicate<T> predicate) {
        return rng -> {
            Optional<T> candidate = IntStream.range(0, MAX_TRIES)
                                             .mapToObj(i -> generate(rng))
                                             .filter(predicate)
                                             .findFirst();

            return candidate.orElseThrow(() ->
                new IllegalArgumentException("Unable to find matching value in " + MAX_TRIES + " attempts"));
        };
    }

}
