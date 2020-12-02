package com.on.spring.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Company Not Found!!")
public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Company Not Found!!");
    }
}
