package com.accenture.budgetapplication.controller.transaction;

import com.accenture.budgetapplication.model.ActionType;
import com.accenture.budgetapplication.model.Transaction;
import com.accenture.budgetapplication.repository.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactionFiltered(@RequestParam(required = false) String product,
                                                                       @RequestParam(required = false) ActionType type,
                                                                       @RequestParam(required = false) Double minAmount,
                                                                       @RequestParam(required = false) Double maxAmount) {
        return transactionService.getAllTransactionFiltered(product, type, minAmount, maxAmount);
    }

    //    GET /transactions/{id} - get transaction with id
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        return transactionService.getTransactionById(id);
    }

    //      POST /transactions - adds a new transaction
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction givenTransaction) {
        return transactionService.createTransaction(givenTransaction);
    }

    //    PUT  /transactions/{id} - replaces the transaction with id
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransactionDataById(@PathVariable Integer id,
                                                                 @RequestBody Transaction givenTransaction) {
        return transactionService.updateTransactionDataById(id, givenTransaction);
    }

    //    DELETE /transactions/{id} - deletes the transaction with id
    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransactionById(@PathVariable Integer id) {
        return transactionService.deleteTransactionById(id);
    }
}
