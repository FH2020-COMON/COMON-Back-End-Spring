package com.on.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "File is Not Found")
public class FileIsNotFoundException extends RuntimeException {
    public FileIsNotFoundException() {
        super("File is not Found");
    }
}
