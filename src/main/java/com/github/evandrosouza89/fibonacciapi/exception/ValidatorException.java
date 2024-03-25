package com.github.evandrosouza89.fibonacciapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidatorException extends ResponseStatusException {

    public ValidatorException(final ErrorMessage reason) {
        super(HttpStatus.BAD_REQUEST, reason.getMessage());
    }

}
