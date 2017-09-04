package com.mifinity.demo.adapter.rest.exceptions;

/**
 * Created by andrea.schembri on 04/09/2017.
 */
public class UnauthorizedCardException extends RuntimeException {

    public UnauthorizedCardException(final String message) {
        super(message);
    }
}
