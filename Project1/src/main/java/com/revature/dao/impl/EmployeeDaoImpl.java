package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDao;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;


public class EmployeeDaoImpl implements EmployeeDao {
	private static Logger log = Logger.getRootLogger();

	@Override
	public List<Employee> getEmployees() {
		log.info("EmployeeDaoImpl execute getEmployees");
		String sql = "select * from project1.employeeacc full outer join project1.employeeinfo on employeeacc.eid = employeeinfo.iid";
		
		List<Employee> employees = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				int emId = rs.getInt("eid");
				String emUN = rs.getString("un");
				String emPW = rs.getString("pw");
				String emFirst = rs.getString("firstname");
				String emLast = rs.getString("lastname");
				long emphone = rs.getLong("phone");
				String emAddress = rs.getString("address");
				Employee d = new Employee(emId, emUN, emPW, emFirst, emLast, emphone, emAddress);
				employees.add(d);
			}
			
		} catch (SQLException e) {
			log.info(e);
		}
		
		return employees;
	
	} 

	@Override
	public int newEmployee(Employee em) {
		log.info("EmployeeDaoImpl execute newEmployees");
		int employeeAdded = 0;
		String sql = "insert into project1.employeeacc (un, pw) values (?, ?)";
		String sql2 = "insert into project1.employeeinfo (lastname, firstname, phone, address) values (?, ?, ?, ?)";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				PreparedStatement psinfo = c.prepareStatement(sql2)){
			
			ps.setString(1, em.getUsername());
			ps.setString(2, em.getPassword());
			psinfo.setString(1, em.getLastName());
			psinfo.setString(2, em.getFirstName());
			psinfo.setLong(3, em.getPhone());
			psinfo.setString(4, em.getAddress());
			
			employeeAdded += ps.executeUpdate();
			employeeAdded += psinfo.executeUpdate();
			
		} catch (SQLException e) {
			log.info(e);
		}
		
		return employeeAdded;
	}

	@Override
	public Employee getEmployeeByUN(String UN) {
		log.info("EmployeeDaoImpl execute getEmployeesByUN");
		String sql = "select * from project1.employeeacc a left join project1.employeeinfo e on a.eid = e.iid where a.un = (?)";
		Employee em = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, UN);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String emUN = rs.getString("un");
				int emId = rs.getInt("eid");
				String emPW = rs.getString("pw");
				String emFirst = rs.getString("firstname");
				String emLast = rs.getString("lastname");
				Long emphone = rs.getLong("phone");
				String emAddress = rs.getString("address");
				em = new Employee(emId, emUN, emPW, emFirst, emLast, emphone, emAddress);
			}
			rs.close();
		} catch (SQLException e) {
			log.info(e);		}
		
		return em;
	}

	@Override
	public int updateInfo(Employee em) {
		log.info("EmployeeDaoImpl execute updateInfo");
		String sql = "update project1.employeeinfo set firstname = ?, lastname = ?, phone = ?, address = ? where iid = ?";
		String sql2 = "update project1.reimbursement set employee = ? where employeeid = ?";
		int employeeUpdate = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				PreparedStatement ps2 = c.prepareStatement(sql2)){
				
				
				ps.setString(1, em.getFirstName());
				ps.setString(2, em.getLastName());
				ps.setLong(3, em.getPhone());
				ps.setString(4, em.getAddress());
				ps.setInt(5, em.getId());
				ps2.setString(1, em.getFirstName());
				ps2.setInt(2, em.getId());
			
				employeeUpdate += ps.executeUpdate();
				employeeUpdate += ps2.executeUpdate();
			
		} catch (SQLException e) {
			log.info(e);		}
		
		return employeeUpdate;
	}

}
