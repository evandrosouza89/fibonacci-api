package com.github.evandrosouza89.fibonacciapi;

import com.github.evandrosouza89.fibonacciapi.controller.FibonacciRest;
import com.github.evandrosouza89.fibonacciapi.service.FibonacciService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SmokeTests {

    // Given

    @Autowired
    private FibonacciRest controller;

	@Autowired
	private FibonacciService fibonacciService;

    // When

    @Test
    void contextLoads() {

		// Then

		assertNotNull(controller);

		assertNotNull(fibonacciService);

    }

}
