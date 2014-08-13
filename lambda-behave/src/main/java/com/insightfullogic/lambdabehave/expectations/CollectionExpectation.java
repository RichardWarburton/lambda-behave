package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

public final class CollectionExpectation<T> extends BoundExpectation<Collection<T>> {

    CollectionExpectation(final Collection<T> objectUnderTest, final boolean positive) {
        super(objectUnderTest, positive);
    }

    public CollectionExpectation<T> isEmpty() {
        return matches(empty());
    }

    public CollectionExpectation<T> hasItem(final T item) {
        return matches(Matchers.hasItem(item));
    }

    public CollectionExpectation<T> hasItem(final Matcher<? super T> item) {
        return matches(Matchers.hasItem(item));
    }

    public CollectionExpectation<T> hasItems(final T ... items) {
        return matches(Matchers.hasItems(items));
    }

    public CollectionExpectation<T> contains(final T ... items) {
        return matches(Matchers.contains(items));
    }

    public CollectionExpectation<T> contains(final Matcher<? super  T> ... items) {
        return matches(Matchers.contains(items));
    }

    public CollectionExpectation<T> contains(final List<Matcher<? super  T>> items) {
        return matches(Matchers.contains(items));
    }

    public CollectionExpectation<T> containsInAnyOrder(final T ... items) {
        return matches(Matchers.containsInAnyOrder(items));
    }

    public CollectionExpectation<T> containsInAnyOrder(final Matcher<? super  T> ... items) {
        return matches(Matchers.containsInAnyOrder(items));
    }

    public CollectionExpectation<T> containsInAnyOrder(final Collection<Matcher<? super  T>> items) {
        return matches(Matchers.containsInAnyOrder(items));
    }

    public CollectionExpectation<T> emptyCollectionOf(final Class<T> type) {
        return matches(Matchers.emptyCollectionOf(type));
    }

    public CollectionExpectation<T> hasSize(final int size) {
        return matches(Matchers.hasSize(size));
    }

    public CollectionExpectation<T> hasSize(final Matcher<? super Integer> size) {
        return matches(Matchers.hasSize(size));
    }

    public CollectionExpectation<T> hasItems(final Matcher<? super T> ... items) {
        return matches(Matchers.hasItems(items));
    }

    private CollectionExpectation<T> matches(final Matcher<? super Collection<T>> matcher) {
        assertThat(objectUnderTest, negatedIfNeeded(matcher));
        return this;
    }

}
