package com.accenture.transactionapplication.repository;

import com.accenture.transactionapplication.model.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private final List<Transaction> transactionRepository = new ArrayList<>();


    @Bean
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
