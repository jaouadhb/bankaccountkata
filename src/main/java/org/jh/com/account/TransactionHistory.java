package org.jh.com.account;

import org.jh.com.transaction.Transaction;
import org.jh.com.transaction.TransactionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.jh.com.transaction.TransactionType.DEPOSIT;

public class TransactionHistory {

    List<Transaction> transactions;

    public TransactionHistory(){
        transactions = new ArrayList<>();
    }

    public void addNewTransaction(Transaction transaction){
        if(DEPOSIT.equals(transaction.getTransactionType()))
        {
            transactions.add(transaction);
        }
    }

    public List<Transaction> getAllTransactionsHistory()
    {
        return Collections.unmodifiableList(transactions);
    }
}
