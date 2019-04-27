package io.fourfinanceit.service.validator;

import io.fourfinanceit.exception.ForFinanceLoanDoNotExistsOrNotActive;
import io.fourfinanceit.exception.ForFinanceLoanValidationException;
import io.fourfinanceit.exception.ForFinanceMaxApplicationCountPerDayReach;
import io.fourfinanceit.exception.ForFinanceWrongParamProvidedException;
import io.fourfinanceit.model.LoanApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationValidatorUnitTest {

	private LoanApplicationValidator loanApplicationValidator = new LoanApplicationValidator();

	@Test
	public void checkAfterMidnightMaxAmount() throws Exception {
		Whitebox.setInternalState(loanApplicationValidator,
				"maxApplicationPerDayCount", 2);
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setAmount((long) 1);

		loanApplicationValidator.checkAfterMidnightMaxAmount(loanApplication, 10);
	}

	@Test
	public void checkAfterMidnightMaxAmountException() throws Exception {
		Whitebox.setInternalState(loanApplicationValidator,
				"maxPossibleAmount", 2);
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setAmount((long) 100);

		try {
			loanApplicationValidator.checkAfterMidnightMaxAmount(loanApplication, 10);
		} catch (ForFinanceLoanValidationException e) {
			assertEquals("asd", e.getMessage());
		}

	}

	@Test
	public void checkMaxApplicationTodayWithSameIP() throws Exception {
		Whitebox.setInternalState(loanApplicationValidator, "maxApplicationPerDayCount", 2);
		loanApplicationValidator.checkMaxApplicationTodayWithSameIP(1);
	}

	@Test
	public void checkMaxApplicationTodayWithSameIPException() throws Exception {
		Whitebox.setInternalState(loanApplicationValidator, "maxApplicationPerDayCount", 0);

		try {
			loanApplicationValidator.checkMaxApplicationTodayWithSameIP(1);
		} catch (ForFinanceMaxApplicationCountPerDayReach e) {
			assertEquals("Max possible amount is requested with same IP", e.getMessage());
		}

	}


	@Test
	public void checkPhoneNumValid() throws Exception {
		loanApplicationValidator.checkPhoneNumValid("2323");
	}

	@Test
	public void checkPhoneNumValidException() throws Exception {
		try {
			loanApplicationValidator.checkPhoneNumValid("dfsd");
			fail();
		} catch (ForFinanceWrongParamProvidedException e) {
			assertEquals("Wrong phoneNumber: use: only digits ( dfsd ) ", e.getMessage());
		}
	}


	@Test
	public void checkLoanApplicationExistsAndIsActive() throws Exception {

		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setClosed(false);
		loanApplication.setAmount((long) 10);
		loanApplication.setDayCountTerm((long) 11);
		loanApplication.setPhoneNumber("23");
		loanApplication.setRemoteIp("ip");
		loanApplication.setTimestampDayCreatedAt((long) 10);

		loanApplicationValidator.checkLoanApplicationExistsAndIsActive(loanApplication);
	}

	@Test
	public void checkLoanApplicationExistsAndIsActiveException() throws Exception {

		try {
			loanApplicationValidator.checkLoanApplicationExistsAndIsActive(null);
			fail();
		} catch (ForFinanceLoanDoNotExistsOrNotActive e) {
			assertEquals(" null", e.getMessage());
		}

	}


}