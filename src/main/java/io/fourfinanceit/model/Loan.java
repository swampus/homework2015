package io.fourfinanceit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.fourfinanceit.model.ennum.LoanStatus;

import javax.persistence.*;

@Entity
@Table(name = "loan")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
		allowGetters = true)
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "phone_num")
	private String phoneNum;

	@Column
	private Long amount;

	@Column(name = "loan_status")
	@Enumerated(EnumType.STRING)
	private LoanStatus loanStatus;

	@Column(name = "term_in_days")
	private Long termInDays;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Long getTermInDays() {
		return termInDays;
	}

	public void setTermInDays(Long termInDays) {
		this.termInDays = termInDays;
	}
}
