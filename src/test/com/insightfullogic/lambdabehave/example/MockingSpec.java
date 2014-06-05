package com.insightfullogic.lambdabehave.example;

import com.insightfullogic.lambdabehave.JunitBehaveRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.mock;

@RunWith(JunitBehaveRunner.class)
public class MockingSpec {

    interface Subscriber extends Consumer<String> {}

    class Publisher {
        private final List<Subscriber> subscribers;

        Publisher() {
            subscribers = new ArrayList<>();
        }

        public void addSubscriber(Subscriber subscriber) {
            subscribers.add(subscriber);
        }

        public void send(String message) {
            subscribers.forEach(subscriber -> subscriber.accept(message));
        }
    }

    {

        Publisher publisher = new Publisher();
        Subscriber subscriber = mock(Subscriber.class);
        Subscriber subscriber2 = mock(Subscriber.class);
        Subscriber subscriber3 = mock(Subscriber.class);
        publisher.addSubscriber(subscriber);
        publisher.addSubscriber(subscriber2);

    describe("should send messages to all subscribers", it -> {
        when:
        publisher.send("hello world");

        then:
        subscriber.accept("hello world");
        subscriber2.accept("hello world");

        never:
        subscriber3.accept("hello world");
    });

}}
