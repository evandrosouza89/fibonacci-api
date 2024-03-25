package com.github.evandrosouza89.fibonacciapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalServerErrorException extends ResponseStatusException {

    public InternalServerErrorException(final ErrorMessage reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason.getMessage());
    }

}
