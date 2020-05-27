package org.jh.com.account;

import org.assertj.core.api.Assertions;
import org.jh.com.commun.Constant;
import org.jh.com.exception.FailureOperationException;
import org.jh.com.transaction.Transaction;
import org.jh.com.transaction.TransactionType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionHistoryTest {

    TransactionHistory transactionHistory;
    @Before
    public void setUp()
    {
        transactionHistory = new TransactionHistory();

    }

    @Test
    public void add_new_transaction(){
        List<Transaction> transactions = Arrays.asList(
                Transaction.build(BigDecimal.valueOf(100), TransactionType.DEPOSIT, Instant.now()),
                Transaction.build(BigDecimal.valueOf(50), TransactionType.DEPOSIT, Instant.now()),
                Transaction.build(BigDecimal.valueOf(60), TransactionType.DEPOSIT, Instant.now())
        );

        transactions.stream().forEach(transaction -> {
            transactionHistory.addNewTransaction(transaction);
        });

        Assertions.assertThat(transactionHistory.getAllTransactionsHistory()).isEqualTo(transactions);
        Assertions.assertThat(transactionHistory.getBalanceFromTransactionHistory()).isEqualTo(BigDecimal.valueOf(210));
    }

    @Test
    public void throw_exception_when_withdrawal_on_empty_account(){
        Transaction build = Transaction.build(BigDecimal.valueOf(100), TransactionType.WITHDRAWAL, Instant.now());
        Assertions.assertThatThrownBy(() -> {
            transactionHistory.addNewTransaction(build);
        }).isInstanceOf(FailureOperationException.class).hasMessageContaining(Constant.THE_ACCOUNT_NOT_YET_INITIALIZED);
    }

    @Test
    public void throw_exception_when_withdrawal_is_more_then_balance(){
        Transaction tr1 = Transaction.build(BigDecimal.valueOf(100), TransactionType.DEPOSIT, Instant.now());
        Transaction tr2 = Transaction.build(BigDecimal.valueOf(140), TransactionType.WITHDRAWAL, Instant.now());
        transactionHistory.addNewTransaction(tr1);
        Assertions.assertThatThrownBy(() -> {
            transactionHistory.addNewTransaction(tr2);
        }).isInstanceOf(FailureOperationException.class).hasMessageContaining(Constant.THIS_OPERATION_CAN_T_BE_DONE);
    }
}

