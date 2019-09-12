package com.revature.dao;

import java.util.List;

import com.revature.model.Department;

public interface DepartmentDao {
	
	public List<Department> getDepartments();
	public Department getDepartmentById(int id);
	public int createDepartment(Department d);
	public Department createDepartmentWithFunction(Department d);
	public int updateDepartment(Department d);
	public void increaseDepartmentBudgetWithFunction(Department d, double increase);
	public int deleteDepartment(int id);

}
