package com.insightfullogic.lambdabehave.example;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import org.junit.runner.RunWith;

import java.util.Stack;

import static com.insightfullogic.lambdabehave.Suite.describe;

@RunWith(JunitSuiteRunner.class)
public class StackSpec {{

    Stack<Integer> stack = new Stack<>();

    describe("a stack", it -> {

        it.isSetupWith(stack::clear);

        it.isConcludedWith(stack::clear);

        it.should("be empty when created", expect -> {
            expect.that(stack).isEmpty();
        });

        it.should("push new elements onto the top of the stack", expect -> {
            expect.that(stack).isEmpty();

            stack.push(1);

            expect.that(stack.get(0)).isEqualTo(1);
        });

        it.should("pop the last element pushed onto the stack", expect -> {
            expect.that(stack).isEmpty();

            stack.push(2);
            stack.push(1);

            expect.that(stack.pop()).isEqualTo(1);
        });

    });

}}
