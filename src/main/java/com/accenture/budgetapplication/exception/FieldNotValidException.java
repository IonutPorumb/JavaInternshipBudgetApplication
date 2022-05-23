package com.accenture.budgetapplication.exception;

public class FieldNotValidException extends RuntimeException {
    private final String fieldName;

    public FieldNotValidException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}


