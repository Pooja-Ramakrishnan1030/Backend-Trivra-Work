package com.tr.trivra.trivraEngine.exception;

public class TransactionLogException extends AbstractGraphqlException {
	private static final long serialVersionUID = 1L;
    public TransactionLogException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
