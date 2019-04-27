package io.fourfinanceit.service.validator;

import io.fourfinanceit.exception.ForFinanceLoanDoNotExistsOrNotActive;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.ennum.LoanStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoanValidatorUnitTest {

	private LoanValidator loanValidator = new LoanValidator();

	@Test
	public void checkLoanExistsAndIsActive() throws Exception {
		Loan loan = new Loan();
		loan.setAmount((long) 10);
		loan.setTermInDays((long) 11);
		loan.setPhoneNum("10");
		loan.setLoanStatus(LoanStatus.ACTIVE);
		loanValidator.checkLoanExistsAndIsActive(loan);
	}

	@Test
	public void checkLoanExistsAndIsActiveException() throws Exception {
		try {
			loanValidator.checkLoanExistsAndIsActive(null);
			fail();
		} catch (ForFinanceLoanDoNotExistsOrNotActive e) {
			assertEquals("Loan is not exists or is closed", e.getMessage());
		}

	}
}