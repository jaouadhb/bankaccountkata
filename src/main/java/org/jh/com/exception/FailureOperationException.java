package org.jh.com.exception;

public class FailureOperationException extends TransactionException {
    public FailureOperationException(String message) {
        super(message);
    }
}
