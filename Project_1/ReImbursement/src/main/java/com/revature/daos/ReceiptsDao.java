package com.revature.daos;

import java.util.List;

import com.revature.models.Receipts;

public interface ReceiptsDao {
	
	public int createReceipt(Receipts r);
	public List<Receipts> getReceipts();
	public Receipts getReceiptById(int id);
	public int deleteReceiptById(int id);
	public int approveReceipt(int rec_id, int emp_id);
	public int denyReceipt(int id);
	public List<Receipts> getReceiptsByEmployeeId(int id);
	public List<Receipts> getApprovedReceiptsByEmployeeId(int emp_id);
	public List<Receipts> getDeniedReceiptsByEmployeeId(int emp_id);
	public List<Receipts> getAllPendingReceipts();
	public List<Receipts> getAllApprovedReceiptsForManager();
}
