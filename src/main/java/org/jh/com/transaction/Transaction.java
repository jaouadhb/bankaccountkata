package org.jh.com.transaction;

import org.jh.com.commun.Constant;
import org.jh.com.exception.TransactionException;
import org.jh.com.transaction.validator.TransactionValidator;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.jh.com.commun.Constant.SPLIT;

public class Transaction {
    private  BigDecimal amount;
    private  TransactionType transactionType;
    private  Instant transactionTime;

    private Transaction(BigDecimal amount, TransactionType transactionType, Instant transactionTime) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionTime = transactionTime;
    }

    public static Transaction build(BigDecimal amount, TransactionType transactionType, Instant transactionTime) {
        new TransactionValidator()
                .validate(amount)
                .validate(transactionType)
                .validate(transactionTime);

        return new Transaction(amount, transactionType, transactionTime);
    }


    /**
     * int the case of WITHDRAWAL we should return signed amount to calculate the balance
     * @return signedAmount
     */
    public BigDecimal getSignedAmount()
    {
        BigDecimal signedAmount = null;
        switch (getTransactionType()){
            case DEPOSIT:
                signedAmount = getAmount();
                break;
            case WITHDRAWAL:
                signedAmount = getAmount().negate();
                break;
            default:
                throw new TransactionException("The transaction type is not supported");
        }
        return signedAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Instant getTransactionTime() {
        return transactionTime;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(getTransactionType())
                .append(SPLIT)
                .append(formatDate(getTransactionTime(),ZoneId.of(Constant.EUROPE_PARIS)))
                .append(SPLIT)
                .append(getAmount()).toString();
    }

    public static String formatDate(Instant instant, ZoneId zoneId)
    {
        return LocalDateTime.ofInstant(instant,zoneId)
                .format(Constant.DATE_FORMATTER);
    }
}
