package com.github.evandrosouza89.fibonacciapi.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {

    N_CANNOT_BE_NULL_OR_EMPTY("n cannot be null or empty!"),
    N_MUST_REPRESENT_A_VALID_INTEGER("n must represent a valid integer!"),
    N_CANNOT_BE_LESS_THAN_ZERO("n cannot be less than zero!"),
    INTERNAL_SERVER_ERROR("Internal server error!");

    private final String message;

}
