package org.jh.com.account;

import org.jh.com.transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PrintMockStrategy implements PrintStrategy {

    private List<String> line = new ArrayList<>();
    @Override
    public void print(Transaction transaction, BigDecimal currentBalance) {
        line.add(transaction.toString() +" | "+currentBalance);
    }

    public List<String> getLine() {
        return line;
    }

    @Override
    public void printHeader() {

    }
}
