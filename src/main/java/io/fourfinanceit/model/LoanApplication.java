package io.fourfinanceit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "loan_application")
@JsonIgnoreProperties(value = {"timestampDayCreatedAt", "id"},
		allowGetters = true)
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotNull
	@Column(name = "day_count_term")
	private Long dayCountTerm;

	@NotNull
	@Column
	private Long amount;

	@NotBlank
	@Column(name = "remote_ip")
	private String remoteIp;

	@NotNull
	@Column(name = "timestamp_day")
	private Long timestampDayCreatedAt;

	@NotNull
	@Column(name = "is_closed")
	private Boolean isClosed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getDayCountTerm() {
		return dayCountTerm;
	}

	public void setDayCountTerm(Long dayCountTerm) {
		this.dayCountTerm = dayCountTerm;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public Long getTimestampDayCreatedAt() {
		return timestampDayCreatedAt;
	}

	public void setTimestampDayCreatedAt(Long timestampDayCreatedAt) {
		this.timestampDayCreatedAt = timestampDayCreatedAt;
	}

	public Boolean getClosed() {
		return isClosed;
	}

	public void setClosed(Boolean closed) {
		isClosed = closed;
	}
}
