package com.github.evandrosouza89.fibonacciapi.controller;

import com.github.evandrosouza89.fibonacciapi.service.FibonacciService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class FibonacciRest {

    public static final String QUERY_PARAM_N = "n";
    public static final String GET_FIBONACCI_ENDPOINT = "/fib";

    private final FibonacciService fibonacciService;

    @GetMapping(value = GET_FIBONACCI_ENDPOINT)
    public String getFibonacci(@RequestParam(name = QUERY_PARAM_N) final String n) {

        return fibonacciService.calculate(n);

    }


}
