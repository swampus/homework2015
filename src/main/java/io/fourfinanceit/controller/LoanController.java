package io.fourfinanceit.controller;

import io.fourfinanceit.model.Loan;
import io.fourfinanceit.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(LoanController.LOAN + "s")
public class LoanController {

	static final String LOAN = "/loan";
	private final LoanService loanService;

	@Autowired
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@PutMapping(value = LOAN + "/close/{loanId}/days/{additionalDays}")
	@ResponseBody
	public void extendLoanByIdAndWeeks(@PathVariable("loanId") Long loanId,
	                                   @PathVariable("additionalDays") int additionalDays) {
		loanService.extendLoan(loanId, additionalDays);
	}


	@PutMapping(value = LOAN + "/close/{loanId}")
	@ResponseBody
	public void closeLoanById(@PathVariable("loanId") Long loanId) {
		loanService.closeLoan(loanId);
	}

	@GetMapping(value = LOAN + "/{loanId}")
	@ResponseBody
	public Loan getLoanById(@PathVariable("loanId") Long loanId) {
		return loanService.getLoanById(loanId);
	}

	@GetMapping(value = LOAN + "/phone_num/{phoneNum}")
	@ResponseBody
	public List<Loan> getAllLoanByPhoneNum(@PathVariable("phoneNum") String phoneNum) {
		return loanService.getAllLoanByPhoneNum(phoneNum);
	}

}
