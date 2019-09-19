package com.revature.model;

public class Reimbursement {
	private int id;
	private int employeeId;
	private String employee;
	private String status;
	private int resolvedBy;
	private int amount;
	private String resultd;
	
	public Reimbursement(int employeeId, String employee, String status, int resolvedBy, int amount,
			String resultd) {
		super();
		this.employeeId = employeeId;
		this.employee = employee;
		this.status = status;
		this.resolvedBy = resolvedBy;
		this.amount = amount;
		this.resultd = resultd;
	}
	public Reimbursement(int id, int employeeId, String employee, String status, int resolvedBy, int amount,
			String resultd) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employee = employee;
		this.status = status;
		this.resolvedBy = resolvedBy;
		this.amount = amount;
		this.resultd = resultd;
	}
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", employeeId=" + employeeId + ", employee=" + employee + ", status="
				+ status + ", resolvedBy=" + resolvedBy + ", amount=" + amount + ", resultd=" + resultd + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + employeeId;
		result = prime * result + id;
		result = prime * result + resolvedBy;
		result = prime * result + ((resultd == null) ? 0 : resultd.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (id != other.id)
			return false;
		if (resolvedBy != other.resolvedBy)
			return false;
		if (resultd == null) {
			if (other.resultd != null)
				return false;
		} else if (!resultd.equals(other.resultd))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getResultd() {
		return resultd;
	}
	public void setResultd(String resultd) {
		this.resultd = resultd;
	}
	public Reimbursement() {
		super();
	}


}
