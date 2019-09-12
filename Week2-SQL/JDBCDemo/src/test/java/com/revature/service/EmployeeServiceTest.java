package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.dao.EmployeeDao;
import com.revature.model.Department;
import com.revature.model.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	
	// still using junit annotations
	
	@InjectMocks
	private EmployeeService es;
	
	@Mock 
	private EmployeeDao ed;
	
	@Before
	public void setUp() {
		Employee e5 = new Employee(5, "Charis Fike",3732, "Mkt rep", 2, new Department(3));
		//set up my when/thenReturn statements
		when(ed.getEmployeeById(5)).thenReturn(e5);
		
		when(ed.getEmployeeById(0)).thenReturn(null);
		when(ed.createEmployee(null)).thenReturn(0);
		
		Employee e6 = new Employee(6, "Lisa Johnson", 2000, "Admin Assist", 1, null);
		when(ed.createEmployee(e6)).thenReturn(1);
	
	}
	
	@Test 
	public void getEmployeeByValidId() {
		assertEquals(new Employee(5, "Charis Fike",3732, "Mkt rep", 2, new Department(3)), es.getEmployeeById(5));
	}
	
	
	@Test
	public void getEmployeeByInvalidId() {
		assertNull(es.getEmployeeById(0));
	}
	
	@Test
	public void testSuccessfulCreation() {
		Employee e6 = new Employee(6, "Lisa Johnson", 2000, "Admin Assist", 1, null);
		assertTrue(es.createEmployee(e6));
	}
	
	@Test
	public void testUnsuccessfulCreation() {
		assertFalse(es.createEmployee(null));
	}

}
