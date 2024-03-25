package com.github.evandrosouza89.fibonacciapi.service;

import com.github.evandrosouza89.fibonacciapi.exception.ErrorMessage;
import com.github.evandrosouza89.fibonacciapi.exception.InternalServerErrorException;
import com.github.evandrosouza89.fibonacciapi.exception.ValidatorException;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@AllArgsConstructor
@Service
public class FibonacciService {

    private final FibonacciCalculator fibonacciCalculator;

    public String calculate(final String n) {

        final BigInteger input = validateAndParseN(n);

        final String result;

        try {
            result = fibonacciCalculator.nthFibonacciTerm(input).toString();
        } catch (final IllegalArgumentException e) {
            throw logAndBuildIllegalArgumentException(ErrorMessage.INTERNAL_SERVER_ERROR);
        }

        return result;

    }

    private BigInteger validateAndParseN(final String input) {

        final BigInteger n;

        if (StringUtils.isEmpty(input)) {
            throw logAndBuildValidationException(ErrorMessage.N_CANNOT_BE_NULL_OR_EMPTY);
        }

        try {
            n = new BigInteger(input);
        } catch (final NumberFormatException nfe) {
            throw logAndBuildValidationException(ErrorMessage.N_MUST_REPRESENT_A_VALID_INTEGER);
        }

        if (BigInteger.ZERO.compareTo(n) > 0) {
            throw logAndBuildValidationException(ErrorMessage.N_CANNOT_BE_LESS_THAN_ZERO);
        }

        return n;

    }

    private ValidatorException logAndBuildValidationException(final ErrorMessage errorMsg) {

        log.info(errorMsg.getMessage());

        return new ValidatorException(errorMsg);

    }

    private InternalServerErrorException logAndBuildIllegalArgumentException(final ErrorMessage errorMsg) {

        log.error(errorMsg.getMessage());

        return new InternalServerErrorException(errorMsg);

    }

}
