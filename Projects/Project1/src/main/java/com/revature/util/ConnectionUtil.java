package com.revature.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = System.getenv("DB_URL");//+":5432/postgres"; // to be put in environment variable
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASS");

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;
	}

}
