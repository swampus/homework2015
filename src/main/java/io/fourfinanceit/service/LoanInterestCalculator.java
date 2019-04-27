package io.fourfinanceit.service;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoanInterestCalculator {

	@Value("${application.properties.loan.default.rate.per.week}")
	private int loanWeekRateIncrease;

	@Value("${application.properties.loan.extended.rate.per.week}")
	private int extendedWeekRateIncrease;


	@VisibleForTesting
	long calculateLoanAmountWithInterests(long amount, int dayCount) {
		return Math.round(amount + loanWeekRateIncrease / 100 * calculateWeekCount(dayCount));
	}

	@VisibleForTesting
	long calculateExtendedLoanAmountWithInterests(long amount, int dayCount) {
		return amount + extendedWeekRateIncrease / 100 * calculateWeekCount(dayCount);
	}

	private long calculateWeekCount(int daysAmount) {
		return Math.max(1, daysAmount / 7);
	}

}
