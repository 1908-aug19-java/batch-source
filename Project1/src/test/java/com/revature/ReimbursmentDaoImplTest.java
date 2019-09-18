package com.revature;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.daos.ReimbursmentDaoImpl;
import com.revature.models.Reimbursment;
import static org.junit.Assert.assertEquals;

public class ReimbursmentDaoImplTest 
{
	ReimbursmentDaoImpl  reimDao=new ReimbursmentDaoImpl ();
	List<Reimbursment> reimbursments = new ArrayList<>();

Reimbursment reimTest=new Reimbursment(6,4,"TRAVEL",300,"0",0);

@Before
public void beforeTest() {
	System.out.println(" JUnit Test Started");
}

@Test
public void getRequestByIdTest() {
	reimbursments=reimDao.getReimbursmenttsById(6);
	for (Reimbursment r :reimbursments) 
	{
		assertEquals(reimTest.getReason(),r.getReason());//Travel
		assertEquals(reimTest.getAmount(),r.getAmount(),0);//return 300
	}
}

	
	@Test
	public void createReimbursmentTest() {
		Reimbursment reimTest2=new Reimbursment(7,4,"Meeting",400,"0",0);

		assertEquals(1,reimDao.createReimbusment(reimTest2));//return 1 if created
		
	}
	@Test
	public void updateReimbursmentTest() {
		assertEquals(1,reimDao.UpdateReimbursment("1",6,0));//return 1 if updated to approved("1")
		
	}

	@After
	public void afterMethod() {
		System.out.println(" JUnit Test Finished ");
	}

}
