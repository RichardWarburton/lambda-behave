package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Block;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Blocks {

    private final String suiteName;
    private final List<Block> blocks;

    public Blocks(final String suiteName) {
        this.suiteName = suiteName;
        blocks = new ArrayList<>();
    }

    public void add(final Block block) {
        blocks.add(block);
    }

    public Optional<SpecificationReport> runAll(final String description) {
        try {
            for (Block block : blocks) {
                block.run();
            }
            return Optional.empty();
        } catch (final Exception e) {
            return Optional.of(SpecificationReport.error(description, e));
        }
    }

    public Stream<CompleteBehaviour> completeFixtures(final String description) {
        return blocks.stream()
                     .map(block -> new CompleteFixture(suiteName, description, block));
    }

    public int size() {
        return blocks.size();
    }
}
