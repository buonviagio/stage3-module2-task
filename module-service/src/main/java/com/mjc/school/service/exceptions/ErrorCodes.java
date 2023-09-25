package com.mjc.school.service.exceptions;

public enum ErrorCodes {
    NEW_ID_N_EXIST("000001", "News with id %d does not exist."),
    AUTH_ID_NOT_EXIST("000002", "Author Id does not exist. Author Id is: %s"),
    NEGATIVE_OR_NULL_NUMBER("000010", "%s can not be null or less than 1. %s is: %s"),
    NULL_STRING("000011", "%s can not be null. %s is null"),
    STRING_LANGTH("000012", "%s can not be less than %d and more than %d symbols. %s is %s"),
    VALID_NUMBER_VALUE("000013", "%s should be number");

    private String code, message;

    ErrorCodes (String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ERROR_CODE: " + code + " ERROR_MESSAGE: " + message;
    }
}
