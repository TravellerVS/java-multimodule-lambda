package com.simplicity.poc.lambda.functions.kafka.setup;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    String OUTPUT = "outputChannel";

    @Output(OUTPUT)
    @Qualifier(OUTPUT)
    MessageChannel outputChannel();
}
