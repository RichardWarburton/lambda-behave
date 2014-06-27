package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Block;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Blocks {

    private final List<Block> blocks;

    public Blocks() {
        blocks = new ArrayList<>();
    }

    public void add(Block block) {
        blocks.add(block);
    }

    public Optional<SpecificationReport> runAll(String description) {
        try {
            for (Block block : blocks) {
                block.run();
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.of(SpecificationReport.error(description, e));
        }
    }

    public Stream<CompleteBehaviour> completeFixtures(String description) {
        return blocks.stream()
                     .map(block -> new CompleteFixture(description, block));
    }
}
