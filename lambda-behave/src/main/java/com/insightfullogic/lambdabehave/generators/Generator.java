package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.impl.generators.Generators;
import com.insightfullogic.lambdabehave.impl.generators.StringGenerator;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.insightfullogic.lambdabehave.impl.generators.Generators.*;
import static java.lang.Double.longBitsToDouble;
import static java.lang.Float.intBitsToFloat;
import static java.lang.Integer.MAX_VALUE;

/**
 * An interface that defines the API for generating values to be used as testcases. The generator should be able
 * to deterministically generate a value from a given sequence of integers. These are provided by source generators.
 * This allows testcase generation to be deterministically reproduced if a failure occurs. Any source generator that
 *
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
    public static Generator<Integer> integersUpTo(final int maxValue) {
        return source -> source.generateInt(maxValue);
    }
    
    /**
     * Creates a generator that generates positive even integers that are &lt;= maxValue.
     *
     * @param maxValue the upper bound on produced integers
     * @return a generator that generates integers that are &lt;= maxValue
     */
    public static Generator<Integer> evenIntegersUpTo(final int maxValue) {
        return source -> source.generateInt(maxValue, 0);
    }
    
    /**
     * Creates a generator that generates positive odd integers that are &lt;= maxValue.
     *
     * @param maxValue the upper bound on produced integers
     * @return a generator that generates integers that are &lt;= maxValue
     */
    public static Generator<Integer> oddIntegersUpTo(final int maxValue) {
        return source -> source.generateInt(maxValue, 1);
    }

    /**
     * Create a generator for your domain class with one argument.
     *
     * @param constructor the constructor, or a factory function, for your domain class
     * @param firstArgumentGenerator the generator for its first argument
     * @param <V> The type of your domain class
     * @param <F> The type of its first argument
     * @return the generator
     */
    public static <V, F> Generator<V> of(final Function<F, V> constructor, final Generator<F> firstArgumentGenerator) {
        return source -> {
            F argument = firstArgumentGenerator.generate(source);
            return constructor.apply(argument);
        };
    }

    /**
     * Create a generator for your domain class with two arguments.
     *
     * @param constructor the constructor, or a factory function, for your domain class.
     * @param firstArgumentGenerator the generator for its first argument
     * @param secondArgumentGenerator the generator for its second argument
     * @param <V> The type of your domain class
     * @param <F> The type of its first argument
     * @param <S> The type of its second argument
     * @return the generator
     */
    public static <V, F, S> Generator<V> of(
        final BiFunction<F, S, V> constructor,
        final Generator<F> firstArgumentGenerator,
        final Generator<S> secondArgumentGenerator) {
        
        return source -> {
            F first = firstArgumentGenerator.generate(source);
            S second = secondArgumentGenerator.generate(source);
            return constructor.apply(first, second);
        };
    }

    /**
     * Create a generator for your domain class with two arguments.
     *
     * @param constructor the constructor, or a factory function, for your domain class.
     * @param firstArgumentGenerator the generator for its first argument
     * @param secondArgumentGenerator the generator for its second argument
     * @param thirdArgumentGenerator the generator for its third argument
     * @param <V> The type of your domain class
     * @param <F> The type of its first argument
     * @param <S> The type of its second argument
     * @param <T> The type of its third argument
     * @return the generator
     */
    public static <V, F, S, T> Generator<V> of(
        final TriFunction<F, S, T, V> constructor,
        final Generator<F> firstArgumentGenerator,
        final Generator<S> secondArgumentGenerator,
        final Generator<T> thirdArgumentGenerator) {

        return source -> {
            F first = firstArgumentGenerator.generate(source);
            S second = secondArgumentGenerator.generate(source);
            T third = thirdArgumentGenerator.generate(source);
            return constructor.apply(first, second, third);
        };
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

    /**
     * Generate a new value. This may make calls to the SourceGenerator provided in order to generate intermediate
     * values. It may make any number of calls to source, but it should deterministically return the same value back
     * given the same sequence of source numbers
     *
     * @param source the source generator used to produce values
     * @return the new value
     */
    public T generate(SourceGenerator source);

    public default Generator<T> matching(final Predicate<T> predicate) {
        return rng -> {
            Optional<T> candidate = IntStream.range(0, MAX_TRIES)
                                             .mapToObj(i -> generate(rng))
                                             .filter(predicate)
                                             .findFirst();

            return candidate.orElseThrow(Generators::exceededMaxTries);
        };
    }

}
