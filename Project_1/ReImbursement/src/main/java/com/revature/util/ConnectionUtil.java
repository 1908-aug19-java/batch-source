package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
					
		}catch (ClassNotFoundException e) {
			System.out.println("error connectiong to db");
			e.printStackTrace();
		}
		
		String url = System.getenv("DB_Url");
		String username = System.getenv("DB_User");
		String password = System.getenv("DB_Pass");
		
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;
	}
}
