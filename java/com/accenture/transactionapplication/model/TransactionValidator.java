package com.accenture.transactionapplication.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransactionValidator implements ConstraintValidator<ValidTransaction, Transaction> {
    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext constraintValidatorContext) {
        boolean amountValid = transaction.getAmount() > 0;
        boolean userIdValid = transaction.getUserId() > 0;
        boolean validType = transaction.getType().equals(ActionType.BUY) ||
                transaction.getType().equals(ActionType.SELL);
        boolean validProduct = transaction.getProduct() == null ||
                transaction.getProduct().equals("");

        if (!amountValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Introduced amount is negative!")
                    .addConstraintViolation();
        }

        if (!userIdValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("User id must be a positive integer number!")
                    .addConstraintViolation();
        }

        if (!validType) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("The introduced Type value must be 'BUY' or 'SELL'!")
                    .addConstraintViolation();
        }

        if (!validProduct) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("You must introduce a Product name in the product field!")
                    .addConstraintViolation();

        }

        return amountValid
                && userIdValid
                && validType
//                && validProduct
                ;
    }
}
