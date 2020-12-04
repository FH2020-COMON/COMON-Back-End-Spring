package com.on.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "File upload is failed!!")
public class FileUploadFailedException extends RuntimeException {
    public FileUploadFailedException() {
        super("File Upload is Failed!!");
    }
}
