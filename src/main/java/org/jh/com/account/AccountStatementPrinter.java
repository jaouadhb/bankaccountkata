package org.jh.com.account;

import org.jh.com.transaction.Transaction;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;

public class AccountStatementPrinter {

    private AccountStatementPrinter(){}

    static void printStatement(List<Transaction> transactions, PrintStrategy printStrategy) {
        BigDecimal balance = BigDecimal.ZERO;
        printStrategy.printHeader();
        for (Transaction transaction:transactions) {
            balance = balance.add(transaction.getSignedAmount());
            printStrategy.print(transaction,balance);
        }
    }

}
