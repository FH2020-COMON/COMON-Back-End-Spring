package com.on.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Add Apply Failed!!", value = HttpStatus.BAD_REQUEST)
public class AddApplyFailedException extends RuntimeException {
    public AddApplyFailedException() {
        super("Add Apply Failed Exception!!");
    }
}
