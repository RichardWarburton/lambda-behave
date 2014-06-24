package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.specifications.Column;
import com.insightfullogic.lambdabehave.specifications.Specification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumns;
import com.insightfullogic.lambdabehave.specifications.TwoColumns;

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
 */
public interface Description {

    /**
     * Specify a behaviour.
     *
     * @param description a human readable description of the behaviour you're expecting.
     * @param specification a function which describes in code the expected behaviour.
     */
    public void should(String description, Specification specification);

    /**
     * Specify a single value data driven behaviour.
     *
     * @param value the only value to parameterise by
     * @param <T> the type of the value
     * @return a fluent builder for a column of values
     */
    public <T> Column<T> uses(T value);

    /**
     * Specify a two value data driven behaviour.
     *
     * @param first the first value to parameterise by
     * @param second the second value to parameterise by
     * @param <F> the type of the first value
     * @param <S> the type of the second value
     * @return a fluent builder for two columns of values
     */
    public <F, S> TwoColumns<F, S> uses(F first, S second);

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
    public <F, S, T> ThreeColumns<F, S, T> uses(F first, S second, T third);

    /**
     * Run some code before each of the specifications.
     *
     * @param block the code to run.
     */
    public void shouldSetup(Block block);

    /**
     * Run some code before all of the specifications.
     *
     * @param block the code to run.
     */
    public void shouldInitialize(Block block);

    /**
     * Run some code after each of the specifications.
     *
     * @param block the code to run.
     */
    public void shouldTearDown(Block block);

    /**
     * Run some code after all of the specifications.
     *
     * @param block the code to run.
     */
    public void shouldComplete(Block block);

}
