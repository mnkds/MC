package com.example.routes.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmptyReqParameterException extends RuntimeException{

    public EmptyReqParameterException() {
        super();
    }
    public EmptyReqParameterException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmptyReqParameterException(String message) {
        super(message);
    }

}
