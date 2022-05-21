package com.accenture.transactionapplication.exception.handler;

import com.accenture.transactionapplication.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleExceptionBadRequest(BadRequestException e) {
        System.out.println("Handle BadRequestException");
        return new ExceptionResponse(e.getFieldName(), 12345, null);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleExceptionEntityNotFound(EntityNotFoundException e) {
        System.out.println("Handle EntityNotFoundException");
        return new ExceptionResponse(e.getFieldName(), 22345, null);
    }

    @ExceptionHandler({FieldNotValidException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleFieldNotValidException(FieldNotValidException e) {
        System.out.println("Handle FieldNotValidException");
        return new ExceptionResponse(e.getFieldName(), 32345, null);
    }

    @ExceptionHandler({NotAuthenticatedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotAuthorized(NotAuthenticatedException e) {
        System.out.println("Handle NotAuthenticatedException");
        return new ExceptionResponse(e.getFieldName(), 42345, null);
    }

    @ExceptionHandler({NotAuthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handleEntityNotFound(NotAuthorizedException e) {
        System.out.println("Handle NotAuthorizedException");
        return new ExceptionResponse(e.getFieldName(), 52345, null);
    }

}

