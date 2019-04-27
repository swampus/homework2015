package io.fourfinanceit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoanInterestCalculatorUnitTest {

	private LoanInterestCalculator loanInterestCalculator = new LoanInterestCalculator();

	@Test
	public void calculateLoanAmountWithInterests() throws Exception {
		Whitebox.setInternalState(loanInterestCalculator,
				"loanWeekRateIncrease", 10);

		assertEquals(10,
				loanInterestCalculator.calculateLoanAmountWithInterests(10, 10));
	}

	@Test
	public void calculateExtendedLoanAmountWithInterests() throws Exception {
		Whitebox.setInternalState(loanInterestCalculator,
				"extendedWeekRateIncrease", 15);

		assertEquals(10,
				loanInterestCalculator.calculateExtendedLoanAmountWithInterests(10, 10));

	}

}