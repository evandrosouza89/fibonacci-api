package com.github.evandrosouza89.fibonacciapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.Optional;

import static com.github.evandrosouza89.fibonacciapi.service.FibonacciCalculator.CACHE_ALIAS_N;
import static com.github.evandrosouza89.fibonacciapi.service.FibonacciCalculator.MIN_INPUT_VALUE_TO_BE_CACHED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FibonacciCalculatorCacheTest {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    FibonacciCalculator underTest;

    @BeforeEach
    void setUp() {

        // Given

        underTest.nthFibonacciTerm(MIN_INPUT_VALUE_TO_BE_CACHED);

    }

    @Test
    void nthFibonacciTerm_result_put_in_cache() {

        // Given

        final BigInteger input = MIN_INPUT_VALUE_TO_BE_CACHED;

        final BigInteger expectedOutput = Optional.ofNullable(cacheManager.getCache(CACHE_ALIAS_N))
                .map(cachedValue -> cachedValue.get(input, BigInteger.class))
                .orElse(null);

        // When

        final BigInteger output = underTest.nthFibonacciTerm(input);

        // Then

        assertEquals(expectedOutput, output);

    }

    @Test
    void nthFibonacciTerm_result_not_put_in_cache() {

        // Given

        final BigInteger input = MIN_INPUT_VALUE_TO_BE_CACHED.subtract(BigInteger.ONE);

        final boolean wasCached = Optional.ofNullable(cacheManager.getCache(CACHE_ALIAS_N))
                .map(cachedValue -> cachedValue.get(input, BigInteger.class))
                .isPresent();

        // When

        underTest.nthFibonacciTerm(input);

        // Then

        assertFalse(wasCached);

    }


}
