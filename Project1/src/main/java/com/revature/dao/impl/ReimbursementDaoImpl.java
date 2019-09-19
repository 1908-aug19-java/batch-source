package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDao;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{
	private static Logger log = Logger.getRootLogger();


	@Override
	public List<Reimbursement> getReimbursement(String status) {
		log.info("ReimbursementDaoImpl execute getReimbursement");
		List <Reimbursement> reimbursements = new ArrayList<>();
		String sql = "select * from project1.reimbursement where status = ?";
		Reimbursement r = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				int rid = rs.getInt("id");
				String employee = rs.getString("employee");
				String statu = rs.getString("status");
				int eid = rs.getInt("employeeid");
				int resolvedby = rs.getInt("resolvedby");
				int amount = rs.getInt("amount");
				String resultd = rs.getString("resultd");
				r = new Reimbursement(rid, eid, employee, statu, resolvedby, amount, resultd);
				reimbursements.add(r);
			}
			rs.close();
		} catch (SQLException e) {
			log.info(e);
		}
		
		return reimbursements;
	}



	@Override
	public List<Reimbursement> emGetReimbursement(String status, int id) {
		log.info("ReimbursementDaoImpl execute emGetReimbursement");
		List <Reimbursement> reimbursements = new ArrayList<>();
		String sql = "select * from project1.reimbursement where employeeid = ? and status = ?";
		Reimbursement r = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ps.setString(2, status);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt("id");
				String employee = rs.getString("employee");
				String statu = rs.getString("status");
				int eid = rs.getInt("employeeid");
				int resolvedby = rs.getInt("resolvedby");
				String resultd = rs.getString("resultd");
				int amount = rs.getInt("amount");
				r = new Reimbursement(rid, eid, employee, statu, resolvedby, amount, resultd);
				reimbursements.add(r);
			}
			rs.close();
		} catch (SQLException e) {
			log.info(e);
		}
		
		return reimbursements;
	}

	@Override
	public int updateReimbursement(int rid, int mid, String result) {
		log.info("ReimbursementDaoImpl execute updateReimbursement");

		int statusUpdated = 0;
		String sql = "update project1.reimbursement set status = ?, resolvedby = ?, resultd =? where id = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			
			ps.setString(1, "resolved");
			ps.setString(3, result);
			ps.setInt(2, mid);
			ps.setInt(4, rid);
			
			statusUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			log.info(e);
		}
		
		return statusUpdated;
	}

	@Override
	public List<Reimbursement> getReimbursementByEmId(int id) {
		log.info("ReimbursementDaoImpl execute getReimbursementByEmId");

		List <Reimbursement> reimbursements = new ArrayList<>();
		String sql = "select * from project1.reimbursement where employeeid = ?";
		Reimbursement r = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int rid = rs.getInt("id");
				String employee = rs.getString("employee");
				String status = rs.getString("status");
				int eid = rs.getInt("employeeid");
				int resolvedby = rs.getInt("resolvedby");
				int amount = rs.getInt("amount");
				String resultd = rs.getString("resultd");
				r = new Reimbursement(rid, eid, employee, status, resolvedby, amount, resultd);
				reimbursements.add(r);
			}
			rs.close();
		} catch (SQLException e) {
			log.info(e);
		}
		
		return reimbursements;
	}



	@Override
	public int addReimbursement(Employee em, int amount) {
		log.info("ReimbursementDaoImpl execute addReimbursement");

		int reimbursementAdded = 0;
		String sql = "insert into project1.reimbursement (employeeid, employee, status, amount, resultd) values (?, ?, ?, ?, ?)";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			
			ps.setInt(1, em.getId());
			ps.setString(2, em.getFirstName());
			ps.setString(3, "pending");
			ps.setInt(4, amount);
			ps.setString(5, "n/a");
			
			reimbursementAdded = ps.executeUpdate();
			
		} catch (SQLException e) {
			log.info(e);
		}
		
		return reimbursementAdded;
	}

  


}
