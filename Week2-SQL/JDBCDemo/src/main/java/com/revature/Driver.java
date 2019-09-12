package com.revature;

import com.revature.dao.DepartmentDao;
import com.revature.dao.impl.DepartmentDaoImpl;
import com.revature.model.Department;

public class Driver {
	
	
	public static void main(String[] args) {
		/*
		try {
			Connection c = ConnectionUtil.getConnection();
			String driver = c.getMetaData().getDriverName();
			System.out.println(driver);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
		DepartmentDao dd = new DepartmentDaoImpl();
//		List<Department> departments = dd.getDepartments();
//		for(Department department: departments) {
//			System.out.println(department);
//		}
		
//		System.out.println(dd.getDepartmentById(25));
		
//		Department dept = new Department("Accounting", 8000);
//		int depts = dd.createDepartment(dept);
//		System.out.println(depts);
		
		
//		List<Department> departments = dd.getDepartments();
//		for(Department department: departments) {
//			System.out.println(department);
//		}
		
//		Department dept = new Department(16, "Accounting",9000);
//		Department dept2 = dd.getDepartmentById(16);
//		System.out.println(dept2);
//		dept2.setMonthlyBudget(9500);
//		System.out.println(dept2);
//		int depts = dd.updateDepartment(dept2);
//		System.out.println(depts);
		
//		int deletedRows = dd.deleteDepartment(16);
//		System.out.println(deletedRows);
		
//		EmployeeDao ed = new EmployeeDaoImpl();
//		List<Employee> employees = ed.getEmployeesAgain();
//		for(Employee e: employees) {
//			System.out.println(e);
//		}
		
//		Department salesDept = new Department(5);
//		dd.increaseDepartmentBudgetWithFunction(salesDept, 100);
		
		Department d = new Department("last department", 1000);
		Department result = dd.createDepartmentWithFunction(d);
		System.out.println(result);
		
	}

}
