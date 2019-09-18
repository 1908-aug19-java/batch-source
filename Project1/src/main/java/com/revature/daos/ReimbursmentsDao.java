package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursment;

public interface ReimbursmentsDao {

	
	public List<Reimbursment> getReimbursments();
	public  List<Reimbursment> getReimbursmenttsById(int id );
	public Reimbursment getReimbusmentsById(int id);
	public int createReimbusment(Reimbursment r);
	public int viewReimbusment(Reimbursment r);
	public int UpdateReimbursment(String status, int rqeimbId,int manager_id);
	//public void rejectReimbursment(Reimbusments r);
	//public void submittReimbursment(Reimbusments r);



	
}
