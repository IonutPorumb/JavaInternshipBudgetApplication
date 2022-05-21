package com.accenture.transactionapplication.exception;

public class FieldNotValidException extends RuntimeException {
    private final String fieldName;

    public FieldNotValidException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}


