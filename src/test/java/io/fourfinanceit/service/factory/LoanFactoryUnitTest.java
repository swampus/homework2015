package io.fourfinanceit.service.factory;

import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanApplication;
import io.fourfinanceit.model.ennum.LoanStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoanFactoryUnitTest {

	private LoanFactory loanFactory = new LoanFactory();

	@Test
	public void createLoanFromLoanApplication() throws Exception {
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setClosed(false);
		loanApplication.setAmount((long) 10);
		loanApplication.setDayCountTerm((long) 11);
		loanApplication.setPhoneNumber("23");
		loanApplication.setRemoteIp("ip");
		loanApplication.setTimestampDayCreatedAt((long) 10);

		Loan loan = loanFactory.createLoanFromLoanApplication(loanApplication, 10);
		assertEquals(LoanStatus.ACTIVE, loan.getLoanStatus());
		assertEquals((long) 10, loan.getAmount(), 0);
		assertEquals(11, loan.getTermInDays(), 0);
		assertEquals("23", loan.getPhoneNum());

	}

	@Test
	public void createNewExtendedLoan() throws Exception {
		Loan loan = new Loan();
		loan.setAmount((long) 10);
		loan.setTermInDays((long) 11);
		loan.setPhoneNum("10");
		Loan newLoan = loanFactory.createNewExtendedLoan(loan, 10, (long) 10);

		assertEquals(LoanStatus.EXTENDED, newLoan.getLoanStatus());
		assertEquals((long) 20, newLoan.getAmount(), 0);
		assertEquals(21, newLoan.getTermInDays(), 0);
		assertEquals("10", newLoan.getPhoneNum());
	}

}