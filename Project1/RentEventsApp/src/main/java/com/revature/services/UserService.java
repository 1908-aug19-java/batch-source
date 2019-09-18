package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.methods.UserDAOMethods;
import com.revature.model.User;

public class UserService {
	
	UserDAO udao =  new UserDAOMethods();
	
	public User getUser(int userId){
		return udao.getUserById(userId);
		
	}
	
	public User getUserByPassword(String userName,String password) {
		
		return udao.getUserByUsernameAndPassword(userName, password);
		
	}
	
	

}
