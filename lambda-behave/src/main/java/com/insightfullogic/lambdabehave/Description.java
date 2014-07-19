package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.generators.GeneratedDescription;
import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.Specification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * A Description is a fluent builder to describe a
 * complete specification.
 * </p>
 *
 * <p>
 * Most specifications will
 * want to use the 'should' method in order to declare
 * a specification.
 * </p>
 *
 * <p>
 * If you want to declare a data-driven specification
 * then start writing your specification with one of
 * the 'uses' methods.
 * </p>
 *
 * @see com.insightfullogic.lambdabehave.specifications.Specification
 * @see com.insightfullogic.lambdabehave.specifications.Column
 * @see com.insightfullogic.lambdabehave.specifications.TwoColumns
 * @see com.insightfullogic.lambdabehave.specifications.ThreeColumns
 */
public interface Description {

    /**
     * Specify a behaviour.
     *
     * @param description a human readable description of the behaviour you're expecting.
     * @param specification a function which describes in code the expected behaviour.
     */
    void should(String description, Specification specification);

    /**
     * Specify a single value data driven behaviour.
     *
     * @param value the only value to parameterise by
     * @param <T> the type of the value
     * @return a fluent builder for a column of values
     */
    <T> Column<T> uses(T value);

    /**
     * Specify a single value data driven behaviour.
     *
     * @param values a list of values to wrap as a Column
     * @param <T> the type of the value
     * @return a fluent builder for a column of values
     */
    <T> Column<T> uses(List<T> values);

    /**
     * Specify a single value data driven behaviour.
     *
     * @param values a Stream of values to wrap as a Column
     * @param <T> the type of the value
     * @return a fluent builder for a column of values
     */
    <T> Column<T> uses(Stream<T> values);

    /**
     * Specify a two value data driven behaviour.
     *
     * @param first the first value to parameterise by
     * @param second the second value to parameterise by
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @return a fluent builder for two columns of values
     */
    <F, S> TwoColumns<F, S> uses(F first, S second);

    /**
     * Specify a two value data driven behaviour using Lists.
     *
     * @param first the list of values to wrap as the first column
     * @param second the list of values to wrap as the second column
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @return a fluent builder for two columns of values
     */
    <F, S> TwoColumns<F, S> uses(List<F> first, List<S> second);

    /**
     * Specify a two value data driven behaviour using Streams.
     *
     * @param first the Stream of values to wrap as the first column
     * @param second the Stream of values to wrap as the second column
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @return a fluent builder for two columns of values
     */
    <F, S> TwoColumns<F, S> uses(Stream<F> first, Stream<S> second);

    /**
     * Specify a three value data driven behaviour.
     *
     * @param first the first value to parameterise by
     * @param second the second value to parameterise by
     * @param third the third value to parameterise by
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @param <T> the type of the third value
     * @return a fluent builder for two columns of values
     */
    <F, S, T> ThreeColumns<F, S, T> uses(F first, S second, T third);

    /**
     * Specify a three value data driven behaviour using Lists.
     *
     * @param first the list of values to wrap as the first column
     * @param second the list of values to wrap as the second column
     * @param third the list of values to wrap as the third column
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @param <T> the type of the third value
     * @return a fluent builder for two columns of values
     */
    <F, S, T> ThreeColumns<F, S, T> uses(List<F> first, List<S> second, List<T> third);

    /**
     * Specify a three value data driven behaviour using streams.
     *
     * @param first the Stream of values to wrap as the first column
     * @param second the Stream of values to wrap as the second column
     * @param third the Stream of values to wrap as the third column
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @param <T> the type of the third value
     * @return a fluent builder for two columns of values
     */
    <F, S, T> ThreeColumns<F, S, T> uses(Stream<F> first, Stream<S> second, Stream<T> third);

    /**
     * Create a fluent builder to do automatic testcase generation.
     *
     * @param exampleCount the number of example test cases to be generated
     * @return the description builder
     */
    GeneratedDescription requires(int exampleCount);

    /**
     * Run some code before each of the specifications.
     *
     * @param block the code to run.
     */
    void isSetupWith(Block block);

    /**
     * Run some code before all of the specifications.
     *
     * @param block the code to run.
     */
    void initializesWith(Block block);

    /**
     * Run some code after each of the specifications.
     *
     * @param block the code to run.
     */
    void isConcludedWith(Block block);

    /**
     * Run some code after all of the specifications.
     *
     * @param block the code to run.
     */
    void completesWith(Block block);

    /**
     * Creates a mock similar to Mockito.mock which gets reset between tests.
     *
     * @param classToMock the class of the mock object
     * @param <T> the type parameter of the mock object's type
     * @return the mock object
     */
    <T> T usesMock(Class<T> classToMock);

}
