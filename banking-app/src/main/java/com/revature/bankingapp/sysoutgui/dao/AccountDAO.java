package com.revature.bankingapp.sysoutgui.dao;

import java.util.List;
import java.util.Optional;

import com.revature.bankingapp.sysoutgui.model.Account;
import com.revature.bankingapp.sysoutgui.model.User;

public interface AccountDAO {

	Optional<Account> findById(long id);
	
	Optional<Account> findByUsername(String username);

	List<Account> findAll();

	Long save(Account account);
	
	Long save(User user, Account account);

	void update(Account account);

	void delete(Account account);
}