package com.accenture.transactionapplication.model;

public class Transaction {

    private final Integer id;

    private final String product;

    private final ActionType type;
    private final Double amount;


    public Transaction(Integer id, String product, ActionType type, Double amount) {
        this.id = id;
        this.product = product;
        this.type = type;
        this.amount = amount;
    }


    public Integer getId() {
        return id;
    }


    public String getProduct() {
        return product;
    }


    public ActionType getType() {
        return type;
    }


    public double getAmount() {
        return amount;
    }

}
