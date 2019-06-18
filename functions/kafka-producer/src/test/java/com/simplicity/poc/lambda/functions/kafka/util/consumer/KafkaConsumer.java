package com.simplicity.poc.lambda.functions.kafka.util.consumer;

import org.springframework.integration.kafka.inbound.KafkaMessageSource;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private KafkaMessageSource<String, String> kafkaMessageSource;

    public KafkaConsumer(KafkaMessageSource<String, String> kafkaTopicMessageSource) {
        this.kafkaMessageSource = kafkaTopicMessageSource;
    }

    public String consume() {
        Message<Object> received = kafkaMessageSource.receive();
        return (String) (received != null ? received.getPayload() : null);
    }

}
