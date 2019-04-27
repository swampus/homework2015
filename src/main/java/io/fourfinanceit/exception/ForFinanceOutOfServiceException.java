package io.fourfinanceit.exception;

public class ForFinanceOutOfServiceException extends RuntimeException {
	public ForFinanceOutOfServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
