package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	public static List<Employee> employees = new ArrayList<>();
	private static Logger log = Logger.getRootLogger();

	@Override
	public List<Employee> getEmployees() {
		String sql = "select * from employees";
		
		try(Connection c = ConnectionUtil.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				
				Employee e = new Employee();
				e.setEmpId(rs.getInt("empl_id"));
				e.setName(rs.getString("empName"));
				e.setEmail(rs.getString("email"));
				e.setPassword(rs.getString("pass"));
				e.setManagerId(rs.getInt("managerID"));

				employees.add(e);
			}
			
		} catch (SQLException e) {
			log.warn(e.getMessage());

		}	
		return employees;

	}
	public Employee getEmployeeByEmail(String email ) {
		String sql = "select * from employees where email = ?";
		Employee e = null;	
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1,email); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int empid=(rs.getInt("empl_id"));
				String name=(rs.getString("empName"));
				String pass=(rs.getString("pass"));
				int isManager =(rs.getInt("managerID"));


				e = new Employee(empid,name,email,pass,isManager);	
				 }
			rs.close();
		} catch (SQLException ex) {
			log.warn(ex.getMessage());
		}		
		return e;
	}
	@Override
	public Employee getEmployeeById(int id) {
		String sql = "select * from employees where empl_id = ?";
		Employee e = null;	
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1,id); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				//int empid=(rs.getInt("emplID"));
				String name=(rs.getString("empName"));
				String email=(rs.getString("email"));
				String pass=(rs.getString("pass"));
				int isManager =(rs.getInt("managerID"));
				e = new Employee(id,name,email,pass,isManager);	
				 }
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}		
		return e;
	}

	@Override
	public int createEmployee(Employee e) {

		int emplCreated = 0;
		String sql = "insert into employees (empName,email,pass,managerID) values (?,?,?,?)";		
		try(Connection con = ConnectionUtil.getConnection()){
				String[] primaryKeyValues = {"empl_id"};
				PreparedStatement ps = con.prepareStatement(sql,primaryKeyValues);
				
			ps.setString(1,e.getName());
			ps.setString(2,e.getEmail());
			ps.setString(3,e.getPassword());
			ps.setInt(4,0);//not manager
			
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				int empId = rs.getInt(1);
				e.setEmpId(empId);
			}
			//Service.users.add(user); // add user to list
			emplCreated = ps.executeUpdate();	
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}		
		employees.add(e);

		return emplCreated;
	}

	@Override
	public int updateEmployee(Employee e, String email) {
		int emplUpdated = 0;
		String sql = "update employees "
				+  " set empName = ? ,"
				+  " pass = ? "
				+ "where email = ? ";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){


			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, email);
	
			emplUpdated = ps.executeUpdate();
			con.close();
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}	
		return emplUpdated ;
	}
	public Employee validateEmployee(String email, String pass) {
		for (Employee e : employees) {
			if (e.getEmail().equals(email) && e.getPassword().equals(pass)) {
				return e;
			 }
		 }
		return null;
	}
}
