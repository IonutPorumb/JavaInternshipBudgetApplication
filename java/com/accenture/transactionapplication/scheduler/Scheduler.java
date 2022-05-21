package com.accenture.transactionapplication.scheduler;

import com.accenture.transactionapplication.model.Transaction;
import com.accenture.transactionapplication.service.TransactionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
public class Scheduler {
    private final TransactionService transactionService;

    public Scheduler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //                 s m h Md M Wd
    @Scheduled(cron = "0 0 9 1 * *")
    public void returnAllConfirmedTransactionsForPastMonth() {
        System.out.println("The following transactions where confirmed in the past month:============================");
        System.out.printf("Time of execution %s \n", LocalDateTime.now());
        for (Transaction t : transactionService.returnAllConfirmedTransactionsForPastMonth())
            System.out.println(t.toString());
    }

    //                 s m h Md M Wd
    @Scheduled(cron = "0 5 * * * *")
    public void confirmingAllUnconfirmedTransactions() {
        System.out.println("The confirm status was set to 'true' for following transactions:=========================");
        System.out.printf("Time of execution %s \n", LocalDateTime.now());
        transactionService.confirmingAllUnconfirmedTransactions();
    }
}
