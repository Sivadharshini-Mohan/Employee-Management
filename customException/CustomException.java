package com.i2i.project.customException;

import java.lang.RuntimeException;

public class CustomException extends Exception {
    CustomException (String message) {
        super(message);
    }
}