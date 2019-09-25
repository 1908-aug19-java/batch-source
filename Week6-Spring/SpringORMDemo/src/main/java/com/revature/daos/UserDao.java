package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	
	public List<User> getUsers();
	public User getUserById(int id);
	
	//remaining crud methods
	
}
