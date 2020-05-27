package org.jh.com.account;

import org.jh.com.transaction.Transaction;

import java.math.BigDecimal;

public class PrintConsoleStrategy implements PrintStrategy {

    @Override
    public void print(Transaction transaction, BigDecimal currentBalance) {
        System.out.println(transaction.toString() +" | "+currentBalance);
    }
}
