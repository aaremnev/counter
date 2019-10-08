package com.example.counter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotSupportedContentException extends RuntimeException {
    public NotSupportedContentException(String s) {
        super(s);
    }
}
