package com.insightfullogic.lambdabehave.example;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import com.insightfullogic.lambdabehave.impl.Specifier;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.Suite.describe;

interface Item {
    int value();
    boolean isOverTwo();
}

@RunWith(JunitSuiteRunner.class)
public class DataDrivenConstructorSpec {{

    describe("a number", it -> {
        // TODO not yet part of the public interface
        /*((Specifier) it).usesTable(Item.class, Item::value, Item::isOverTwo)
                .withRow(2, false)
                .withRow(3, true)
                .withRow(1, false)
            .toShow("%d is greater than two? %s", (expect, row) -> {
                expect.that(row.value() > 2).is(row.isOverTwo());
            });*/
    });
}}
