package com.github.evandrosouza89.fibonacciapi.service;

import com.github.evandrosouza89.fibonacciapi.exception.ErrorMessage;
import com.github.evandrosouza89.fibonacciapi.exception.InternalServerErrorException;
import com.github.evandrosouza89.fibonacciapi.exception.ValidatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FibonacciServiceTest {

    @Mock
    private FibonacciCalculator fibonacciCalculator;

    @InjectMocks
    private FibonacciService underTest;

    @Test
    void testCalculate_valid_input() {

        // Given

        final String input = "0";

        final BigInteger expectedOutput = new BigInteger(input);

        doReturn(expectedOutput)
                .when(fibonacciCalculator).nthFibonacciTerm(new BigInteger(input));

        // When

        final String output = underTest.calculate(input);

        // Then

        verify(fibonacciCalculator).nthFibonacciTerm(new BigInteger(input));

        assertEquals(expectedOutput.toString(), output);

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testCalculate_null_and_empty_input(final String invalidInput) {

        // Given

        Exception actualException = null;

        // When

        try {
            underTest.calculate(invalidInput);
        } catch (final Exception e) {
            actualException = e;
        }

        // Then

        assertNotNull(actualException);

        assertEquals(ValidatorException.class, actualException.getClass());

        assertEquals(
                ErrorMessage.N_CANNOT_BE_NULL_OR_EMPTY.getMessage(),
                ((ValidatorException) actualException).getReason()
        );

        verify(fibonacciCalculator, times(0)).nthFibonacciTerm(any(BigInteger.class));

    }

    @ParameterizedTest
    @ValueSource(strings = {"E", "_", "A0", " ", "1.0", ".0"})
    void testCalculate_not_a_number_input(final String invalidInput) {

        // Given

        Exception actualException = null;

        // When

        try {
            underTest.calculate(invalidInput);
        } catch (final Exception e) {
            actualException = e;
        }

        // Then

        assertNotNull(actualException);

        assertEquals(ValidatorException.class, actualException.getClass());

        assertEquals(
                ErrorMessage.N_MUST_REPRESENT_A_VALID_INTEGER.getMessage(),
                ((ValidatorException) actualException).getReason()
        );

        verify(fibonacciCalculator, times(0)).nthFibonacciTerm(any(BigInteger.class));

    }

    @Test
    void testCalculate_negative_number_input() {

        // Given

        final String invalidInput = "-1";

        Exception actualException = null;

        // When

        try {
            underTest.calculate(invalidInput);
        } catch (final Exception e) {
            actualException = e;
        }

        // Then

        assertNotNull(actualException);

        assertEquals(ValidatorException.class, actualException.getClass());

        assertEquals(
                ErrorMessage.N_CANNOT_BE_LESS_THAN_ZERO.getMessage(),
                ((ValidatorException) actualException).getReason()
        );

        verify(fibonacciCalculator, times(0)).nthFibonacciTerm(any(BigInteger.class));

    }

    @Test
    void testCalculate_calculator_error() {

        // Given

        final String input = "0";

        Exception actualException = null;

        doThrow(new IllegalArgumentException())
                .when(fibonacciCalculator).nthFibonacciTerm(any(BigInteger.class));

        // When

        try {
            underTest.calculate(input);
        } catch (final Exception e) {
            actualException = e;
        }

        // Then

        assertNotNull(actualException);

        assertEquals(InternalServerErrorException.class, actualException.getClass());

        assertEquals(
                ErrorMessage.INTERNAL_SERVER_ERROR.getMessage(),
                ((InternalServerErrorException) actualException).getReason()
        );

        verify(fibonacciCalculator).nthFibonacciTerm(any(BigInteger.class));

    }

}
