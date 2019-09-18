package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeService {
	
	private final EmployeeDao empDao = new EmployeeDaoImpl();

	
	public List<Employee> getAll(HttpServletRequest request, HttpServletResponse response){
		return empDao.getEmployees();
	}
	
	public Employee getById(int id) {
		return empDao.getEmployeeById(id);
	}
	
	public int create(Employee e) {
		return empDao.createEmployee(e);
	}
	

}
