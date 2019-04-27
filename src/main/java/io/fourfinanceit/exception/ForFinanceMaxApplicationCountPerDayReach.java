package io.fourfinanceit.exception;

public class ForFinanceMaxApplicationCountPerDayReach extends RuntimeException {
	public ForFinanceMaxApplicationCountPerDayReach(String message) {
		super(message);
	}
}
