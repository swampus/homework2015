package io.fourfinanceit.service;

import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.ennum.LoanStatus;
import io.fourfinanceit.resource.criteria.LoanCriteria;
import io.fourfinanceit.resource.repository.LoanRepository;
import io.fourfinanceit.service.factory.LoanFactory;
import io.fourfinanceit.service.validator.LoanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanValidator loanValidator;
    private final LoanInterestCalculator loanInterestCalculator;
    private final LoanFactory loanFactory;
    private final LoanCriteria loanCriteria;

    @Autowired
    public LoanService(LoanRepository loanRepository, LoanValidator loanValidator,
                       LoanInterestCalculator loanInterestCalculator, LoanFactory loanFactory,
                       LoanCriteria loanCriteria) {
        this.loanRepository = loanRepository;
        this.loanValidator = loanValidator;
        this.loanInterestCalculator = loanInterestCalculator;
        this.loanFactory = loanFactory;
        this.loanCriteria = loanCriteria;
    }

    public void closeLoan(Long loanId) {
        findAndCloseLoanIfPossible(loanId);
    }

    public void extendLoan(Long loanId, int daysInAddition) {
        Loan loan = findAndCloseLoanIfPossible(loanId);
        Loan extendedLoan = loanFactory.createNewExtendedLoan(loan, loanInterestCalculator
                .calculateExtendedLoanAmountWithInterests(loan.getAmount(), daysInAddition), daysInAddition);
        loanRepository.save(extendedLoan);
    }

    public Loan getLoanById(Long loanId) {
        return loanRepository.findOne(loanId);
    }

    public List<Loan> getAllLoanByPhoneNum(String phoneNum) {
        return loanCriteria.getAllLoanByPhoneNum(phoneNum);
    }


    private Loan findAndCloseLoanIfPossible(Long loanId) {
        Loan loan = loanValidator.checkLoanExistsAndIsActive(loanRepository.findOne(loanId));
        loan.setLoanStatus(LoanStatus.CLOSED);
        loanRepository.save(loan);
        return loan;
    }

}
