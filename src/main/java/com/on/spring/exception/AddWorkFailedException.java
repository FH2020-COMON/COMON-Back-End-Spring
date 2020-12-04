package com.on.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Add Work Failed!!")
public class AddWorkFailedException extends RuntimeException {
    public AddWorkFailedException() {
        super("Add Work Failed!!");
    }
}
