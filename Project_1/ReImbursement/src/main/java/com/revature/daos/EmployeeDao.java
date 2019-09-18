package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	
	
	public List<Employee> getEmployees();
	public Employee getEmployeeById(int id);
//	public int createEmployee(Employee e);
	public int updateEmployee(Employee old, Employee updated);
	public List<String> getUsernames();
	public int login(String username, String password); 
	public boolean checkIfManager(String username);
	public int changeUserPassword(int emp_id, String pass);
	
	
	
}
