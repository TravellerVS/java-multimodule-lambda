package com.simplicity.poc.lambda.functions.kafka.setup;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Channels.class)
public class KafkaProducer {

    private MessageChannel ouputChannel;

    KafkaProducer(@Qualifier(Channels.OUTPUT) MessageChannel ouputChannel) {
        this.ouputChannel = ouputChannel;
    }

    public void publish(Object payload) {
        Message<Object> message = MessageBuilder
                .withPayload(payload)
                .build();
        ouputChannel.send(message);
    }
}
