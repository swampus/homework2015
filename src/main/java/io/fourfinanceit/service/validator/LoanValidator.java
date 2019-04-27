package io.fourfinanceit.service.validator;

import io.fourfinanceit.exception.ForFinanceLoanDoNotExistsOrNotActive;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.ennum.LoanStatus;
import org.springframework.stereotype.Component;

@Component
public class LoanValidator implements Validator {

	public io.fourfinanceit.model.Loan checkLoanExistsAndIsActive(Loan loan) {
		if (loan == null || loan.getLoanStatus().equals(LoanStatus.CLOSED)) {
			throw new ForFinanceLoanDoNotExistsOrNotActive("Loan is not exists or is closed");
		}
		return loan;
	}

}
