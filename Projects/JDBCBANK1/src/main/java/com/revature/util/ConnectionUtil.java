package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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