package com.on.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Apply is not found!!")
public class ApplyNotFoundException extends RuntimeException {
    public ApplyNotFoundException() {
        super("Apply is not found!!");
    }
}
