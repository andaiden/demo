package com.mifinity.demo.adapter.rest;

import com.mifinity.demo.adapter.rest.exceptions.UnauthorizedCardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by andrea.schembri on 04/09/2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnauthorizedCardException.class)
    public ResponseEntity<String> handleIllegalStateException(final UnauthorizedCardException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
