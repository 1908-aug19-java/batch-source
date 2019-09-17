package com.revature.ers.security;

public class DBCredentials {

	static final String driver = "org.postgresql.Driver";
	static final String db = "postgres";
	static final String url = "jdbc:postgresql://localhost:5432/ERS_DB";
//	static final String url = "jdbc:postgresql://expense-reimbursement-system.cjus9rioqccl.us-east-2.rds.amazonaws.com:5432/" + db;
	static final String user = "postgres";
	static final String pass = "password";

	public static String getDriver() {
		return driver;
	}

	public static String getDb() {
		return db;
	}

	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}

	public static String getPass() {
		return pass;
	}
}
