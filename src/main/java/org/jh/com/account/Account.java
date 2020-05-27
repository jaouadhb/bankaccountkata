package org.jh.com.account;

import org.jh.com.transaction.Transaction;
import org.jh.com.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public class Account {

    private TransactionHistory transactionHistory;

    public Account(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }


    public void deposit(BigDecimal amount) {
        Transaction transaction = Transaction.build(amount, TransactionType.DEPOSIT, Instant.now());
        transactionHistory.addNewTransaction(transaction);
    }

    public void withdrawal(BigDecimal amount) {
        Transaction transaction = Transaction.build(amount, TransactionType.WITHDRAWAL, Instant.now());
        transactionHistory.addNewTransaction(transaction);
    }


    public BigDecimal getBalanceFromTransactionHistory()
    {
      return transactionHistory.getBalanceFromTransactionHistory();
    }
}
