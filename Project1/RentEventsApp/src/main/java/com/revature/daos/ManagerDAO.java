package com.revature.daos;

import java.util.List;

import com.revature.model.User;

public interface ManagerDAO {
	
	public int createUser(User u);
	public int deleteUser(int id);
	public User getManager();
	public User getUserById(int id);
	public List<User> getUsers();
	public String getUserRole(User u);
	

}
