package com.revature.dao;

import java.util.List;

import com.revature.model.BankAccount;
import com.revature.model.User;

public interface BankAccountDao {

	public List<BankAccount> getAccounts(); //
	public BankAccount  getAccounByUsernam(String usernam); //

	public List<BankAccount>  getAccounByusername(String username);//
	
	
	public void depositTo(BankAccount bankk);//
	
	public int updateAccount(BankAccount account);
	

	public int deletAccount(BankAccount ac);//


	public void createAccount(BankAccount acount);//
	public void withdrawWithSqlFunct(double decrease ,BankAccount b);
}
