package com.accenture.budgetapplication.repository.transaction;

import com.accenture.budgetapplication.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private final List<Transaction> transactionRepository = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactionRepository;
    }

    @Override
    public String toString() {
        return "TransactionRepository{" +
                "transactions=" + transactionRepository +
                '}';
    }
}
