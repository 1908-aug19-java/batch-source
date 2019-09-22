package com.revature.services;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import com.revature.Driver;
import com.revature.dao.BankAccountDao;
import com.revature.dao.impl.BankAccountDaoImpl;
import com.revature.dao.impl.UserDaoImpl;
import com.revature.model.BankAccount;
import com.revature.model.User;

public final class BankingServices {
	private static List<String> emailSet = new ArrayList<>();
	private static Set<Integer> idList = new HashSet<>();
	private static Set<Integer> acctIdSet = new HashSet<>();
	private static List<String> userlist = new ArrayList<>();
	private static Set<String> userSet = new HashSet<>();
    private static BankAccountDaoImpl bdao = new BankAccountDaoImpl();
	private static UserDaoImpl udao = new UserDaoImpl();


	private static Scanner sc = new Scanner(System.in);

	
	public BankingServices() {
		super();

		
	}

	public static void run() {

		createUserAccount();
	
	
	}
	
////////////////////////////////////////////////////
	public static void getUsersNamList()
	{
		UserDaoImpl udao = new UserDaoImpl();
		for (User u : udao.getUsers()) {
		userSet.add(u.getUsername());
		}
	}
		public static User createUserAccount() {
			// unique email
			for (User em : udao.getUsers()) {
				emailSet.add(em.getEmail());
			}
			//UserDaoImpl udao = new UserDaoImpl();
			getUsersNamList();

			for (User id : udao.getUsers()) {
				idList.add(id.getId());
			}
			
			System.out.println("Great Lets create Your New Account:\n ");
						
//////
			System.out.println("Enter your firstname:\n");
			String in=sc.nextLine(); 
			while ((in == null)&& (in.equals(""))&& (!in.matches("^[a-zA-Z]*$"))) {
				System.out.println("Invalid Entry Try Again\n");
				in = sc.nextLine();	
			}
			String firstname=in;
/////////
			System.out.println("Enter your lastname:\n\n");
			in = sc.nextLine();
			while ((in == null)&& (!in.matches("^[a-zA-Z]*$"))) {
				System.out.println("Invalid Entry Try Again\n\n");
				in = sc.nextLine();	
			}
			String lastname=in;
///////
			boolean emailExist=false;
			System.out.println("Enter your email:\n\n");
			String email="";
			in = sc.nextLine();		

			while (!emailExist)//// unique email
			{
				if (!(emailSet.contains(in)) )
				{
					emailSet.add(in);
					email=in;	
					emailExist=true;
				}
				else if ((emailSet.contains(in)) )
				{
					System.out.println("Invalid Entry \n Try Again\n");
					emailExist=false;
					in = sc.nextLine();
				}				
			}			
////////
			System.out.println("Enter your password:\n \n");
			in = sc.nextLine();
			while ((in == null)) {
				System.out.println("Invalid Entry Try Again \n \n");
				in = sc.nextLine();	
			}
			String password=in;
///			
			System.out.println("Enter your Username:\n \n");
			in = sc.nextLine();
			while ((in == null)&& (in.equals(" "))) {
				System.out.println("Invalid Entry Try Again\n \n");
				in = sc.nextLine();	
			}
			String username=in;
//	
			User user = new User(idList.size()+1,firstname,lastname,email, password,username);		
			udao.createUser(user);
			
			System.out.println(user);
			Createaccount(user,username)	;
			return user;
		}
///////////////////////////////////////////////////////////
		public static BankAccount Createaccount(User user,String username) {

			
			System.out.println("Please enter type of the account: \n");
			System.out.println(" checking==[c] for  saving==[s]");
			String in=sc.nextLine(); 
			String type=new String();

			try {
			switch (in) {
	        case "c":
	              type="checking";
	              break;
	        case "s":{
	        	  type="Saving";
	              break;
	        }
	        default:
	            System.out.println("Invalid Entry 1");
	            Driver.mainMenu();
	            return null;
			             }
			}
			catch(InputMismatchException e) {
				System.out.println("That is not a valid input.");
				System.exit(0);	
			}
		
			BankAccount account=new BankAccount(username,type,0);
			System.out.println("Account"+account+"Is Created");
			// transaction section
            System.out.println("To Deposit Money Enter [d]  to Exit Enter[e]");
			String inn=sc.nextLine();
			boolean breakout=true;
			while(breakout)
			{
				try 
				{
					switch (inn) {
			        case "d":
			        {
			        	deposit(account);		        	
			        	breakout=false;		
			              break;
			        } 
			        case "e":
			        	 breakout=false;
			        	 Driver.mainMenu();
			        	 break;
			        default:
			        {
			            System.out.println("Invalid Entry 1");			
		                Driver.mainMenu();
		                return null;
				             }
				}										
			    }
				catch(InputMismatchException e) {
					System.out.println("That is not a valid input.");
					System.exit(0);	
				}
		
			}
			bdao.createAccount(account);
			return account;
		}
		
		public static void Menu(BankAccount account) {
			
		}
/////////////////////////////////////////////////////////////////////////
		public static void deposit(BankAccount account) {
	        System.out.println("Please enter an amount: ");
 	        String in=sc.nextLine();
 	        double amt;
 	        double balance;
			amt = Double.parseDouble(in);
	        BankAccount newAcc = account;
		    balance=account.getAccount_balance();
			if(amt > 0) 
			{
				newAcc.setAccount_balance(balance+amt);
				bdao.updateAccount(newAcc);
			    System.out.println("$" + amt + " deposited. Your balance is now " + newAcc.getAccount_balance() + ".");
			}
			else if (amt<0)
			{
				  System.out.println("That is not a valid input.");
		          deposit(account);
			}		    
			}

/////////////////////////////////////////////////////////////////////////////
		public static void withdraw(BankAccount account) {
	        System.out.println("Please enter an amount: ");
			String in = sc.nextLine();
			double amt;
			double balance;

	        BankAccount newAcc = account;

			
	
		     amt = Double.parseDouble(in);
			 balance=account.getAccount_balance();
		     if (balance<amt) 
		     {
		    	 System.out.println("insufficient funds !!!");
				 in = sc.nextLine();		
		     } 
		     else 
		     {
				 newAcc.setAccount_balance(balance-amt);
		    	 bdao.withdrawWithSqlFunct(amt, account);
			     System.out.println("$" + amt + " Withdraw. Your balance is " + account.getAccount_balance() + ".");
		    }
}
//////////////////


		public static int deleteUser(String username) {
			
			UserDaoImpl userDI = new UserDaoImpl();
			return userDI.deleteUserByUsername(username);
			}
	

		
		
		
}
