package com.revature.ers.services;

import com.revature.ers.models.UserAccount;

public interface ReimbursementService {

	void submitReimbursement(UserAccount userAccount, Double amount);
}
