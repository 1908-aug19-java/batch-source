package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursment;
import com.revature.util.ConnectionUtil;

public class ReimbursmentDaoImpl  implements ReimbursmentsDao{

	
	public  List<Reimbursment> getReimbursments() {
		List<Reimbursment> reimbursment = new ArrayList<>();
		String sql = "select * from REIMBURSMENTS";
		
		try(Connection c = ConnectionUtil.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				
				Reimbursment r = new Reimbursment();
				r.setReqNum(rs.getInt("reimnum_id"));
				r.setEmplId(rs.getInt("empl_id"));
				r.setReason(rs.getString("Description"));
				r.setAmount(rs.getDouble("amount"));
				r.setStatus(rs.getString("status"));
				r.setManagerId(rs.getInt("manager_id"));

				reimbursment.add(r);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return reimbursment;
	}
	public  List<Reimbursment> getReimbursmenttsById(int id ) {
		List<Reimbursment> reimbursment = new ArrayList<>();
		String sql = "select * from REIMBURSMENTS where empl_id= ?";
		
		Reimbursment r = null;	
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){			
			ps.setInt(1, id); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId=(rs.getInt("reimnum_id"));
				String reason=(rs.getString("Description"));
				Double amt=(rs.getDouble("amount"));
				String status=(rs.getString("status"));
				int managerId=(rs.getInt("manager_id"));
				
				r = new Reimbursment(reimbId,id,reason,amt,status,managerId);	

				reimbursment.add(r);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return reimbursment;
	}
	@Override
	public Reimbursment getReimbusmentsById(int id) {
		String sql = "select * from REIMBURSMENTS where empl_id = ?";
		Reimbursment r = null;	
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){			
			ps.setInt(1, id); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				//r= new Reimbusments();
				
				int reimbId=(rs.getInt("reimnum_id"));
				String reason=(rs.getString("Description"));
				Double amt=(rs.getDouble("amount"));
				String status=(rs.getString("status"));
				int managerId=(rs.getInt("manager_id"));

				r = new Reimbursment(reimbId,id,reason,amt,status,managerId);	
				 }
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return r;
	}

	@Override
	public int createReimbusment(Reimbursment r) {
		List<Reimbursment> reimbrsmn = new ArrayList<>();

		int reimbCreated = 0;
		String sql = "insert into REIMBURSMENTS (empl_id,Description,amount,status,manager_id) values (?,?,?,?,?)";		
		try(Connection con = ConnectionUtil.getConnection()){
				String[] primaryKeyValues = {"reimnum_id"};
				PreparedStatement ps = con.prepareStatement(sql,primaryKeyValues);
			ps.setInt(1,r.getEmplId());
			ps.setString(2,r.getReason());
			ps.setDouble(3,r.getAmount());
			ps.setInt(4,0);//pending
			ps.setInt(5, 1);//initially assigend to manager 1
						
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				int reqNum = rs.getInt(1);
				r.setReqNum(reqNum);
			}
			reimbCreated = ps.executeUpdate();	
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}		
		reimbrsmn.add(r);
		return reimbCreated;
	}
	@Override
	public int viewReimbusment(Reimbursment r) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int UpdateReimbursment(String status, int rqeimbId, int manager_id) {
		int reimbUpdated = 0;

		String sql = ("update REIMBURSMENTS set status = ? set manager_id= ? where reimnum_id = ? "); 
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, status);
			ps.setInt(2, manager_id);			
			ps.setInt(3, rqeimbId);			
			reimbUpdated = ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return reimbUpdated ;		
	}
}
