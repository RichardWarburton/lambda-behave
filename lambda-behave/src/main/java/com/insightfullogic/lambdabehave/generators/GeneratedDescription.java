package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

/**
 * A fluent builder interface for describing how test cases get generated.
 */
public interface GeneratedDescription {

    /**
     * Set a new source generator.
     *
     * The source generator is the component which provides a source of numbers, upon which random test
     * case generation is performed.
     *
     * If not set the default will generate random numbers.
     *
     * @param sourceGenerator the new source generator
     * @return this
     */
    GeneratedDescription withSource(SourceGenerator sourceGenerator);

    /**
     * Use this generator to produce a single column of example testcases.
     *
     * @param generator the generator to use to produce the test case values
     * @param <T> the type of the values in column
     * @return this
     */
    <T> Column<T> example(Generator<T> generator);

    /**
     * Use these generators to produce two columns of example testcases.
     *
     * @param firstGenerator the generator to use to produce the first column of test case values
     * @param secondGenerator the generator to use to produce the second column of test case values
     * @param <F> the type of the values in the first column
     * @param <S> the type of the values in the second column
     * @return this
     */
    <F, S> TwoColumns<F, S> example(
            Generator<F> firstGenerator,
            Generator<S> secondGenerator);

    /**
     * Use these generators to produce three columns of example testcases.
     *
     * @param firstGenerator the generator to use to produce the first column of test case values
     * @param secondGenerator the generator to use to produce the second column of test case values
     * @param thirdGenerator the generator to use to produce the third column of test case values
     * @param <F> the type of the values in the first column
     * @param <S> the type of the values in the second column
     * @param <T> the type of the values in the third column
     * @return this
     */
    <F, S, T> ThreeColumns<F, S, T> example(
            Generator<F> firstGenerator,
            Generator<S> secondGenerator,
            Generator<T> thirdGenerator);

}
