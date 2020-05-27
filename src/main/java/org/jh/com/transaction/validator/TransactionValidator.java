package org.jh.com.transaction.validator;

import org.jh.com.exception.InvalidAmountException;
import org.jh.com.exception.TransactionException;
import org.jh.com.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionValidator {
    public TransactionValidator(){
        /**
         * This is empty constructor
         */
    }
    public TransactionValidator validate(TransactionType transactionType)
    {
        if((!TransactionType.DEPOSIT.equals(transactionType))&&(!TransactionType.WITHDRAWAL.equals(transactionType))){
            throw new TransactionException("Transaction type not valid");
        }
        return this;
    }

    public TransactionValidator validate(BigDecimal amount)
    {
        if(amount==null || amount.compareTo(BigDecimal.ZERO)<0){
            throw new InvalidAmountException("The Amount should not be null or negative");
        }
        return this;
    }
    public TransactionValidator validate(Instant transactionTime)
    {
        if(transactionTime==null ){
            throw new TransactionException("The Transaction time should not be null");
        }
        return this;
    }
}
