package com.revatue;

import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.ReceiptDaoImpl;
import com.revature.daos.ReceiptsDao;
import com.revature.models.Employee;
import com.revature.models.Receipts;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Test Employee DAO impl
		EmployeeDao e = new EmployeeDaoImpl();
		
		List<Employee> employees = e.getEmployees();
		
		for(Employee em : employees) {
			System.out.println(em);
		}
		
		System.out.println(e.getUsernames());

	** Test Receipts Dao
	*
		ReceiptsDao rd = new ReceiptDaoImpl();
		
		List<Receipts> receipts = rd.getReceiptsByEmployeeId(2);
		
		for(Receipts rc : receipts) {
			System.out.println(rc);
		}
		*/
		// login functionality test
//		
		EmployeeDao e = new EmployeeDaoImpl();
//		
		System.out.println(e.getEmployees());
//				
//		EmployeeDao e = new EmployeeDaoImpl();
//	
//		List<Employee> employees = e.getEmployees();
//		
//		for(Employee em : employees) {
//			System.out.println(em);
//		}
//		
		
		
	}

}
