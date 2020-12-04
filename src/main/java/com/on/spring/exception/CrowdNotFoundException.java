package com.on.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Crowd is not found!!")
public class CrowdNotFoundException extends RuntimeException {
    public CrowdNotFoundException() {
        super("Crowd is not found!!");
    }
}
