package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.impl.generators.StringGenerator;

import static java.lang.Double.longBitsToDouble;
import static java.lang.Float.intBitsToFloat;
import static java.lang.Integer.MAX_VALUE;

public final class Example {

    public static Generator<String> strings() {
        return new StringGenerator(' ', Character.MAX_VALUE);
    }

    public static Generator<String> asciiStrings() {
        return new StringGenerator(' ', '~');
    }

    public static Generator<Integer> integersUpTo(int maxValue) {
        return ng -> ng.generateInt(maxValue);
    }

    public static Generator<Long> longs() {
        return ng -> longs(ng);
    }

    public static Generator<Double> doubles() {
        return ng -> longBitsToDouble(longs(ng));
    }

    public static Generator<Float> floats() {
        return ng -> intBitsToFloat(ng.generateInt(MAX_VALUE));
    }

    private static long longs(NumberGenerator ng) {
        return ((long) ng.generateInt(MAX_VALUE)) << 32 + ng.generateInt(MAX_VALUE);
    }

}
