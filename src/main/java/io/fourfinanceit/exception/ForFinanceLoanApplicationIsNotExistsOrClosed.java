package io.fourfinanceit.exception;

public class ForFinanceLoanApplicationIsNotExistsOrClosed extends RuntimeException {
	public ForFinanceLoanApplicationIsNotExistsOrClosed(String message) {
		super(message);
	}
}
