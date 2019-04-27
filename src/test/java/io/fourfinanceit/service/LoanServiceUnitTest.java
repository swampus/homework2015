package io.fourfinanceit.service;

import io.fourfinanceit.model.Loan;
import io.fourfinanceit.resource.repository.LoanRepository;
import io.fourfinanceit.service.factory.LoanFactory;
import io.fourfinanceit.service.validator.LoanValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceUnitTest {

	@Mock
	private LoanRepository loanRepository;

	@Mock
	private LoanValidator loanValidator;

	@Mock
	private LoanInterestCalculator loanInterestCalculator;

	@Mock
	private LoanFactory loanFactory;

	@InjectMocks
	private LoanService loanService;

	@Test
	public void closeLoan() throws Exception {
		Loan loan = new Loan();
		Long ln = (long) 1;

		when(loanRepository.findOne(ln)).thenReturn(loan);
		when(loanValidator.checkLoanExistsAndIsActive(loan)).thenReturn(loan);

		loanService.closeLoan(ln);
		verify(loanValidator, times(1))
				.checkLoanExistsAndIsActive(loan);
		verify(loanRepository, times(1))
				.findOne(ln);

		verify(loanRepository, times(1))
				.save(loan);
		verifyNoMoreInteractions(loanRepository, loanValidator);

	}

	@Test
	public void extendLoan() throws Exception {
		Loan loan = new Loan();
		loan.setAmount((long) 10);
		Loan extendedLoan = new Loan();
		Long ln = (long) 1;
		Long uLong = (long) 42;
		Long uAmount = (long) 10;

		when(loanRepository.findOne(ln)).thenReturn(loan);
		when(loanValidator.checkLoanExistsAndIsActive(loan)).thenReturn(loan);

		when(loanInterestCalculator.calculateExtendedLoanAmountWithInterests(uLong, 10))
				.thenReturn(uAmount);

		when(loanFactory.createNewExtendedLoan(loan, uAmount, uAmount)).thenReturn(extendedLoan);

		loanService.extendLoan(ln, 10);

		verify(loanValidator, times(1))
				.checkLoanExistsAndIsActive(loan);
		verify(loanRepository, times(1))
				.findOne(ln);
		verify(loanRepository, times(1))
				.save(loan);

		verify(loanInterestCalculator, times(1))
				.calculateExtendedLoanAmountWithInterests(uAmount, 10);

		verify(loanFactory, times(1))
				.createNewExtendedLoan(loan, 0, uAmount);

		verifyNoMoreInteractions(loanValidator, loanInterestCalculator, loanFactory);


	}


}