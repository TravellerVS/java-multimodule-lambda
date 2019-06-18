package com.simplicity.poc.lambda.functions.kafka.util.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.kafka.inbound.KafkaMessageSource;

@Configuration
public class KafkaMessageSourceBeans {

    @Value("${spring.cloud.stream.bindings.outputChannel.destination}")
    private String kafkaTopic;

    @Bean
    KafkaMessageSource<String, String> kafkaTopicMessageSource(KafkaMessageSourceFactory kafkaMessageSourceFactory) {
        return kafkaMessageSourceFactory.getKafkaMessageSource(kafkaTopic);
    }

}
