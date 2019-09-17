package com.revature.models;

public class Receipts {

	private int receipt_id;
	private double amount;
	private String note;
	private int employee_id;
	private String status;
	private String approving_manager;
	
	


	public String getApproving_manager() {
		return approving_manager;
	}


	public void setApproving_manager(String approving_manager) {
		this.approving_manager = approving_manager;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + employee_id;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + receipt_id;
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
		Receipts other = (Receipts) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (receipt_id != other.receipt_id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}


	public Receipts() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Receipts(int receipt_id, double amount, String note, int employee_id, String status) {
		super();
		this.receipt_id = receipt_id;
		this.amount = amount;
		this.note = note;
		this.employee_id = employee_id;
		this.status = status;
	}

	public Receipts(double amount, String note, int employee_id) {
		super();
		this.amount = amount;
		this.note = note;
		this.employee_id = employee_id;
	}
	
	public Receipts(String note, double amount, String approving_manager) {
		super();
		this.note = note;
		this.amount = amount;
		this.approving_manager = approving_manager;
	}
	
	

	public Receipts(double amount, String note, String status) {
		super();
		this.amount = amount;
		this.note = note;
		this.status = status;
	}


	public int getReceipt_id() {
		return receipt_id;
	}


	public void setReceipt_id(int receipt_id) {
		this.receipt_id = receipt_id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public int getEmployee_id() {
		return employee_id;
	}


	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}


	public String isApproved() {
		return status;
	}


	public void setApproved(String status) {
		this.status = status;
	}


	
	


	@Override
	public String toString() {
		return "Receipts [receipt_id=" + receipt_id + ", amount=" + amount + ", note=" + note + ", employee_id="
				+ employee_id + ", status=" + status + "]";
	}
	
	
	
	
	
}
