package com.accenture.transactionapplication.exception.handler;

public class ExceptionResponse {
    private final String message;
    private final Integer internalCode;
    private final String field;

    public ExceptionResponse(String message, Integer internalCode, String field) {
        this.message = message;
        this.internalCode = internalCode;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public Integer getInternalCode() {
        return internalCode;
    }

    public String getField() {
        return field;
    }
}
