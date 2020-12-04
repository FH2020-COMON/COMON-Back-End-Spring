package com.on.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User Is Not Owner!!")
public class UserNotOwnerException extends RuntimeException {
    public UserNotOwnerException() {
        super("User is not Owner!!");
    }
}
