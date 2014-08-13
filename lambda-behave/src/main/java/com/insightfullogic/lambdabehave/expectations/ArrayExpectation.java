package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public final class ArrayExpectation<T> extends BoundExpectation<T[]> {

    ArrayExpectation(final T[] array, final boolean positive) {
        super(array, positive);
    }

    public ArrayExpectation<T> isArrayWithSize(final int size) {
        return matches(arrayWithSize(size));
    }

    public ArrayExpectation<T> isArrayWithSize(final Matcher<? super Integer> matcher) {
        return matches(arrayWithSize(matcher));
    }

    public ArrayExpectation<T> isArray(final Matcher<? super T> ... elementMatchers) {
        return matches(array(elementMatchers));
    }

    public ArrayExpectation<T> isArrayContaining(final T ... items) {
        return matches(arrayContaining(items));
    }

    public ArrayExpectation<T> isArrayContaining(final Matcher<? super T> ... elements) {
        return matches(arrayContaining(elements));
    }

    public ArrayExpectation<T> isArrayContaining(final List<Matcher<? super T>> elements) {
        return matches(arrayContaining(elements));
    }

    public ArrayExpectation<T> isArrayContainingInAnyOrder(final T ... items) {
        return matches(arrayContainingInAnyOrder(items));
    }

    public ArrayExpectation<T> isArrayContainingInAnyOrder(final Matcher<? super T> ... elements) {
        return matches(arrayContainingInAnyOrder(elements));
    }

    public ArrayExpectation<T> isArrayContainingInAnyOrder(final Collection<Matcher<? super T>> elements) {
        return matches(arrayContainingInAnyOrder(elements));
    }

    public ArrayExpectation<T> isEmptyArray() {
        return matches(emptyArray());
    }

    public ArrayExpectation<T> isArrayWithItem(final T item) {
        return matches(hasItemInArray(item));
    }

    public ArrayExpectation<T> isArrayWithItem(final Matcher<? super T> itemMatcher) {
        return matches(hasItemInArray(itemMatcher));
    }

    @Override
    public ArrayExpectation<T> isEqualTo(final T[] expected) {
        super.isEqualTo(expected);
        return this;
    }

    @Override
    public ArrayExpectation<T> hasToString(final Matcher<? super String> str) {
        super.hasToString(str);
        return this;
    }

    @Override
    public ArrayExpectation<T> hasToString(final String arg) {
        super.hasToString(arg);
        return this;
    }

    @Override
    public ArrayExpectation<T> instanceOf(final Class<?> arg) {
        super.instanceOf(arg);
        return this;
    }

    @Override
    public ArrayExpectation<T> isNotNull() {
        super.isNotNull();
        return this;
    }

    @Override
    public ArrayExpectation<T> toBeNull() {
        super.toBeNull();
        return this;
    }

    @Override
    public ArrayExpectation<T> sameInstance(final T[] target) {
        super.sameInstance(target);
        return this;
    }

    @Override
    public ArrayExpectation<T> any() {
        super.any();
        return this;
    }

    @Override
    public ArrayExpectation<T> hasProperty(final String propertyName, final Matcher<?> propertyValue) {
        super.hasProperty(propertyName, propertyValue);
        return this;
    }

    @Override
    public ArrayExpectation<T> is(final T[] value) {
        super.is(value);
        return this;
    }

    @Override
    public ArrayExpectation<T> is(final Matcher<? super T[]> matcher) {
        super.is(matcher);
        return this;
    }

    @Override
    public ArrayExpectation<T> isIn(final Collection<T[]> values) {
        super.isIn(values);
        return this;
    }

    @Override
    public ArrayExpectation<T> isIn(final T[] ... values) {
        super.isIn(values);
        return this;
    }

    @Override
    public ArrayExpectation<T> never() {
        super.never();
        return this;
    }

    private ArrayExpectation<T> matches(final Matcher<? super T[]> matcher) {
        assertThat(objectUnderTest, matcher);
        return this;
    }

}
