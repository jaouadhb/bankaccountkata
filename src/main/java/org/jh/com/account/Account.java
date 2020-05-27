package org.jh.com.account;

import org.jh.com.transaction.Transaction;
import org.jh.com.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.function.Supplier;

public class Account {

    private TransactionHistory transactionHistory;
    private Supplier<Instant>  dateOfTransaction;

    public Account(TransactionHistory transactionHistory,Supplier dateOfTransaction) {
        this.transactionHistory = transactionHistory;
        this.dateOfTransaction  = dateOfTransaction;
    }

    public void deposit(BigDecimal amount) {
        Transaction transaction = Transaction.build(amount, TransactionType.DEPOSIT, dateOfTransaction.get());
        transactionHistory.addNewTransaction(transaction);
    }

    public void withdrawal(BigDecimal amount) {
        Transaction transaction = Transaction.build(amount, TransactionType.WITHDRAWAL, dateOfTransaction.get());
        transactionHistory.addNewTransaction(transaction);
    }

    public void printStatement(PrintStrategy printStrategy){
            AccountStatementPrinter.printStatement(transactionHistory.getAllTransactionsHistory(),printStrategy);
    }

    public BigDecimal getBalanceFromTransactionHistory()
    {
      return transactionHistory.getBalanceFromTransactionHistory();
    }
}
