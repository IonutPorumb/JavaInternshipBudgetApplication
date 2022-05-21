package com.accenture.transactionapplication.exception;

public class BadRequestException extends RuntimeException {
    private final String fieldName;

    public BadRequestException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
