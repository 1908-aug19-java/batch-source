package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.revature.Driver;
import com.revature.dao.impl.BankAccountDaoImpl;
import com.revature.dao.impl.UserDaoImpl;
import com.revature.model.BankAccount;
import com.revature.model.User;

public class LoginServices {
	private static Scanner sc = new Scanner(System.in);

	private static Set<String> userSet = new HashSet<>();
	private static Set<String> passSet = new HashSet<>();

    private static BankAccountDaoImpl bdao = new BankAccountDaoImpl();
	private static UserDaoImpl udao = new UserDaoImpl();
	
///////////////////////////
	public static BankAccount matchUsernam(String username) {

		BankAccount acct=null;
	    String in=sc.nextLine();	
		String pass;
		
		System.out.println("Please Enter your password.");
		pass = sc.nextLine();	
		if(udao.getUserByUserName(username).getPassword().equals(pass)) 
		{
			acct = bdao.getAccounByUsernam(username);
			System.out.println("acct "+acct);			
		}
		return acct;
	}
//////////////////////////	
	public static boolean logIn() {
		BankAccount acct=null;	
		String username ;
		for (User u : udao.getUsers()) 
		{
			userSet.add(u.getUsername());
		}
		System.out.println("username list availiable are: "+userSet);		
		System.out.println(" Please enter your user name");
		username=sc.next();

		if (userSet.contains(username))
		{
			acct = matchUsernam(username);
			boolean r=true;
			while(r)
			{
				System.out.println("login to Bank  " +username + "   is successful ");
				System.out.println(" To View Balance Enter [V]; For Deposit [D];For Withdraw[W]");
				String input= sc.next();
				if (input.equalsIgnoreCase("v"))
				{
					System.out.println("Your Account Details are  "+ acct );
					System.out.println("To Make Another Transaction press [n]");
					String inputt= sc.next();
					if (inputt.equalsIgnoreCase("n"))
					    {r=true;}
					else 
					{
						r=false;
						Driver.mainMenu();
					}	
				}
				else if (input.equalsIgnoreCase("d"))
				{
					BankingServices.deposit(acct);
					System.out.println("To Make Another Transaction press [n]");
					String inputt= sc.next();
					if (inputt.equalsIgnoreCase("n"))
						{r=true;}
					else 
					{
						r=false;
						Driver.mainMenu();
					}										
				}
				else if (input.equalsIgnoreCase("w"))
				{
					BankingServices.withdraw(acct);
					System.out.println("To Make Another Transaction press [n]");
					String inputt= sc.next();
					if (inputt.equalsIgnoreCase("n"))
						{r=true;}
					else
					{
						r=false;
						Driver.mainMenu();
						 }
				 }
				else 
				{
					System.out.println("Invalid Entry 1");
					Driver.mainMenu();
				}	
			}//while
		}//if user	
		else  
			{
				System.out.println("login to Bank  " +username + "   is NOT successful ");
			    System.out.println(" If you do not have an account ");
			    System.out.println("Type [c] To Create New Account... ");
			    
			    String inn= sc.next();
				if (inn.equalsIgnoreCase("c"))
				{
					  BankingServices.run();
				}
				else
				{
					System.out.println("Invalid Entry Try Again\n\n     Main Menu \n\n");
					Driver.mainMenu();
				 }
			}
				
		
		return false;
	}
	


	


}