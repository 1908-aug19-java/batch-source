package com.revature.service;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.impl.EmployeeDaoImpl;
import com.revature.model.Employee;

public class EmployeeService {
	
	private EmployeeDao ed = new EmployeeDaoImpl();
	
	public List<Employee> getAllEmployees(){
		return ed.getEmployeesAgain();
	}
	
	public Employee getEmployeeById(int id) {
		return ed.getEmployeeById(id);
	}
	
	public boolean createEmployee(Employee e) {
		int employeesCreated = ed.createEmployee(e);
		if(employeesCreated!=0) {
			return true;
		}
		return false;
	}
	
	// update and delete methods

}
