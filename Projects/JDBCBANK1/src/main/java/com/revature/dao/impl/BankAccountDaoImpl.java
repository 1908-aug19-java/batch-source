package com.revature.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.BankAccountDao;
import com.revature.model.BankAccount;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class BankAccountDaoImpl implements BankAccountDao{
	
	public BankAccountDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	/////////////////////////////////////////////////////
	public void createAccount(BankAccount acount) 
 {
	//int accountCretaed =0;
		String sql = "insert into bank (username,acct_balance,acct_type) values (?,?, ?)RETURNING username";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, acount.getUsername());//getusername
			ps.setDouble(2, acount.getAccount_balance());
			ps.setString(3, acount.getAccountType());//getAccountType
			
			ResultSet rs = ps.executeQuery();
			//accountCretaed=ps.executeUpdate();
			if (rs.next()) {
				String usernam = rs.getString("username");
				acount.setUsername(usernam);;
			}
			if (rs.next()) {
				double balance= rs.getDouble("acct_balance");
				acount.setAccount_balance(balance);
			}
			if (rs.next()) {
				String type = rs.getString("acct_type");
				acount.setAccountType(type);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return accountCretaed ;
	}	
	@Override//////////////////////////////////////////////////////////////////////////////
	public List<BankAccount> getAccounts(){
		
		List<BankAccount> bankAccounts=new ArrayList<>();	
		String sql = "select * from bank";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);)
		{			
			while(rs.next()) 
			{
				String usernam = rs.getString("username");
				double balance = rs.getDouble("acct_balance");
				String acctType = rs.getString("acct_type");
				
				BankAccount b = new BankAccount(usernam, acctType, balance);
				bankAccounts.add(b);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return bankAccounts;
	}
	@Override//////////////////////////////////////////////////////////////////////////////
	public BankAccount getAccounByUsernam(String usernam) {
		
		String sql ="SELECT * FROM bank WHERE username = ?"; 
		BankAccount account = null;

		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){			
			ps.setString(1, usernam); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				account=new BankAccount();
				String username=(rs.getString("username"));
				String type=(rs.getString("acct_type"));
				Double balance=(rs.getDouble("acct_balance"));
				account.setAccount_balance(balance);
				account.setAccountType(type);
				account.setUsername(username);			 
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return account;
	}
	@Override//////////////////////////////////////////////////////////////////////////////
	public List<BankAccount> getAccounByusername(String username) {
		List<BankAccount> bankAccounts=new ArrayList<>();	

        String sql="select customer.fname,customer.lname, bank.acct_type,bank.acct_balance  from customer join bank on customer.username = bank.username";
		BankAccount b = null;

		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){			
			ps.setString(1, username); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String usernam=(rs.getString("username"));
				String fname=(rs.getString("fname"));
				String lname=(rs.getString("lname"));
				String type=(rs.getString("acct_type"));
				Double balance=(rs.getDouble("acct_balance"));
				
				b = new BankAccount (usernam, type, balance);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		bankAccounts.add(b);

		return bankAccounts;
	}
	
	@Override//////////////////////////////////////////////////////////////////////////////
	public int updateAccount(BankAccount account) {
		
		int bankacountUpdated = 0;
		String sql = "update bank "
				+  " set acct_balance = ? ,"
				+  " acct_type = ? "
				+ "where username = ? ";  //by account username
	
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql))
		{			
			ps.setString(3, account.getUsername());//getAccount_Id
			ps.setDouble(1, account.getAccount_balance());
			ps.setString(2, account.getAccountType());//getAccountType
			
			bankacountUpdated = ps.executeUpdate();
			//commit the group of tx
			//con.commit();
			con.close();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}	
		return bankacountUpdated ;
	}

	@Override//////////////////////////////////////////////////////////////////////////////
	public int deletAccount(BankAccount ac) {
		
		int rowsDeleted = 0;
		String sql = "delete from bank where username = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, ac.getUsername());
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsDeleted;
	}


	@Override//////////////////////////////////////////////////////////////////////////////
	public void depositTo(BankAccount bankk) {
		String sql = "UPDATE bank set balance=? where username=?";

		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setDouble(1, bankk.getAccount_balance());
			ps.setString(2, bankk.getAccountType());

			ResultSet rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void withdrawWithSqlFunct(double decrease ,BankAccount b){
		
		String sql = "{call withdraw(?,?)}";
		
		try(Connection c = ConnectionUtil.getConnection();
			CallableStatement cs = c.prepareCall(sql)){
			cs.setDouble(1, decrease);
			cs.setString(2, b.getUsername());
			
			ResultSet rs=cs.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
