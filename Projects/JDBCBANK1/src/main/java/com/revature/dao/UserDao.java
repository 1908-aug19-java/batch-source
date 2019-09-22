package com.revature.dao;

import java.sql.Connection;
import java.util.List;
import com.revature.model.User;

public interface UserDao {
	
//create user
	public int createUser(User user);// frist last email

	public int deleteUserByUsername(String username);

// get user
	public List<User> getUsers();

	public User getUserByUserName(String username);

	public User getUsersBylasttName(String lastName);

// update user	
	public int updateUser(User user);

}
