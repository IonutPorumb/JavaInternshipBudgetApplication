package com.accenture.budgetapplication.repository.transaction;

import com.accenture.budgetapplication.model.ActionType;
import com.accenture.budgetapplication.model.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
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
        Stream<Transaction> transactionStream = transactionRepository.getTransactions().stream();
        if (product == null && type == null && minAmount == null && maxAmount == null) {
            return ResponseEntity.ok(transactionRepository.getTransactions());
        } else {
            if (product != null) {
                transactionStream = transactionStream.filter(t -> t.getProduct().equals(product));
            }
            if (type != null) {
                transactionStream = transactionStream.filter(t -> t.getType().equals(type));
            }
            if (minAmount != null) {
                transactionStream = transactionStream.filter(t -> t.getAmount() > minAmount);
            }
            if (maxAmount != null) {
                transactionStream = transactionStream.filter(t -> t.getAmount() < maxAmount);
            }
            return ResponseEntity.ok(transactionStream.toList());
        }
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
        Transaction savedTransaction = new Transaction(newId, transaction.getProduct(), LocalDateTime.now(),
                transaction.isConfirmed(), transaction.getUserId(), transaction.getType(), transaction.getAmount());
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
        Transaction updatedTransaction = new Transaction(id, transaction.getProduct(), LocalDateTime.now(),
                transaction.isConfirmed(), transaction.getUserId(), transaction.getType(), transaction.getAmount());
        transactions.set(transactions.indexOf(receivedTransaction), updatedTransaction);
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

    public List<Transaction> returnAllConfirmedTransactionsForPastMonth() {
        int beginDay = 1;
        int endDay = LocalDate.now().minusMonths(1)
                .with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        Month transactionMonth = LocalDate.now().minusMonths(1).getMonth();
        int transactionYear = LocalDate.now().minusMonths(1).getYear();
        LocalDateTime beginDate = LocalDateTime.of
                (transactionYear, transactionMonth, beginDay, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of
                (transactionYear, transactionMonth, endDay, 23, 59, 59);
        return transactionRepository.getTransactions().stream()
                .filter(t -> t.isConfirmed() &&
                        t.getCreatedAt().isAfter(beginDate) && t.getCreatedAt().isBefore(endDate))
                .toList();
    }

    public void confirmingAllUnconfirmedTransactions() {
        List<Transaction> confirmedTransactions = new java.util.ArrayList<>
                (transactionRepository.getTransactions().stream()
                        .filter(t -> !t.isConfirmed())
                        .toList());
        if (confirmedTransactions.size() == 0) {
            System.out.println("------------------");
        } else {
            for (Transaction t : confirmedTransactions) {
                int indexOfT = transactionRepository.getTransactions().indexOf(t);
                t.setConfirmed(true);
                transactionRepository.getTransactions()
                        .set(indexOfT, t);
                System.out.println(transactionRepository.getTransactions()
                        .get(indexOfT));
            }
        }
    }
}
