package com.revature.project1.dao.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.impl.ReimbursementDaoImpl;
import com.revature.model.Employee;

public class ReimbursementDaoImplTest {
	ReimbursementDao rd = new ReimbursementDaoImpl();
	
	
	@Test
	public void testAddReimbursement() {
		Employee em = new Employee(1, "yinyu", "12345", "yinyu", "chen", 4353234, "fee asdfe ew");
		assertEquals(1, rd.addReimbursement(em, 300));
	}
	
	@Test
	public void updateReimbursement() {
		int i = rd.updateReimbursement(2, 3, "deny");
		assertEquals(1, i);
	}
	
	
	
}
