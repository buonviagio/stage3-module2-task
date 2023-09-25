package com.mjc.school.service.exceptions;

import java.lang.reflect.InvocationTargetException;

public class CheckException extends RuntimeException {
    public CheckException(String message) {
        super(message);
    }
}
