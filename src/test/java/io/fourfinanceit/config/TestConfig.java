package io.fourfinanceit.config;

import com.google.common.collect.ImmutableList;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanApplication;
import io.fourfinanceit.model.ennum.LoanStatus;
import io.fourfinanceit.service.LoanApplicationService;
import io.fourfinanceit.service.LoanService;
import io.fourfinanceit.service.utils.TimeUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class TestConfig {

	@Bean
	public TimeUtils timeUtils() {
		TimeUtils timeUtils = mock(TimeUtils.class);
		when(timeUtils.getCurrentTimestampInDays()).thenReturn((long) 10000);
		return timeUtils;
	}

	@Bean
	public LoanService loanService() {

		LoanService loanService = mock(LoanService.class);
		when(loanService.getAllLoanByPhoneNum("33")).thenReturn(createLoans());
		Loan loan = createLoan();
		when(loanService.getLoanById((long) 1)).thenReturn(loan);

		return loanService;
	}

	@Bean
	public LoanApplicationService loanApplicationService() {

		LoanApplicationService loanApplicationService = mock(LoanApplicationService.class);
		when(loanApplicationService.getAllLoanApplicationByPhoneNum("33"))
				.thenReturn(ImmutableList.of(createLoanApplication()));

		when(loanApplicationService.getLoanApplicationById((long) 1)).thenReturn(createLoanApplication());

		return loanApplicationService;
	}

	private LoanApplication createLoanApplication() {
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setAmount((long) 10);
		loanApplication.setDayCountTerm((long) 10);
		loanApplication.setId((long) 1);
		return loanApplication;
	}


	private Loan createLoan() {
		Loan loan = new Loan();
		loan.setAmount((long) 10);
		loan.setTermInDays((long) 20);
		loan.setPhoneNum("33");
		loan.setLoanStatus(LoanStatus.ACTIVE);
		return loan;
	}

	private ImmutableList<Loan> createLoans() {
		Loan loan1 = createLoan();

		Loan loan2 = new Loan();
		loan2.setAmount((long) 5);
		loan2.setTermInDays((long) 25);
		loan2.setPhoneNum("33");

		loan2.setLoanStatus(LoanStatus.ACTIVE);
		Loan loan3 = new Loan();
		loan3.setAmount((long) 11);
		loan3.setTermInDays((long) 22);
		loan3.setPhoneNum("33");
		loan3.setLoanStatus(LoanStatus.CLOSED);


		return ImmutableList.of(loan1, loan2, loan3);
	}
}
