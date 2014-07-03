package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;

public class StringGenerator implements Generator<String> {

    private static final int MAX_STRING_LENGTH = 10;

    private final int minValue;
    private final int range;

    public StringGenerator(int minValue, int maxValue) {
        this.minValue = minValue;
        range = maxValue - minValue;
    }

    @Override
    public String generate(SourceGenerator source) {
        final int length = source.generateInt(MAX_STRING_LENGTH);

        char[] characters = new char[length];
        for (int i = 0; i < length; i++) {
            characters[i] = (char) (minValue + source.generateInt(range));
        }

        return new String(characters);
    }

}
