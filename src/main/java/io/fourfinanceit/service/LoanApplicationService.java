package io.fourfinanceit.service;

import io.fourfinanceit.model.LoanApplication;
import io.fourfinanceit.resource.criteria.LoanApplicationCriteria;
import io.fourfinanceit.resource.repository.LoanApplicationRepository;
import io.fourfinanceit.resource.repository.LoanRepository;
import io.fourfinanceit.service.factory.LoanFactory;
import io.fourfinanceit.service.utils.TimeUtils;
import io.fourfinanceit.service.validator.LoanApplicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplicationService {


    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanRepository loanRepository;
    private final TimeUtils timeUtils;
    private final LoanApplicationValidator loanApplicationValidator;
    private final LoanInterestCalculator loanInterestCalculator;
    private final LoanFactory loanFactory;
    private final LoanApplicationCriteria loanApplicationCriteria;

    @Autowired
    public LoanApplicationService(
            LoanApplicationRepository loanApplicationRepository,
            LoanRepository loanRepository, TimeUtils timeUtils,
            LoanApplicationValidator loanApplicationValidator,
            LoanInterestCalculator loanInterestCalculator, LoanFactory loanFactory,
            LoanApplicationCriteria loanApplicationCriteria) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.loanRepository = loanRepository;
        this.timeUtils = timeUtils;
        this.loanApplicationValidator = loanApplicationValidator;
        this.loanInterestCalculator = loanInterestCalculator;
        this.loanFactory = loanFactory;
        this.loanApplicationCriteria = loanApplicationCriteria;
    }

    public void saveLoanApplication(LoanApplication loanApplication) {

        validate(loanApplication);

        loanApplication.setTimestampDayCreatedAt(timeUtils.getCurrentTimestampInDays());
        loanApplicationRepository.save(loanApplication);
    }

    private void validate(LoanApplication loanApplication) {
        loanApplicationValidator.checkContainsNullField(
                loanApplication.getAmount(),
                loanApplication.getClosed(),
                loanApplication.getDayCountTerm(),
                loanApplication.getRemoteIp(),
                loanApplication.getPhoneNumber());

        loanApplicationValidator.checkPhoneNumValid(loanApplication.getPhoneNumber());

        loanApplicationValidator.checkMaxApplicationTodayWithSameIP(
                getAllLoanApplicationByIpToday(loanApplication.getRemoteIp()).size());

        loanApplicationValidator.checkAfterMidnightMaxAmount(loanApplication, timeUtils.getCurrentTimeInHours());

    }

    public LoanApplication getLoanApplicationById(Long id) {
        return loanApplicationRepository.findOne(id);
    }

    public List<LoanApplication> getAllLoanApplicationByPhoneNum(String phoneNum) {
        return loanApplicationCriteria.getAllLoanApplicationByPhoneNum(phoneNum);
    }

    private List<LoanApplication> getAllLoanApplicationByIpToday(String ip) {
        return loanApplicationCriteria.getAllLoanApplicationByIpToday(ip, timeUtils.getCurrentTimestampInDays());
    }

    public void rejectLoanApplication(Long id) {
        findAndCloseIfExists(id);
    }

    public void acceptLoanApplication(Long id) {
        LoanApplication loanApplication = findAndCloseIfExists(id);
        loanRepository.save(loanFactory.createLoanFromLoanApplication(loanApplication,
                loanInterestCalculator.calculateLoanAmountWithInterests(
                        loanApplication.getAmount(),
                        Math.toIntExact(loanApplication.getDayCountTerm()))));
    }

    private LoanApplication findAndCloseIfExists(Long id) {
        LoanApplication loanApplication = loanApplicationRepository.findOne(id);
        loanApplicationValidator.checkLoanApplicationExistsAndIsActive(loanApplication);
        loanApplication.setClosed(true);
        loanApplicationRepository.save(loanApplication);
        return loanApplication;
    }
}
