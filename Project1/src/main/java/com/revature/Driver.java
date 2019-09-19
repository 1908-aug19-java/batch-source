package com.revature;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.impl.EmployeeDaoImpl;
import com.revature.dao.impl.ReimbursementDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
		try {
			Connection c = ConnectionUtil.getConnection();
			String driver = c.getMetaData().getDriverName();
			System.out.println(driver);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		EmployeeDao dd = new EmployeeDaoImpl();
		List<Employee> departments = dd.getEmployees();
		for(Employee department: departments) {
			System.out.println(department);
		}
		
		Employee dept = new Employee( "usern", "password", "first", "last", 343925, "address");
		/*int depts = dd.newEmployee(dept);
		System.out.println(depts); */
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		System.out.println(rd.addReimbursement(dept, 1000));
		
		List <Reimbursement> r = rd.emGetReimbursement("resolved", 1);
		System.out.println(r);
		 
		
		}
}
