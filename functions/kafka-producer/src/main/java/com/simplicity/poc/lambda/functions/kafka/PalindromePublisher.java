package com.simplicity.poc.lambda.functions.kafka;

import com.simplicity.poc.lambda.functions.kafka.setup.KafkaProducer;
import com.simplicity.poc.lambda.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PalindromePublisher implements Function<String, String> {

    private KafkaProducer kafkaProducer;

    public PalindromePublisher(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public String apply(String input) {
        String result;
        if (StringUtils.isBlank(input)) {
            result = String.format("Did not publish message <%s> because it was blank", input);
        } else {
            String message = createPalindrome(input);
            kafkaProducer.publish(message);
            result = String.format("Published message <%s> to Kafka.", message);
        }
        return result;
    }

    private String createPalindrome(String input) {
        return StringUtil.reverseString(input) + input;
    }

}
