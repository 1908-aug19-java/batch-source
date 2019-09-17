package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Receipts;
import com.revature.util.ConnectionUtil;

public class ReceiptDaoImpl implements ReceiptsDao {

	@Override
	public int createReceipt(Receipts r) {
		// TODO Auto-generated method stub
		int receiptsCreated = 0;
		String sql = "Insert into receipts (receipt_note, receipt_amount, employee_id) values(?, ?, ?)";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, r.getNote());
			ps.setDouble(2, r.getAmount());
			ps.setInt(3, r.getEmployee_id());
			
			receiptsCreated =  ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return receiptsCreated;
	}

	@Override
	public List<Receipts> getReceipts() {
		// TODO Auto-generated method stub
		
		String sql = "Select * from receipts";
		
		List<Receipts> receipts = new ArrayList<Receipts>();
		
		try(Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				Integer receipt_id = rs.getInt("receipt_id");
				Double amount = rs.getDouble("receipt_amount");
				String note = rs.getString("receipt_note");
				Integer employee_id = rs.getInt("employee_id");
				String status = rs.getString("status");
				
				Receipts rm = new Receipts(receipt_id, amount, note, employee_id, status);
				
				receipts.add(rm);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return receipts;
	}

	@Override
	public Receipts getReceiptById(int id) {
		// TODO Auto-generated method stub
		
		
		Receipts rc = null;
		
		String sql = "Select * from receipts where receipt_id = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
//			loop through result
			
			while(rs.next()) {
				Integer receipt_id = rs.getInt("receipt_id");
				Double amount = rs.getDouble("receipt_amount");
				String note = rs.getString("receipt_note");
				Integer employee_id = rs.getInt("employee_id");
				String status = rs.getString("status");
				
				rc = new Receipts(receipt_id, amount, note, employee_id, status);
			}
			
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return rc;
	}

	@Override
	public int deleteReceiptById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int approveReceipt(int rec_id, int emp_id) {
		// TODO Auto-generated method stub
		
		int receiptsUpdated = 0;
		
		String sql = "Update receipts set status = ?, approved_by = ? where receipt_id = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			
			ps.setString(1, "Approved");
			ps.setInt(2, emp_id);
			ps.setInt(3, rec_id);
			
			receiptsUpdated = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return receiptsUpdated;
	}
	
	@Override
	public int denyReceipt(int id) {
		// TODO Auto-generated method stub
		
		int receiptsDenied = 0;
		
		String sql = "Update receipts set status = ? where receipt_id = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			
			ps.setString(1, "Denied");
			ps.setInt(2, id);
			
			receiptsDenied = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return receiptsDenied;
		
	}
	
	@Override
	public List<Receipts> getReceiptsByEmployeeId(int id){
		
		String sql = "Select * from receipts where employee_id = ?";
		
		List<Receipts> receipts = new ArrayList<Receipts>();
		
		try(Connection c = ConnectionUtil.getConnection(); 
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			System.out.println(rs.getMetaData());
			
			while(rs.next()) {
				Integer receipt_id = rs.getInt("receipt_id");
				Double amount = rs.getDouble("receipt_amount");
				String note = rs.getString("receipt_note");
				Integer employee_id = rs.getInt("employee_id");
				String status = rs.getString("status");
				
				Receipts rc = new Receipts(receipt_id, amount, note, employee_id, status);
				
				receipts.add(rc);
			}
			
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return receipts;
	}

	@Override
	public List<Receipts> getApprovedReceiptsByEmployeeId(int emp_id) {
		// TODO Auto-generated method stub
		String sql = "Select * from receipts where status = ? and employee_id = ?";
		
		List<Receipts> approvedReceipts = new ArrayList<Receipts>();
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, "Approved");
			ps.setInt(2, emp_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Integer receipt_id = rs.getInt("receipt_id");
				Double amount = rs.getDouble("receipt_amount");
				String note = rs.getString("receipt_note");
				Integer employee_id = rs.getInt("employee_id");
				String status = rs.getString("status");
				
				Receipts rc = new Receipts(receipt_id, amount, note, employee_id, status);
				
				approvedReceipts.add(rc);
			}
			
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return approvedReceipts;
	}
	
	
	@Override
	public List<Receipts> getDeniedReceiptsByEmployeeId(int emp_id) {
		// TODO Auto-generated method stub
		String sql = "Select * from receipts where status = ? and employee_id = ?";
		
		List<Receipts> deniedReceipts = new ArrayList<Receipts>();
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, "Denied");
			ps.setInt(2, emp_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Integer receipt_id = rs.getInt("receipt_id");
				Double amount = rs.getDouble("receipt_amount");
				String note = rs.getString("receipt_note");
				Integer employee_id = rs.getInt("employee_id");
				String status = rs.getString("status");
				
				Receipts rc = new Receipts(receipt_id, amount, note, employee_id, status);
				
				deniedReceipts.add(rc);
			}
			
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

		
		return deniedReceipts;
	}

//	Get all pending receipts for the manager to view
	@Override
	public List<Receipts> getAllPendingReceipts() {
		// TODO Auto-generated method stub
		
		String sql = "Select * from receipts where status = ?";
		
		List<Receipts> pendingReceipts = new ArrayList<Receipts>();
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, "pending");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Integer receipt_id = rs.getInt("receipt_id");
				Double amount = rs.getDouble("receipt_amount");
				String note = rs.getString("receipt_note");
				Integer employee_id = rs.getInt("employee_id");
				String status = rs.getString("status");
				
				Receipts rc = new Receipts(receipt_id, amount, note, employee_id, status);
				
				pendingReceipts.add(rc);
			}
			
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pendingReceipts;
	}

	@Override
	public List<Receipts> getAllApprovedReceiptsForManager() {
		// TODO Auto-generated method stub
		
		String sql = "select receipts.receipt_note, receipts.receipt_amount, employees.first_name from receipts join employees on receipts.approved_by = employees.employee_id";
		
		List<Receipts> receipts = new ArrayList<Receipts>();

		
		try(Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				String receipt_note = rs.getString("receipt_note");
				Double receipt_amount = rs.getDouble("receipt_amount");
				String manager_name = rs.getString("first_name");
				
				Receipts rm = new Receipts(receipt_note, receipt_amount, manager_name);
				
				receipts.add(rm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return receipts;
	}

	

	
	
	
}
