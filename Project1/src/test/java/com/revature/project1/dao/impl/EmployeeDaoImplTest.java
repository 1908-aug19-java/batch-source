package com.revature.project1.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.dao.impl.EmployeeDaoImpl;
import com.revature.model.Employee;

public class EmployeeDaoImplTest {
	EmployeeDao ed = new EmployeeDaoImpl();
	
	@Test
	public void testGetEmployeeByUN() {
		Employee e = new Employee(1, "yinyu", "chen", "yinyu", "yyc", 655, "99 99ave");
		assertEquals(e, ed.getEmployeeByUN("yinyu"));
	}
	@Test
	public void testNotexistEmployee() {
		Employee e = new Employee(4, "usern", "password", "first", "last", 343925, "address");
		assertNotEquals(e, ed.getEmployeeByUN("use"));
	}
	@Test
	public void testUpdateEmployee() {
		Employee e = new Employee(4, "testing", "testing", "testing", "testing", 3333, "address");
		assertNotEquals(2, ed.updateInfo(e));
	}
	
	
}
