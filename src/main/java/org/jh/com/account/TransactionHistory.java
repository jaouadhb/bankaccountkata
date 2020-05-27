package org.jh.com.account;

import org.jh.com.exception.FailureOperationException;
import org.jh.com.transaction.Transaction;
import org.jh.com.transaction.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.jh.com.transaction.TransactionType.DEPOSIT;
import static org.jh.com.transaction.TransactionType.WITHDRAWAL;

public class TransactionHistory {

    List<Transaction> transactions;

    public TransactionHistory(){
        transactions = new ArrayList<>();
    }

    /**
     * this method is the principal to add new transaction
     * @param transaction : @{link Transaction}
     */
    public void addNewTransaction(Transaction transaction){

        if(WITHDRAWAL.equals(transaction.getTransactionType()))
        {
            if(transactions.isEmpty()){
                throw new FailureOperationException("The Account not yet initialized");
            }

            BigDecimal signedAmount = getBalanceFromTransactionHistory().add(transaction.getSignedAmount());
            if(signedAmount.compareTo(BigDecimal.ZERO)<0){
                throw new FailureOperationException("This operation can't be done");
            }
        }
        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactionsHistory()
    {
        return Collections.unmodifiableList(transactions);
    }

    public BigDecimal getBalanceFromTransactionHistory()
    {
        BigDecimal lBalanceFromTransaction = getAllTransactionsHistory().stream().map(Transaction::getSignedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return lBalanceFromTransaction;
    }
}
