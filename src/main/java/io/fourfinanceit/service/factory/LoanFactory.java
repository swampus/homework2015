package io.fourfinanceit.service.factory;

import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanApplication;
import io.fourfinanceit.model.ennum.LoanStatus;
import org.springframework.stereotype.Service;

@Service
public class LoanFactory {

	public Loan createLoanFromLoanApplication(LoanApplication loanApplication, long loanAmountWithInterest) {
		Loan loan = new Loan();
		loan.setLoanStatus(LoanStatus.ACTIVE);
		loan.setPhoneNum(loanApplication.getPhoneNumber());
		loan.setAmount(loanAmountWithInterest);
		loan.setTermInDays(loanApplication.getDayCountTerm());
		return loan;
	}

	public Loan createNewExtendedLoan(Loan oldLoan, long extendedLoanAmountWithInterest, long additionalDays) {
		Loan loan = new Loan();
		loan.setLoanStatus(LoanStatus.EXTENDED);
		loan.setPhoneNum(oldLoan.getPhoneNum());
		loan.setAmount(oldLoan.getAmount() + extendedLoanAmountWithInterest);
		loan.setTermInDays(oldLoan.getTermInDays() + additionalDays);
		return loan;
	}

}
