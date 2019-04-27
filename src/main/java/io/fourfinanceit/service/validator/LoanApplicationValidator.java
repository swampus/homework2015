package io.fourfinanceit.service.validator;

import io.fourfinanceit.exception.ForFinanceLoanDoNotExistsOrNotActive;
import io.fourfinanceit.exception.ForFinanceLoanValidationException;
import io.fourfinanceit.exception.ForFinanceMaxApplicationCountPerDayReach;
import io.fourfinanceit.exception.ForFinanceWrongParamProvidedException;
import io.fourfinanceit.model.LoanApplication;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationValidator implements Validator {

	private static final int START_OF_THE_DAY = 8;
	private static final int START_OF_THE_NIGHT = 0;

	@Value("${application.properties.max.possible.applications.per.day.count}")
	private int maxApplicationPerDayCount;

	@Value("${application.properties.max.possible.amount}")
	private int maxPossibleAmount;


	public void checkAfterMidnightMaxAmount(LoanApplication loanApplication, int currentTimeInHours) {
		if ((currentTimeInHours > START_OF_THE_NIGHT && currentTimeInHours < START_OF_THE_DAY)
				&& loanApplication.getAmount() == maxPossibleAmount) {
			throw new ForFinanceLoanValidationException("Max possible amount is requested after Midnight: "
					+ currentTimeInHours);
		}
	}

	public void checkMaxApplicationTodayWithSameIP(int applicationCount) {
		if (applicationCount > maxApplicationPerDayCount) {
			throw new ForFinanceMaxApplicationCountPerDayReach("Max possible amount is requested with same IP");
		}
	}


	public void checkPhoneNumValid(String number) {
		if (!NumberUtils.isDigits(number)) {
			throw new ForFinanceWrongParamProvidedException("Wrong phoneNumber: use: only digits ( "
					+ number + " ) ");
		}
	}

	public LoanApplication checkLoanApplicationExistsAndIsActive(LoanApplication loanApplication) {
		if (loanApplication == null || loanApplication.getClosed()) {
			throw new ForFinanceLoanDoNotExistsOrNotActive(" " + loanApplication);
		}
		return loanApplication;
	}


}
