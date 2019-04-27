package io.fourfinanceit.service;

import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanApplication;
import io.fourfinanceit.resource.repository.LoanApplicationRepository;
import io.fourfinanceit.resource.repository.LoanRepository;
import io.fourfinanceit.service.factory.LoanFactory;
import io.fourfinanceit.service.validator.LoanApplicationValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceUnitTest {

	@Mock
	private LoanApplicationRepository loanApplicationRepository;

	@Mock
	private LoanRepository loanRepository;

	@Mock
	private LoanApplicationValidator loanApplicationValidator;

	@Mock
	private LoanInterestCalculator loanInterestCalculator;

	@Mock
	private LoanFactory loanFactory;

	@InjectMocks
	private LoanApplicationService loanApplicationService;

	@Test
	public void acceptLoanApplication() throws Exception {
		LoanApplication loanApplication = new LoanApplication();
		Long loanId = (long) 1;
		Long amount = (long) 10;
		Long days = (long) 10;

		Loan loan = new Loan();

		loanApplication.setAmount(amount);
		loanApplication.setDayCountTerm(days);

		when(loanApplicationRepository.findOne(loanId)).thenReturn(loanApplication);
		when(loanApplicationValidator.checkLoanApplicationExistsAndIsActive(loanApplication))
				.thenReturn(loanApplication);

		when(loanInterestCalculator.calculateLoanAmountWithInterests(amount, 10)).thenReturn(amount);
		when(loanFactory.createLoanFromLoanApplication(loanApplication, amount)).thenReturn(loan);

		loanApplicationService.acceptLoanApplication(loanId);

		verify(loanApplicationRepository, times(1)).findOne(loanId);
		verify(loanApplicationValidator, times(1))
				.checkLoanApplicationExistsAndIsActive(loanApplication);
		verify(loanApplicationRepository, times(1)).save(loanApplication);

		verify(loanInterestCalculator, times(1))
				.calculateLoanAmountWithInterests(amount, 10);

		verify(loanFactory, times(1))
				.createLoanFromLoanApplication(loanApplication, amount);

		verifyNoMoreInteractions(loanApplicationRepository,
				loanApplicationValidator, loanApplicationRepository, loanInterestCalculator, loanFactory);


	}

}