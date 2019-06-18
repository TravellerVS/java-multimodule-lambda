package com.simplicity.poc.lambda.functions.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthCheckerTest {

    String endpointUrl = "http://localhost:8080/healthCheck";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HealthChecker function;

    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void get_whenResponseis200_returnsOK() throws URISyntaxException {
        mockResponseAndAssertResult(HttpStatus.OK, "Healthy");
    }

    @Test
    public void get_whenResponseis500_returnsInternalServerError() throws URISyntaxException {
        mockResponseAndAssertResult(HttpStatus.INTERNAL_SERVER_ERROR, "Sick");
    }

    private void mockResponseAndAssertResult(HttpStatus serverResponseStatusCode, String expectedResult) throws URISyntaxException {
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(endpointUrl)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(serverResponseStatusCode));
        String result = function.apply(endpointUrl);
        mockServer.verify();
        assertEquals(expectedResult, result);
    }

}