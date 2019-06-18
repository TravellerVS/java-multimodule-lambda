package com.simplicity.poc.lambda.functions.kafka.util.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.kafka.inbound.KafkaMessageSource;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaMessageSourceFactory {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.cloud.stream.default.group}")
    private String consumerGroup;
    @Value("${spring.cloud.stream.default.consumer.startOffset}")
    private String autoOffsetConfig;

    public KafkaMessageSource<String, String> getKafkaMessageSource(String kafkaTopic) {
        DefaultKafkaConsumerFactory<String, String> consumerFactory = getConsumerFactory();
        consumerFactory.setValueDeserializer(new StringDeserializer());
        KafkaMessageSource<String, String> messageSource = new KafkaMessageSource(consumerFactory, kafkaTopic);
        messageSource.setGroupId(consumerGroup);
        messageSource.reset();
        return messageSource;
    }

    private DefaultKafkaConsumerFactory getConsumerFactory() {
        Map<String, Object> consumerProps = getDefaultConfig();
        return new DefaultKafkaConsumerFactory<>(consumerProps);
    }

    private Map<String, Object> getDefaultConfig() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        consumerProps.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        consumerProps.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);

        consumerProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetConfig);
        return consumerProps;
    }
}
