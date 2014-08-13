package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.SourceGenerator;

public final class ValueSourceGenerator implements SourceGenerator {

    private final int[] values;

    private int index;

    public ValueSourceGenerator(final int[] values) {
        this.values = values;
        index = 0;
    }

    @Override
    public int generateInt(final int maxValue) {
        try {
            final int candidate = values[index];
            if (candidate > maxValue)
                return candidate & maxValue;

            return candidate;
        } finally {
            index = (index + 1) % values.length;
        }
    }

    /**
     * There's no concept of a seed in this case
     *
     * @return 0
     */
    @Override
    public long getSeed() {
        return 0;
    }
}
