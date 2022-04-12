package com.example.Api.scheduler;


import com.example.Api.transaction.Transaction;
import com.example.Api.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class contains a Scheduler that will automatically check for transactions
 * to process in the data base on a daily base.
 */
@Component @Slf4j @RequiredArgsConstructor
public class TrxScheduler extends AbstractScheduler{

    private final TransactionService transactionService;

    /**
     * This method will process every transaction in the data base for which the date is already passed
     */
    @Scheduled(initialDelay = 5, fixedRate = days, timeUnit = TimeUnit.SECONDS)
    public void processAllTrx(){
        // No transaction will be processed on week ends
        if (isWeekend(LocalDateTime.now())){
            log.info("[SCHEDULED TASK] The process will start back on monday");
        }
        else{
            //Collecting transactions that are still with status = 0 in the data base
            List<Transaction> trxToProcess = transactionService.getTransactionToProcess(LocalDateTime.now());
            //Printing number of transactions to process
            log.info("[SCHEDULED TASK] Processing transactions (i={})", trxToProcess.size());

            for(int i = 0; i < trxToProcess.size(); i ++){
                Transaction transaction = trxToProcess.get(i);

                transactionService.performTransaction(transaction);
            }
            log.info("[SCHEDULED TASK] Completed all scheduled task");
        }
    }

    /**
     * This method only check if we are on weekend day
     * @param dateTime the dateTime we want to check
     * @return true if we are on a weekend day, else false
     */
    public boolean isWeekend(LocalDateTime dateTime){
        switch(dateTime.getDayOfWeek()){
            case FRIDAY:
                return dateTime.getHour() >= 22;
            case SATURDAY:
                return true;
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }
}