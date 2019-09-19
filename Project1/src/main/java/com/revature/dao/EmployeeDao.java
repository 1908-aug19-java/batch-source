package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	public List<Employee> getEmployees();
	public int newEmployee(Employee e);
	public Employee getEmployeeByUN(String UN);
	public int updateInfo(Employee e);
}
