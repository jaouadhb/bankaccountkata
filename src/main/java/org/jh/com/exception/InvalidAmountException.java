package org.jh.com.exception;

public class InvalidAmountException extends TransactionException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
