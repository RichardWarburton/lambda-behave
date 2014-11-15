package com.insightfullogic.lambdabehave.expectations;

import com.insightfullogic.lambdabehave.impl.HasTypesafeProperty;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Collection;
import java.util.function.Function;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BoundExpectation<T> {

    protected final boolean positive;
    protected final T objectUnderTest;

    BoundExpectation(final T value, final boolean positive) {
        this.objectUnderTest = value;
        this.positive = positive;
    }

    public <O> BoundExpectation<O> and(final O value) {
        return new BoundExpectation<O>(value, true);
    }

    public <O> CollectionExpectation<O> and(final Collection<O> collection) {
        return new CollectionExpectation<O>(collection, true);
    }

    public StringExpectation and(final String str) {
        return new StringExpectation(str, true);
    }

    public BoundExpectation<T> isEqualTo(final T expected) {
        return matches(equalTo(expected));
    }

    public BoundExpectation<T> hasToString(final Matcher<? super String> str) {
        return matches(Matchers.hasToString(str));
    }

    public BoundExpectation<T> hasToString(final String str) {
        return matches(Matchers.hasToString(str));
    }

    public BoundExpectation<T> instanceOf(final Class<?> type) {
        return matches(Matchers.instanceOf(type));
    }

    public BoundExpectation<T> isNotNull() {
        return matches(Matchers.notNullValue());
    }

    public BoundExpectation<T> toBeNull() {
        return matches(Matchers.nullValue());
    }

    public BoundExpectation<T> sameInstance(final T target) {
        return matches(Matchers.sameInstance(target));
    }

    public BoundExpectation<T> any() {
        return matches(Matchers.any(getWrappedClass()));
    }

    public BoundExpectation<T> hasProperty(final String propertyName, final Matcher<?> propertyValue) {
        return matches(Matchers.hasProperty(propertyName, propertyValue));
    }

    public BoundExpectation<T> is(final Matcher<? super T> matcher) {
        return matches(matcher);
    }

    public BoundExpectation<T> has(final Matcher<? super T> matcher) {
        return is(matcher);
    }

    public BoundExpectation<T> is(final T value) {
        return matches(Matchers.is(value));
    }

    public BoundExpectation<T> isIn(final Collection<T> values) {
        return matches(Matchers.isIn(values));
    }

    public BoundExpectation<T> isIn(final T ... values) {
        return matches(Matchers.isIn(values));
    }

    public BoundExpectation<T> never() {
        return new BoundExpectation<>(objectUnderTest, !positive);
    }

    public <P> BoundExpectation<T> hasPropertyOf(final Function<T, P> getter, final Matcher<P> propertymatcher) {
        return matches(new HasTypesafeProperty<>(getter, propertymatcher));
    }

    public <P> BoundExpectation<T> hasProperty(final Function<T, P> getter, P property) {
        return hasPropertyOf(getter, Matchers.is(property));
    }

    private BoundExpectation<T> matches(final Matcher<? super T> matcher) {
        assertThat(objectUnderTest, negatedIfNeeded(matcher));
        return this;
    }

    protected Matcher<? super T> negatedIfNeeded(final Matcher<? super T> matcher) {
        return positive ? matcher : Matchers.not(matcher);
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getWrappedClass() {
        return (Class<T>)objectUnderTest.getClass();
    }

}
