package org.jh.com.account;

import org.jh.com.transaction.Transaction;
import org.jh.com.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public class Account {

    private TransactionHistory transactionHistory;
    private BigDecimal totalBalance;

    public Account(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
        this.totalBalance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return totalBalance;
    }

    public void deposit(BigDecimal amount) {
        Transaction transaction = Transaction.build(amount, TransactionType.DEPOSIT, Instant.now());
        transactionHistory.addNewTransaction(transaction);
        totalBalance = totalBalance.add(amount);
    }
}
