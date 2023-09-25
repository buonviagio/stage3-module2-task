package com.mjc.school.exception;

public class CommandNotFoundException extends RuntimeException{
    public CommandNotFoundException(String message) {
        super(message);
    }
}
