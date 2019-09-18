package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.revature.daos.ReimbursmentDaoImpl;
import com.revature.daos.ReimbursmentsDao;
import com.revature.models.Reimbursment;


public class ReeimbursmentServices {
	private final ReimbursmentsDao resDao = new ReimbursmentDaoImpl();

public List<Reimbursment> getReimbursements(HttpServletRequest request, HttpServletResponse response) {
		
		return resDao.getReimbursments();
	}

public List<Reimbursment> getReimbursementsById(int id,HttpServletRequest request, HttpServletResponse response) {
	
	return resDao.getReimbursmenttsById(id);
	//return resDao.getReimbursments();
}
}

