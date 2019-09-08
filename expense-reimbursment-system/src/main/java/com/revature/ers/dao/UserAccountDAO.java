package com.revature.ers.dao;

import java.util.List;
import java.util.Optional;

import com.revature.ers.models.UserAccount;

public interface UserAccountDAO {

	Optional<UserAccount> findById(long id);

	Optional<UserAccount> findByEmail(String emailAddress);

	List<UserAccount> findAll();

	Long save(UserAccount userAccount);

	void update(UserAccount userAccount);

	void delete(UserAccount userAccount);
}
