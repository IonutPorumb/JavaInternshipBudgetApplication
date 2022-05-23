package com.accenture.budgetapplication.exception;

public class NotAuthorizedException extends RuntimeException {
    private final String fieldName;

    public NotAuthorizedException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

}