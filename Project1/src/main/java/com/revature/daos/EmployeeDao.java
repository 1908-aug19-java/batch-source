package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	
	public List<Employee> getEmployees();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByEmail(String email );
	public int createEmployee(Employee e);
	public int updateEmployee(Employee e ,String email);
	public Employee validateEmployee(String email, String pass);
	//public int deleteEmployee(int id);

}
