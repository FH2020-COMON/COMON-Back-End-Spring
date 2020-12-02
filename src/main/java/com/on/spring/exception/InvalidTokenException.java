package com.on.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid Token!!")
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Invalid Token");
    }
}
