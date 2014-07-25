package com.insightfullogic.lambdabehave.generated;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import org.junit.runner.RunWith;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.*;

@RunWith(JunitSuiteRunner.class)
public class Answers {{

    describe("the fluent mock answer API", it -> {

        it.should("Allow you to answer no arg void method calls", expect -> {

            // given
            Runnable mockAnswer = mock(Runnable.class);
            AnswerObject answerObject = mock(AnswerObject.class);

            expect.toAnswer(mockAnswer)
                  .when(answerObject).noArgMethod();

            when:
            answerObject.noArgMethod();

            then:
            verify(mockAnswer).run();
        });

        it.should("Allow you to answer 1 arg void method calls", expect -> {

            // given
            Consumer<String> mockAnswer = mock(Consumer.class);
            AnswerObject answerObject = mock(AnswerObject.class);

            expect.toAnswer(mockAnswer)
                  .when(answerObject).oneArgMethod(anyString());

            when:
            answerObject.oneArgMethod("a");

            then:
            verify(mockAnswer).accept("a");
        });

        it.should("Allow you to answer 2 arg void method calls", expect -> {

            // given
            BiConsumer<String, Integer> mockAnswer = mock(BiConsumer.class);
            AnswerObject answerObject = mock(AnswerObject.class);

            expect.toAnswer(mockAnswer)
                    .when(answerObject).twoArgMethod(anyString(), any());

            when:
            answerObject.twoArgMethod("a", 1);

            then:
            verify(mockAnswer).accept("a", 1);
        });

    });

}}
