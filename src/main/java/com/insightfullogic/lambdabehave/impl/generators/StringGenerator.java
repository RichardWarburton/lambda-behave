package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.generators.NumberGenerator;

public class StringGenerator implements Generator<String> {

    private static final int MAX_STRING_LENGTH = 10;

    private final int minValue;
    private final int range;

    public StringGenerator(int minValue, int maxValue) {
        this.minValue = minValue;
        range = maxValue - minValue;
    }

    @Override
    public String generate(NumberGenerator ng) {
        final int length = ng.generateInt(MAX_STRING_LENGTH);

        char[] characters = new char[length];
        for (int i = 0; i < length; i++) {
            characters[i] = (char) (minValue + ng.generateInt(range));
        }

        return new String(characters);
    }

}
