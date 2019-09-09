package com.revature.bankingapp.sysoutgui.dao;

import java.util.List;
import java.util.Optional;

import com.revature.bankingapp.sysoutgui.model.User;

public interface UserDAO{

	Optional<User> findById(long id);
	
	Optional<User> findByEmail(String emailAddress);
    
    List<User> findAll();
    
    Long save(User user);
     
    void update(User user);
     
    void delete(User user);
}