package com.revature.ers.dao;

import java.util.List;
import java.util.Optional;

import com.revature.ers.models.Reimbursement;

public interface ReimbursementDAO {

	Optional<Reimbursement> findById(long id);

	List<Reimbursement> findAll();

	Long save(Reimbursement reimbursement);

	void update(Reimbursement reimbursement);

	void delete(Reimbursement reimbursement);
}
