package com.revature;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.dao.BankAccountDao;
import com.revature.dao.impl.BankAccountDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.impl.UserDaoImpl;
import com.revature.model.BankAccount;
import com.revature.model.User;
import com.revature.services.BankingServices;
import com.revature.services.LoginServices;
import com.revature.util.ConnectionUtil;

public class Driver {
	  private static BankAccountDaoImpl bdao = new BankAccountDaoImpl();
	  private static UserDaoImpl udao = new UserDaoImpl();
		private static Scanner sc = new Scanner(System.in);
		
	public static void main(String[] args) {
		Connection c;
		try {
			c = ConnectionUtil.getConnection();
			String driver = c.getMetaData().getDriverName();
			System.out.println(driver);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("WELCOME TO  Abdellatif Bank Aplication  [Main Menu]");
		System.out.println();
		boolean a=true;
		 a=mainMenu();
		while (a) 
		{	


			mainMenu();
		 }
	}
        public static boolean mainMenu() 
        { 
			System.out.println("Please Enter Your Choice");
			System.out.println("1 ----->  Login:");
			System.out.println("2 ----->  Create Account");
			System.out.println("0 ----->  Quit");
        	boolean c=false;
            int selection=sc.nextInt();
            boolean out=true;

            while(out)
            {
            	try {
            		 switch (selection) 
                     {
                     case 1:
                     	LoginServices.logIn();
                     	//mainMenu();
                     	break;
                     case 2:
                            BankingServices.run();
                           // mainMenu();
                            break;
                     case 0: 	 
                           out=false;
                           System.out.println("Thank you for Choosing Abdellatif Bank Application ");
                           out=false;
                           c=true; //to repeat the function
                           break;
                     default: 
                     	System.out.println("Invalid Entry 1");
                     	break;
     
                     	
                    }
               
        }catch(InputMismatchException e) {
				System.out.println("That is not a valid input.");
				
			}
        }
    return c;
 }//func
       

}//class