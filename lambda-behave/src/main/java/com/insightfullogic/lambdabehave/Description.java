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
     * <p>
     *     Specify a behaviour.
     * </p>
     *
     * @param description a human readable description of the behaviour you're expecting.
     * @param specification a function which describes in code the expected behaviour.
     */
    void should(String description, Specification specification);

    /**
     * <p>
     *     Specify a single value data driven behaviour.
     * </p>
     *
     * @param value the only value to parameterise by
     * @param <T> the type of the value
     * @return a fluent builder for a column of values
     */
    <T> Column<T> uses(T value);

    /**
     * <p>
     *     Specify a single value data driven behaviour.
     * </p>
     *
     * @param values a list of values to wrap as a Column
     * @param <T> the type of the value
     * @return a fluent builder for a column of values
     */
    <T> Column<T> uses(List<T> values);

    /**
     * <p>
     *     Specify a single value data driven behaviour.
     * </p>
     *
     * @param values a Stream of values to wrap as a Column
     * @param <T> the type of the value
     * @return a fluent builder for a column of values
     */
    <T> Column<T> uses(Stream<T> values);

    /**
     * <p>
     *     Specify a two value data driven behaviour.
     * </p>
     *
     * @param first the first value to parameterise by
     * @param second the second value to parameterise by
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @return a fluent builder for two columns of values
     */
    <F, S> TwoColumns<F, S> uses(F first, S second);

    /**
     * <p>
     *     Specify a two value data driven behaviour using Lists.
     * </p>
     *
     * @param first the list of values to wrap as the first column
     * @param second the list of values to wrap as the second column
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @return a fluent builder for two columns of values
     */
    <F, S> TwoColumns<F, S> uses(List<F> first, List<S> second);

    /**
     * <p>
     *     Specify a two value data driven behaviour using Streams.
     * </p>
     *
     * @param first the Stream of values to wrap as the first column
     * @param second the Stream of values to wrap as the second column
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @return a fluent builder for two columns of values
     */
    <F, S> TwoColumns<F, S> uses(Stream<F> first, Stream<S> second);

    /**
     * <p>
     *     Specify a three value data driven behaviour.
     * </p>
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
     * <p>
     *     Specify a three value data driven behaviour using Lists.
     * </p>
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
     * <p>
     *     Specify a three value data driven behaviour using streams.
     * </p>
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
     * <p>
     *     Create a fluent builder to do automatic testcase generation.
     * </p>
     *
     * @param exampleCount the number of example test cases to be generated
     * @return the description builder
     */
    GeneratedDescription requires(int exampleCount);

    long getSeed();

    /**
     * <p>
     *     Run some code before each of the specifications.
     * </p>
     *
     * <p>
     *     This performs the same role as the <code>@Before</code> annotation
     * in Junit.
     * </p>
     *
     * @param block the code to run.
     */
    void isSetupWith(Block block);

    /**
     * <p>
     *     Run some code before all of the specifications in the suite.
     * </p>
     *
     * <p>
     *     This performs the same role as the <code>@BeforeClass</code> annotation
     * in Junit.
     * </p>
     *
     * @param block the code to run.
     */
    void initializesWith(Block block);

    /**
     * <p>
     *     Run some code after each of the specifications.
     * </p>
     *
     * <p>
     *     This performs the same role as the <code>@After</code> annotation
     *     in Junit.
     * </p>
     *
     * @param block the code to run.
     */
    void isConcludedWith(Block block);

    /**
     * <p>
     *  Run some code after all of the specifications in the suite.
     * </p>
     *
     * <p>
     *  This performs the same role as the <code>@AfterClass</code> annotation
     *  in Junit.
     * </p>
     *
     * @param block the code to run.
     */
    void completesWith(Block block);

}
