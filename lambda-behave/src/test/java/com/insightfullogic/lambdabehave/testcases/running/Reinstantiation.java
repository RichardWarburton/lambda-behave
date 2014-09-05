package com.insightfullogic.lambdabehave.testcases.running;

import com.insightfullogic.lambdabehave.Suite;

import java.util.ArrayList;
import java.util.List;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class Reinstantiation {{

    List<Integer> numbers = new ArrayList<Integer>();

    describe("test reinstantiation", it -> {

        it.isSetupWith(() -> {
            numbers.add(1);
        });

        it.should("have clean setup state the first time", expect -> {
            expect.that(numbers).hasSize(1);
        });

        it.should("have clean setup state the second time", expect -> {
            expect.that(numbers).hasSize(1);
        });

    });

}}
