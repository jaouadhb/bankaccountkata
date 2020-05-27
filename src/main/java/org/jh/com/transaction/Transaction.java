package org.jh.com.transaction;

import org.jh.com.exception.TransactionException;
import org.jh.com.transaction.validator.TransactionValidator;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
    private  BigDecimal amount;
    private  TransactionType transactionType;
    private  Instant transactionTime;

    private Transaction(BigDecimal amount, TransactionType transactionType, Instant transactionTime) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionTime = transactionTime;
    }

    public static Transaction build(BigDecimal amount, TransactionType transactionType, Instant transactionTime) {
        new TransactionValidator()
                .validate(amount)
                .validate(transactionType)
                .validate(transactionTime);

        return new Transaction(amount, transactionType, transactionTime);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Instant getTransactionTime() {
        return transactionTime;
    }
}
