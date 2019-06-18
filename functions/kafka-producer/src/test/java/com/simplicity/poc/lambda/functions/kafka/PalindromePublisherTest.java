package com.simplicity.poc.lambda.functions.kafka;

import com.simplicity.poc.lambda.functions.kafka.util.consumer.KafkaConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"spring.cloud.stream.default.consumer.startOffset=latest"})
public class PalindromePublisherTest {

    @Autowired
    KafkaConsumer consumer;

    @Autowired
    PalindromePublisher function;

    @Test
    public void apply_withString_publishesPalindrome() {
        function.apply("Abc");
        assertEquals("cbAAbc", consumer.consume());
    }

    @Test
    public void apply_withNull_doesNotPublish() {
        function.apply(null);
        assertNull(consumer.consume());
    }

    @Test
    public void apply_withEmptyString_doesNotPublish() {
        function.apply("");
        assertNull(consumer.consume());
    }


    @Test
    public void apply_withWhitespaceFilledString_doesNotPublish() {
        function.apply("    ");
        assertNull(consumer.consume());
    }

}