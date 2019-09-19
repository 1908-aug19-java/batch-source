package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	public List<Reimbursement> getReimbursement(String status);
	public int addReimbursement (Employee em, int amount);
	public List<Reimbursement> emGetReimbursement(String status, int id);
	public int updateReimbursement (int rid, int mid, String resultd);
	public List<Reimbursement> getReimbursementByEmId(int id);
	
}
