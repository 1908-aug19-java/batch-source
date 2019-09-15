package com.revature.ers.services;

import com.revature.ers.dao.ReimbursementDAO;
import com.revature.ers.dao.ReimbursementDAOimpl;
import com.revature.ers.models.UserAccount;

public class ReimbursementServiceImpl implements ReimbursementService {

	private static final ReimbursementDAO ReimbursementDAO = new ReimbursementDAOimpl();
	@Override
	public void submitReimbursement(UserAccount userAccount, Double amount) {
		
	}

}
