package com.accentureapplication.service;

import com.accentureapplication.model.ActionType;
import com.accentureapplication.repository.TransactionRepository;
import com.accentureapplication.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Service
//@Component
public class TransactionService {

    private final TransactionRepository transactionRepository;


    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }


    public ResponseEntity<List<Transaction>> getAllTransactionFiltered(String product, ActionType type,
                                                                       Double minAmount, Double maxAmount) {
        List<Transaction> transactions = transactionRepository.getTransactions();
        ResponseEntity<List<Transaction>> transactionResult = ResponseEntity.ok(transactions);
        if (product != null && type == null && minAmount == null && maxAmount == null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getProduct().equals(product))
                    .toList());
        }
        if (product == null && type != null && minAmount == null && maxAmount == null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getType().equals(type))
                    .toList());
        }
        if (product == null && type == null && minAmount != null && maxAmount == null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getAmount() > minAmount)
                    .toList());
        }
        if (product == null && type == null && minAmount == null && maxAmount != null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getAmount() < maxAmount)
                    .toList());
        }
        if (product != null && type != null && minAmount == null && maxAmount == null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getProduct().equals(product) && t.getType().equals(type))
                    .toList());
        }
        if (product != null && type != null && minAmount != null && maxAmount == null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getProduct().equals(product) && t.getType().equals(type) && t.getAmount() > minAmount)
                    .toList());
        }
        if (product != null && type != null && minAmount != null && maxAmount != null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getProduct().equals(product) && t.getType().equals(type) &&
                            t.getAmount() > minAmount && t.getAmount() < maxAmount)
                    .toList());
        }
        if (product != null && type == null && minAmount != null && maxAmount == null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getProduct().equals(product) && t.getAmount() > minAmount)
                    .toList());
        }
        if (product != null && type == null && minAmount == null && maxAmount != null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getProduct().equals(product) && t.getAmount() < maxAmount)
                    .toList());
        }
        if (product == null && type != null && minAmount != null && maxAmount == null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getType().equals(type) && t.getAmount() > minAmount)
                    .toList());
        }
        if (product == null && type != null && minAmount == null && maxAmount != null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getType().equals(type) && t.getAmount() < maxAmount)
                    .toList());
        }
        if (product == null && type == null && minAmount != null && maxAmount != null) {
            return ResponseEntity.ok(transactions.stream()
                    .filter(t -> t.getAmount() > minAmount && t.getAmount() < maxAmount)
                    .toList());
        }
        return transactionResult;
    }


    public ResponseEntity<Transaction> getTransactionById(Integer id) {
        return ResponseEntity.ok(transactionRepository.getTransactions().stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new));
    }


//      POST /transactions - adds a new transaction
    public ResponseEntity<Transaction> createTransaction(Transaction transaction) {
        int newId = transactionRepository.getTransactions().size();
        Transaction savedTransaction = new Transaction(
                newId, transaction.getProduct(), transaction.getType(), transaction.getAmount());
        transactionRepository.getTransactions().add(savedTransaction);
        return ResponseEntity.created(URI.create("/" + newId)).body(savedTransaction);
    }


//    PUT  /transactions/{id} - replaces the transaction with id
    public ResponseEntity<Transaction> updateTransactionDataById(Integer id, Transaction transaction) {
        List<Transaction> transactions = transactionRepository.getTransactions();
        ResponseEntity<Transaction> transactionToBeUpdated = ResponseEntity.ok(transactions.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new));
        Transaction receivedTransaction = transactionToBeUpdated.getBody();
        Transaction updatedTransaction = new Transaction(id, transaction.getProduct(), transaction.getType(), transaction.getAmount());
        transactions.add(transactions.indexOf(receivedTransaction), updatedTransaction);
        return ResponseEntity.created(URI.create("/" + id)).body(updatedTransaction);

    }


//      DELETE /transactions/{id} - deletes the transaction with id
    public ResponseEntity<Transaction> deleteTransactionById(Integer id) {
        List<Transaction> transactions = transactionRepository.getTransactions();
        ResponseEntity<Transaction> transactionToBeDeleted = ResponseEntity.ok(transactions.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new));
        Transaction transactionToDelete = transactionToBeDeleted.getBody();
        transactions.remove(transactionToDelete);
        return transactionToBeDeleted;
    }


}
