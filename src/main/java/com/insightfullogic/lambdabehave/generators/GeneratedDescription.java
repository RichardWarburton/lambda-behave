package com.insightfullogic.lambdabehave.generators;

/**
 * 
 */
public interface GeneratedDescription {

    GeneratedDescription numberedBy(SourceGenerator sourceGenerator);

    <T> GeneratedColumn<T> example(Generator<T> cls);

    <F, S> GeneratedTwoColumns<F, S> example(
            Generator<F> firstGenerator,
            Generator<S> secondGenerator);

    <F, S, T> GeneratedThreeColumns<F, S, T> example(
            Generator<F> firstGenerator,
            Generator<S> secondGenerator,
            Generator<T> thirdGenerator);

}
