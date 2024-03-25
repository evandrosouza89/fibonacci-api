package com.github.evandrosouza89.fibonacciapi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
class FibonacciCalculator {

    static final String CACHE_ALIAS_N = "n";
    static final BigInteger MIN_INPUT_VALUE_TO_BE_CACHED = BigInteger.valueOf(50001L);

    /*The iterative method avoids repetitive work by storing the last two Fibonacci terms in variables.
                The time complexity and space complexity of the iterative method is O(n) and O(1) respectively.*/
    @Cacheable(value = CACHE_ALIAS_N, condition = "#input.compareTo(new java.math.BigInteger(\"50000\")) > 0")
    public BigInteger nthFibonacciTerm(final BigInteger input) throws IllegalArgumentException {

        if (BigInteger.ZERO.compareTo(input) > 0) {
            throw new IllegalArgumentException();
        }

        if (BigInteger.ZERO.equals(input) || BigInteger.ONE.equals(input)) {
            return input;
        }

        BigInteger n0 = BigInteger.ZERO;

        BigInteger n1 = BigInteger.ONE;

        BigInteger tempNthTerm;

        BigInteger i = BigInteger.TWO;

        while (i.compareTo(input) <= 0) {

            tempNthTerm = n0.add(n1);

            n0 = n1;

            n1 = tempNthTerm;

            i = i.add(BigInteger.ONE);

        }

        return n1;

    }

}
