package org.jh.com.transaction;

import org.jh.com.exception.InvalidAmountException;
import org.jh.com.exception.TransactionException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionTest {

    Transaction transaction;


    @Test
    public void should_create_transaction_instance()
    {
        transaction = Transaction.build(BigDecimal.valueOf(100L), TransactionType.WITHDRAWAL, Instant.now());
        Assert.assertEquals(BigDecimal.valueOf(100L),transaction.getAmount());
        Assert.assertEquals(TransactionType.WITHDRAWAL,transaction.getTransactionType());
    }

    @Test(expected = InvalidAmountException.class)
    public void should_throw_exception_negative_amount(){
        transaction = Transaction.build(BigDecimal.valueOf(-100L), TransactionType.WITHDRAWAL, Instant.now());
    }

    @Test(expected = InvalidAmountException.class)
    public void should_throw_exception_null_amount(){
        transaction = Transaction.build(null, TransactionType.WITHDRAWAL, Instant.now());
    }

    @Test(expected = TransactionException.class)
    public void should_throw_exception_null_transaction_type(){
        transaction = Transaction.build(BigDecimal.valueOf(100L), null, Instant.now());
    }

    @Test(expected = TransactionException.class)
    public void should_throw_exception_null_transaction_time(){
        transaction =   Transaction.build(BigDecimal.valueOf(-100L), TransactionType.WITHDRAWAL, null);
    }


    @Test
    public void should_return_the_amount_to_substract()
    {
        transaction = Transaction.build(BigDecimal.valueOf(100L), TransactionType.WITHDRAWAL, Instant.now());
        BigDecimal opertionalAmount = transaction.getSignedAmount();
        Assert.assertEquals(BigDecimal.valueOf(-100),opertionalAmount);
    }

    @Test
    public void should_return_the_amount_to_add()
    {
        transaction = Transaction.build(BigDecimal.valueOf(100L), TransactionType.DEPOSIT, Instant.now());
        BigDecimal opertionalAmount = transaction.getSignedAmount();
        Assert.assertEquals(BigDecimal.valueOf(100),opertionalAmount);
    }

    @Test(expected = TransactionException.class)
    public void should_throw_exception_of_invalid_type()
    {
        transaction = Transaction.build(BigDecimal.valueOf(100L), null, Instant.now());
        BigDecimal opertionalAmount = transaction.getSignedAmount();
    }
}
