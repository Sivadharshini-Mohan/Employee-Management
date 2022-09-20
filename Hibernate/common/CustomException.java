package com.i2i.annotation.common;

import java.lang.RuntimeException;

/**
 * <p>
 * Custom Exception is handling the exception by manually
 * </p>
 */ 
public class CustomException extends Exception {

    public CustomException () {
        super();
    }

    public CustomException (String message) {
        super(message);
    }

    public CustomException (String message, Throwable error) {
        super(message, error);
    }
}