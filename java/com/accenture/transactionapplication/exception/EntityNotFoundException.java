package com.accenture.transactionapplication.exception;

public class EntityNotFoundException extends RuntimeException {
    private final String fieldName;

    public EntityNotFoundException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
