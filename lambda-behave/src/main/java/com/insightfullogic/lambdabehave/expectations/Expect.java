package com.insightfullogic.lambdabehave.expectations;

import com.insightfullogic.lambdabehave.Block;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.stubbing.Stubber;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class Expect {

    public <T> BoundExpectation<T> that(T value) {
        return new BoundExpectation<T>(value, true);
    }

    public <T> CollectionExpectation<T> that(Collection<T> collection) {
        return new CollectionExpectation<>(collection, true);
    }

    public <T extends Comparable<T>> ComparableExpectation<T> that(T comparable) {
        return new ComparableExpectation<T>(comparable, true);
    }

    public StringExpectation that(String str) {
        return new StringExpectation(str, true);
    }

    public DoubleExpectation that(Double value) {
        return new DoubleExpectation(value, true);
    }

    public <T> ArrayExpectation<T> that(T[] array) {
        return new ArrayExpectation<>(array, true);
    }

    public void exception(Class<? extends Throwable> expectedException, Block block) throws Exception {
        String expectedName = expectedException.getName();
        try {
            block.run();
        } catch (Throwable throwable) {
            if (!expectedException.isInstance(throwable)) {
                String name = throwable.getClass().getName();
                failure("Expected exception: " + expectedName + ", but " + name + " was thrown");
            }
            return;
        }
        failure("Expected exception: " + expectedName + ", but no exception was thrown");
    }

    // NB: no failure without a message because its a bad idea to not have test failure diagnostics
    public void failure(final String message) {
        Assert.fail(message);
    }

    @SuppressWarnings("unchecked")
    public <T> Stubber toAnswer(Runnable method) {
        return Mockito.doAnswer(invocation -> {
            Object[] arguments = invocation.getArguments();
            method.run();
            return null;
        });
    }

    @SuppressWarnings("unchecked")
    public <T> Stubber toAnswer(Consumer<T> method) {
        return doAnswer(arguments -> method.accept((T) arguments[0]), 1);
    }

    @SuppressWarnings("unchecked")
    public <F, S> Stubber toAnswer(BiConsumer<F, S> method) {
        return doAnswer(arguments ->
            method.accept((F) arguments[0], (S) arguments[1]), 2);
    }

    @SuppressWarnings("unchecked")
    public <F, S, T> Stubber toAnswer(TriConsumer<F, S, T> method) {
        return doAnswer(arguments ->
                method.accept((F) arguments[0], (S) arguments[1], (T) arguments[2]), 3);
    }

    private  Stubber doAnswer(Consumer<Object[]> method, int argumentCount) {
        return Mockito.doAnswer(invocation -> {
            Object[] arguments = invocation.getArguments();
            if (arguments.length >= argumentCount) {
                method.accept(arguments);
            } else {
                failure("Invocation requires at least " + argumentCount + " argument");
            }
            return null;
        });
    }

}
