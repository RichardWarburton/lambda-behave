package com.insightfullogic.lambdabehave.generators;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@FunctionalInterface
public interface Generator<T> {

    public static final int MAX_TRIES = 100_000;

    public T generate(NumberGenerator source);

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
