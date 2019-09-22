package com.revature.models;

public class EmployeeReimbursments {
	

	private int empId;
	private String name;
	private String email;
	
	private int reqNum;
	private  String reason;
	private double amount;
	private  String status;
	private int ManagerId;
	
	public EmployeeReimbursments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeReimbursments(int empId, String name, String email, int reqNum, String reason, double amount,
			String status, int managerId) {
		super();
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.reqNum = reqNum;
		this.reason = reason;
		this.amount = amount;
		this.status = status;
		ManagerId = managerId;
	}



public int getEmpId() {
	return empId;
}


public void setEmpId(int empId) {
	this.empId = empId;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public int getReqNum() {
	return reqNum;
}


public void setReqNum(int reqNum) {
	this.reqNum = reqNum;
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
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + empId;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	EmployeeReimbursments other = (EmployeeReimbursments) obj;
	if (ManagerId != other.ManagerId)
		return false;
	if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (empId != other.empId)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
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


@Override
public String toString() {
	return "EmployeeReimbursments [empId=" + empId + ", name=" + name + ", email=" + email + ", reqNum=" + reqNum
			+ ", reason=" + reason + ", amount=" + amount + ", status=" + status + ", ManagerId=" + ManagerId + "]";
}

}