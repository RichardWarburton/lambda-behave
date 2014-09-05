package com.insightfullogic.lambdabehave.example;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * This example specification is designed more as a way of testing out the API and
 * showing that it is readable and usable than actually testing out the matcher API.
 */
@RunWith(JunitSuiteRunner.class)
public class ExpectationSpec {{

    describe("the different forms of expectation", it -> {

        it.should("support basic expectations over values", expect -> {

            House greenGables = new House("Green Gables", "Anne", true);
            List<House> houses = Arrays.asList(greenGables);
            Object nothing = null;

            expect.that(greenGables)
                  .instanceOf(House.class)
                  .is(greenGables)
                  .isEqualTo(greenGables)
                  .isIn(houses)
                  .isIn(greenGables)
                  .isNotNull()
                  .sameInstance(greenGables)
                  .hasProperty(House::isFictional, true)
                  .hasPropertyOf(House::getName, containsString("Green "))
                  .and(nothing).toBeNull();
        });

        it.should("allow you write expectations about different collection values", expect -> {

            House greenGables = new House("Green Gables", "Anne", true);
            House kensingtonPalace = new House("Kensington Palace", "The Royal Family", false);
            House nothing = null;
            List<House> houses = Arrays.asList(greenGables, kensingtonPalace, nothing);

            expect.that(houses)
                  .hasItem(greenGables)
                  .contains(greenGables, kensingtonPalace, nothing)
                  .containsInAnyOrder(nothing, kensingtonPalace, greenGables)
                  .hasItems(kensingtonPalace, greenGables)
                  .hasSize(3)
                  .isNotNull()
                  .sameInstance(houses)
                  .never()
                      .toBeNull();
        });

        it.should("support expectations about different string values", expect -> {

            String str = "The quick brown fox jumps over the lazy dog";
            expect.that(str)
                  .containsString("quick brown")
                  .equalToIgnoringCase("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG")
                  .endsWith("lazy dog")
                  .startsWith("The quick")
                  .is(str);
        });

        it.should("support expectations about different array values", expect -> {

            final String[] strs = { "The quick brown", "fox jumps over", "the lazy dog" };
            expect.that(strs)
                  .isArrayWithSize(3)
                  .isArrayWithItem("the lazy dog")
                  .is(strs);
        });

    });

}}

class House {

    private final String name;
    private final String owner;
    private final boolean isFictional;

    House(final String name, final String owner, final boolean isFictional) {
        this.name = name;
        this.owner = owner;
        this.isFictional = isFictional;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isFictional() {
        return isFictional;
    }

}
