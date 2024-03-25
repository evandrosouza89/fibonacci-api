package com.github.evandrosouza89.fibonacciapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class FibonacciCalculatorTest {

    @InjectMocks
    private FibonacciCalculator underTest;

    @ParameterizedTest
    @ValueSource(longs = {0L, 1L})
    void testNthFibonacciTerm_zero_one(final long value) {

        // Given
        final BigInteger input = BigInteger.valueOf(value);

        final BigInteger expectedOutput = BigInteger.valueOf(value);

        // When

        final BigInteger output = underTest.nthFibonacciTerm(input);

        // Then

        assertEquals(expectedOutput, output);

    }

    @Test
    void testNthFibonacciTerm_invalid_input() {

        // Given

        final BigInteger invalidInput = BigInteger.valueOf(-1);

        Exception actualException = null;

        // When

        try {
            underTest.nthFibonacciTerm(invalidInput);
        } catch (final Exception e) {
            actualException = e;
        }

        // Then

        assertNotNull(actualException);

        assertEquals(IllegalArgumentException.class, actualException.getClass());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data.csv", numLinesToSkip = 1)
    void testNthFibonacciTerm(final String value, final String expected) {

        // Given
        final BigInteger input = new BigInteger(value);

        final BigInteger expectedOutput = new BigInteger(expected);

        // When

        final BigInteger output = underTest.nthFibonacciTerm(input);

        // Then

        assertEquals(expectedOutput, output);

    }


}
