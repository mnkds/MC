package com.example.routes.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProcessingException extends RuntimeException{

    public ProcessingException() {
        super();
    }
    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
    public ProcessingException(String message) {
        super(message);
    }

}
