package org.jh.com.account;

import org.jh.com.commun.Constant;
import org.jh.com.exception.FailureOperationException;
import org.jh.com.transaction.Transaction;
import org.jh.com.transaction.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.jh.com.commun.Constant.THIS_OPERATION_CAN_T_BE_DONE;
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
                throw new FailureOperationException(Constant.THE_ACCOUNT_NOT_YET_INITIALIZED);
            }

            BigDecimal signedAmount = getBalanceFromTransactionHistory().add(transaction.getSignedAmount());
            if(signedAmount.compareTo(BigDecimal.ZERO)<0){
                throw new FailureOperationException(Constant.THIS_OPERATION_CAN_T_BE_DONE);
            }
        }
        transactions.add(transaction);
    }

    /**
     * This method is used to read the list of all transactions (read only)
     * @return unmodifiable list
     */
    public List<Transaction> getAllTransactionsHistory()
    {
        return Collections.unmodifiableList(transactions);
    }

    /**
     * This method return the balance of transactions
     * @return Balance
     */
    public BigDecimal getBalanceFromTransactionHistory()
    {
        BigDecimal lBalanceFromTransaction = getAllTransactionsHistory().stream().map(Transaction::getSignedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return lBalanceFromTransaction;
    }
}
