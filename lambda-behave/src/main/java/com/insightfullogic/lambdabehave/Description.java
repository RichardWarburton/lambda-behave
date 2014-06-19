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
     * @param description
     * @param specification
     */
    public void should(String description, Specification specification);

    /**
     * Specify a single value data driven behaviour.
     *
     * @param value
     * @param <T>
     * @return
     */
    public <T> Column<T> uses(T value);

    /**
     * Specify a two value data driven behaviour.
     *
     * @param first
     * @param second
     * @param <F>
     * @param <S>
     * @return
     */
    public <F, S> TwoColumns<F, S> uses(F first, S second);

    /**
     * Specify a three value data driven behaviour.
     *
     * @param first
     * @param second
     * @param third
     * @param <F>
     * @param <S>
     * @param <T>
     * @return
     */
    public <F, S, T> ThreeColumns<F, S, T> uses(F first, S second, T third);

    /**
     * Run some code before each of the specifications.
     *
     * @param block the code to run.
     */
    public void shouldSetup(Runnable block);

    /**
     * Run some code before all of the specifications.
     *
     * @param block the code to run.
     */
    public void shouldInitialize(Runnable block);

    /**
     * Run some code after each of the specifications.
     *
     * @param block the code to run.
     */
    public void shouldTearDown(Runnable block);

    /**
     * Run some code after all of the specifications.
     *
     * @param block the code to run.
     */
    public void shouldComplete(Runnable block);

}
