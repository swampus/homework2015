package io.fourfinanceit.exception;

public class ForFinanceLoanDoNotExistsOrNotActive extends RuntimeException {
	public ForFinanceLoanDoNotExistsOrNotActive(String message) {
		super(message);
	}
}
