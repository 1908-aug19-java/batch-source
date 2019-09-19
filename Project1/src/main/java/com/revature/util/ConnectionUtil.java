package com.revature.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionUtil {
	private static Connection connection;

	public static Connection getConnection() throws SQLException {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_User");
		String password = System.getenv("DB_PW");

		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;
	}
}