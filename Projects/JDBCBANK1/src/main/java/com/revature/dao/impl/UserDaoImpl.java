package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.BankAccountDao;
import com.revature.dao.UserDao;
import com.revature.model.BankAccount;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {
	BankAccountDao bb = new BankAccountDaoImpl();
	
	@Override//////////////////////////////////////////////////////////////////////////////
	public List<User> getUsers() {
		
		List<User> users = new ArrayList<>();
		String sql = "select * from customer";
		
		try(Connection c = ConnectionUtil.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				
				User u = new User();
				u.setId(rs.getInt("userid"));
				u.setFirstName(rs.getString("fname"));
				u.setLastName(rs.getString("lname"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("upassword"));
				u.setUsername(rs.getString("username"));

				users.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return users;
	}
	
	@Override//////////////////////////////////////////////////////////////////////////////
	public User getUserByUserName(String username) {
		
		String sql = "select * from customer where username = ?";
		User u = null;	
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, username); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				u = new User();
				
				int id=(rs.getInt("userid"));
				String fname=(rs.getString("fname"));
				String lname=(rs.getString("lname"));
				String email=(rs.getString("email"));
				String pass=(rs.getString("upassword"));
				String usernam=(rs.getString("username"));

				u = new User(id, fname,lname,email,pass,usernam);	
				 }
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return u;
	}
	@Override//////////////////////////////////////////////////////////////////////////////
	public User getUsersBylasttName(String lastName) {
		String sql = "select * from customer where lname = ?";
		User u = null;	
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1,lastName ); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id=(rs.getInt("userId"));
				String fname=(rs.getString("fname"));
				String email=(rs.getString("email"));
				String pass=(rs.getString("upassword"));
				 String usernam =(rs.getString("username"));
				u = new User(id, fname,lastName,email,pass, usernam);	
				 }
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return u;
	}
	@Override//////////////////////////////////////////////////////////////////////////////
	public int createUser(User user) {
		
		int userCreated = 0;
		String sql = "insert into customer (userid, fname,lname,email,upassword,username)  values (?,?,?,?, ?,?)";		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, user.getId());//getAccount_Id
			ps.setString(2,user.getFirstName());
			ps.setString(3,user.getLastName());
			ps.setString(4,user.getEmail());
			ps.setString(5,user.getPassword());
			ps.setString(6,user.getUsername());

						
			userCreated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return userCreated;
	}


	@Override//////////////////////////////////////////////////////////////////////////////
	public int deleteUserByUsername(String username) {
		
		int rowsDeleted = 0;
		String sql = "delete from customer where username = ?";
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){			
			ps.setString(1,username);	
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return rowsDeleted;
	
	}

	@Override//////////////////////////////////////////////////////////////////////////////
	public int updateUser(User user) {
		int userUpdated = 0;
		String sql = "update customer "
				+ "set userid = ?, "
				+ "fname = ? ,"
				+ "lname = ? ,"
				+ "email = ? ,"
				+ "upassword = ? ,"
				+ "where username = ? ";  //by username
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			//group multiple PS Statements under the same transaction
			con.setAutoCommit(false);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getUsername());//getAccount_Id

			
			userUpdated = ps.executeUpdate();
			//commit the group of tx
			con.commit();		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return userUpdated ;
	}

}
