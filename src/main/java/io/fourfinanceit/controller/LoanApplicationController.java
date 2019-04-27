package io.fourfinanceit.controller;

import io.fourfinanceit.model.LoanApplication;
import io.fourfinanceit.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LoanApplicationController {

	private static final String ACCEPT_APPLICATION_JSON = "Accept=application/json";
	private static final String APP_URL = "applications";

	private final LoanApplicationService loanApplicationService;

	@Autowired
	public LoanApplicationController(LoanApplicationService loanApplicationService) {
		this.loanApplicationService = loanApplicationService;
	}


	@PutMapping(value = "/" + APP_URL + "/new", headers = {ACCEPT_APPLICATION_JSON})
	@ResponseBody
	public void registerLoanApplication(@RequestBody LoanApplication loanApplication) {
		loanApplicationService.saveLoanApplication(loanApplication);
	}

	@PutMapping(value = "/" + APP_URL + "/reject/{loanApplicationId}")
	@ResponseBody
	public void rejectLoanApplication(@PathVariable("loanApplicationId") Long loanApplicationId) {
		loanApplicationService.rejectLoanApplication(loanApplicationId);
	}

	@PutMapping(value = "/" + APP_URL + "/accept/{loanApplicationId}")
	@ResponseBody
	public void acceptLoanApplication(@PathVariable("loanApplicationId") Long loanApplicationId) {
		loanApplicationService.acceptLoanApplication(loanApplicationId);
	}


	@GetMapping(value = "/" + APP_URL + "/id/{loanApplicationId}")
	@ResponseBody
	public LoanApplication getLoanApplicationById(@PathVariable("loanApplicationId") Long loanApplicationId) {
		return loanApplicationService.getLoanApplicationById(loanApplicationId);
	}

	@GetMapping(value = "/" + APP_URL + "/phone_num/{phoneNum}")
	@ResponseBody
	public List<LoanApplication> getAllLoanApplicationByPhoneNum(@PathVariable("phoneNum") String phoneNum) {
		return loanApplicationService.getAllLoanApplicationByPhoneNum(phoneNum);
	}


}
