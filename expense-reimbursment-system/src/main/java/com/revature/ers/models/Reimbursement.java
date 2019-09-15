package com.revature.ers.models;

import java.sql.Timestamp;
import java.time.Instant;

public class Reimbursement {

	private Long id;
	private Double amount;
	private String status;
	private Timestamp dateSubmitted;
	private Long userAccountId;
	private Long managerAccountId;

	public Reimbursement() {
		super();
	}

	public Reimbursement(Long id, Double amount, String status, Timestamp dateSubmitted, Long userAccountId,
			Long managerAccountId) {
		super();
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.dateSubmitted = dateSubmitted;
		this.userAccountId = userAccountId;
		this.managerAccountId = managerAccountId;
	}

	public Reimbursement(Double amount, String status, Long userAccountId, Long managerAccountId) {
		super();
		this.amount = amount;
		this.status = status;
		this.userAccountId = userAccountId;
		this.dateSubmitted = Timestamp.from(Instant.now());
		this.managerAccountId = managerAccountId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Long getManagerAccountId() {
		return managerAccountId;
	}

	public void setManagerAccountId(Long managerAccountId) {
		this.managerAccountId = managerAccountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}	
		return true;
	}

}
