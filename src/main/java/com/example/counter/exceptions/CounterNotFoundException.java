package com.example.counter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CounterNotFoundException extends RuntimeException {
    public CounterNotFoundException(String msg) {
        super(msg);
    }
}
