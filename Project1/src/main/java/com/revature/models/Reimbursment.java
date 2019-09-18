package com.revature.models;

import java.io.Serializable;

public class Reimbursment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int reqNum;
	private int emplId;
	private  String reason;
	private double amount;
	private  String status;
	private int ManagerId;
	
	public Reimbursment() {
		super();
	}
	public Reimbursment(int reqNum, int emplId, String reason, double amount, String status, int managerId) {
		super();
		this.reqNum = reqNum;
		this.emplId = emplId;
		this.reason = reason;
		this.amount = amount;
		this.status = status;
		ManagerId = managerId;
	}
	@Override
	public String toString() {
		return "Reimbusments [reqNum=" + reqNum + ", emplId=" + emplId + ", reason=" + reason + ", amount=" + amount
				+ ", status=" + status + ", ManagerId=" + ManagerId + "]";
	}
	public int getReqNum() {
		return reqNum;
	}
	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}
	public int getEmplId() {
		return emplId;
	}
	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getManagerId() {
		return ManagerId;
	}
	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ManagerId;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + emplId;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + reqNum;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Reimbursment other = (Reimbursment) obj;
		if (ManagerId != other.ManagerId)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (emplId != other.emplId)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (reqNum != other.reqNum)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}	
}
