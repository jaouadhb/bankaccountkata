package org.jh.com.account;

import org.jh.com.commun.Constant;
import org.jh.com.transaction.Transaction;

import java.math.BigDecimal;

@FunctionalInterface
public interface PrintStrategy {
    public void print(Transaction transaction, BigDecimal currentBalance);

    default void printHeader(){
        System.out.println(Constant.HEADER_FORMAT);
    }
}
