package com.revature.services;

import java.util.List;

import com.revature.daos.ManagerDAO;
import com.revature.daos.methods.UserDAOMethods;
import com.revature.model.User;

public class ManagerService {
	
	ManagerDAO mdao = new UserDAOMethods();
	
	public int createUser(User u) {
		return mdao.createUser(u);
	}
	
	public User getManager(){
		return mdao.getManager();
	}
	
	public User getUserById(int id) {
		return mdao.getUserById(id);
	}
	public List<User> getAllUsers(){
		return mdao.getUsers();
		
	}
	
	public int deleteUser(int id) {
		return mdao.deleteUser(id);
		
		
	}
	
	
	

}
