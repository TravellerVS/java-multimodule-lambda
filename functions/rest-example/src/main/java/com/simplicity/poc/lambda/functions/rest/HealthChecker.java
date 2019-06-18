package com.simplicity.poc.lambda.functions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Service
public class HealthChecker implements Function<String, String> {

    private static final String SICK = "Sick";
    private static final String HEALTHY = "Healthy";

    RestTemplate restTemplate;

    HealthChecker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String apply(String endpointUrl) {
        String result;
        try {
            HttpStatus statusCode = restTemplate.getForEntity(endpointUrl, String.class).getStatusCode();
            result = (HttpStatus.OK.equals(statusCode)) ? HEALTHY : SICK;
        } catch (HttpServerErrorException e) {
            result = SICK;
        }
        return result;
    }

}
