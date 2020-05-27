package org.jh.com;

import org.jh.com.account.Account;
import org.jh.com.account.PrintConsoleStrategy;
import org.jh.com.account.PrintStrategy;
import org.jh.com.account.TransactionHistory;

import java.math.BigDecimal;
import java.time.Instant;

public class Application {
    public static void main(String[] args) {
        Account account = new Account(new TransactionHistory(),()-> Instant.now());
        account.deposit(BigDecimal.valueOf(130));
        account.deposit(BigDecimal.valueOf(70));
        account.withdrawal(BigDecimal.valueOf(200));


        account.printStatement(new PrintConsoleStrategy());
    }
}
