package com.github.evandrosouza89.fibonacciapi;

import com.github.evandrosouza89.fibonacciapi.controller.FibonacciRest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static com.github.evandrosouza89.fibonacciapi.controller.FibonacciRest.QUERY_PARAM_N;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class IntegrationTests {

    @Autowired
    private FibonacciRest controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testActuatorHealth() {

        // Given

        final String baseUrl = "http://localhost:" + port + "/actuator/health";

        final HttpStatusCode expectedStatusCode = HttpStatus.OK;

        final String expectedResponse = "{\"status\":\"UP\"}";

        // When

        final ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);

        // Then

        assertEquals(expectedStatusCode, response.getStatusCode());

        assertEquals(expectedResponse, response.getBody());


    }

    @Test
    void testGetFibonacci_happy_path() {

        // Given

        final String baseUrl = "http://localhost:" + port + FibonacciRest.GET_FIBONACCI_ENDPOINT;

        final String input = "1";

        final String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam(QUERY_PARAM_N, input)
                .encode()
                .toUriString();

        final String expectedResponse = "1";

        final HttpStatusCode expectedStatusCode = HttpStatus.OK;

        // When

        final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Then

        assertEquals(expectedStatusCode, response.getStatusCode());

        assertEquals(expectedResponse, response.getBody());

    }

    @Test
    void testGetFibonacci_validation_exception() {

        // Given

        final String baseUrl = "http://localhost:" + port + FibonacciRest.GET_FIBONACCI_ENDPOINT;

        final String input = "-1";

        final String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam(QUERY_PARAM_N, input)
                .encode()
                .toUriString();

        final HttpStatusCode expectedStatusCode = HttpStatus.BAD_REQUEST;

        // When

        final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Then

        assertEquals(expectedStatusCode, response.getStatusCode());

    }

    @Test
    void testErrorHandlingReturnsBadRequest() {

        // Given

        final String url = "http://localhost:" + port + "/wrong";

        final HttpStatus expectedStatusCode = HttpStatus.NOT_FOUND;

        // When

        final ResponseEntity<String> actualResponse = restTemplate.getForEntity(url, String.class);

        // Then

        assertNotNull(actualResponse);

        assertEquals(expectedStatusCode, actualResponse.getStatusCode());

    }

}