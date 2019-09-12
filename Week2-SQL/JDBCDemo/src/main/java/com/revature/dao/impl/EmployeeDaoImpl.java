package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DepartmentDao;
import com.revature.dao.EmployeeDao;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	DepartmentDao dd = new DepartmentDaoImpl();
	
	@Override
	public List<Employee> getEmployees() {
		
		List<Employee> employees = new ArrayList<>();
		String sql = "select * from employee";
		
		try(Connection c = ConnectionUtil.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("empl_id"));
				e.setName(rs.getString("empl_name"));
				e.setMonthlySalary(rs.getDouble("monthly_salary"));
				e.setManagerId(rs.getInt("manager_id"));
				int deptId = rs.getInt("dept_id");
				if(deptId!=0) {
					e.setDepartment(new Department(deptId));
				}
				employees.add(e);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<Department> departments = dd.getDepartments();
		
		for(Employee e: employees) {
			if(e.getDepartment()!=null) {
				int deptId = e.getDepartment().getId();	
				if(deptId!=0) {
					for(Department d: departments) {
						if(deptId == d.getId()) {
							e.setDepartment(d);
						}
					}
				}
			}
		}
		
		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createEmployee(Employee e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Employee> getEmployeesAgain() {
		
		List<Employee> employees = new ArrayList<>();
		
		String sql = "select * from {oj employee left outer join department on (employee.dept_id=department.dept_id)}";
		
		try(Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()){
			
//			for(int i =0; i<rs.getMetaData().getColumnCount(); i++) {
//				System.out.println(rs.getMetaData().getColumnName(i+1));
//			}
			while(rs.next()) {
				
				Employee e = new Employee();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setMonthlySalary(rs.getDouble(3));
				e.setPostion(rs.getString(4));
				e.setManagerId(rs.getInt(5));
				
				int deptId = rs.getInt(6);
				
				if(deptId!=0) {
					Department d = new Department(deptId);
					d.setName(rs.getString(8));
					d.setMonthlyBudget(rs.getDouble(9));
					e.setDepartment(d);
				}
				
				employees.add(e);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

}
