package com.example.routes.demo.controller;

import com.example.routes.demo.exception.EmptyReqParameterException;
import com.example.routes.demo.exception.ProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice(assignableTypes = DemoController.class)
public class DemoControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EmptyReqParameterException.class,
                                ProcessingException.class
                                })
    protected ResponseEntity<?> handleConflict(RuntimeException ex, WebRequest request){
        if (ex instanceof EmptyReqParameterException)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        else if (ex instanceof ProcessingException)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

}
