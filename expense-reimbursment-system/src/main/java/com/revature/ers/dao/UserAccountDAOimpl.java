package com.revature.ers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.ers.models.UserAccount;
import com.revature.ers.security.DBCredentials;

public class UserAccountDAOimpl implements UserAccountDAO {

	private final String[] databaseColumns = { "id", "first_name", "last_name", "email", "password", "last_login",
			"isactive", "blocked", "failed_logins", "authority_id" };
	private static Logger logger = LogManager.getLogger();

	public Optional<UserAccount> findById(long id) {
		Optional<UserAccount> userAccountOptional = Optional.empty();
		String query = "SELECT * FROM user_accounts WHERE id=?";
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass()); PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString(databaseColumns[1]);
				String lastName = rs.getString(databaseColumns[2]);
				String email = rs.getString(databaseColumns[3]);
				String password = rs.getString(databaseColumns[4]);
				Timestamp last_login = rs.getTimestamp(databaseColumns[5]);
				Boolean isactive = rs.getBoolean(databaseColumns[6]);
				Boolean blocked = rs.getBoolean(databaseColumns[7]);
				Long failed_logins = rs.getLong(databaseColumns[8]);
				Long authority_id = rs.getLong(databaseColumns[9]);
				UserAccount userAccount = new UserAccount(id, firstName, lastName, email, password, last_login,
						isactive, blocked, failed_logins, authority_id);
				userAccountOptional = Optional.of(userAccount);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e);
		}
		return userAccountOptional;
	}

	public Optional<UserAccount> findByEmail(String email) {
		Optional<UserAccount> userAccountOptional = Optional.empty();
		String query = "SELECT * FROM user_accounts WHERE email=?";
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass()); PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong(databaseColumns[0]);
				String firstName = rs.getString(databaseColumns[1]);
				String lastName = rs.getString(databaseColumns[2]);
				String password = rs.getString(databaseColumns[4]);
				Timestamp last_login = rs.getTimestamp(databaseColumns[5]);
				Boolean isactive = rs.getBoolean(databaseColumns[6]);
				Boolean blocked = rs.getBoolean(databaseColumns[7]);
				Long failed_logins = rs.getLong(databaseColumns[8]);
				Long authority_id = rs.getLong(databaseColumns[9]);
				UserAccount userAccount = new UserAccount(id, firstName, lastName, email, password, last_login,
						isactive, blocked, failed_logins, authority_id);
				userAccountOptional = Optional.of(userAccount);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return userAccountOptional;
	}

	public List<UserAccount> findAll() {
		List<UserAccount> userAccounts = new ArrayList<UserAccount>();
		String query = "SELECT * FROM user_accounts";
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass());
				Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_SENSITIVE);
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				Long id = rs.getLong(databaseColumns[0]);
				String firstName = rs.getString(databaseColumns[1]);
				String lastName = rs.getString(databaseColumns[2]);
				String email = rs.getString(databaseColumns[3]);
				String password = rs.getString(databaseColumns[4]);
				Timestamp last_login = rs.getTimestamp(databaseColumns[5]);
				Boolean isactive = rs.getBoolean(databaseColumns[6]);
				Boolean blocked = rs.getBoolean(databaseColumns[7]);
				Long failed_logins = rs.getLong(databaseColumns[8]);
				Long authority_id = rs.getLong(databaseColumns[9]);
				UserAccount userAccount = new UserAccount(id, firstName, lastName, email, password, last_login,
						isactive, blocked, failed_logins, authority_id);
				userAccounts.add(userAccount);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return userAccounts;
	}

	public Long save(UserAccount userAccount) {
		String query = "INSERT INTO user_accounts values(default,?,?,?)";
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass());
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, userAccount.getFirstName());
			stmt.setString(2, userAccount.getLastName());
			stmt.setString(3, userAccount.getEmail());
			stmt.setString(4, userAccount.getPassword());
			stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setBoolean(6, true);
			stmt.setBoolean(7, false);
			stmt.setLong(8, 0L);
			stmt.setLong(9, userAccount.getAuthorityId());

			int i = stmt.executeUpdate();

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					userAccount.setId(generatedKeys.getLong("id"));
					logger.info(i + " records inserted");
				} else {
					throw new SQLException("Creating UserAccount failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			logger.error(e);
		}

		return userAccount.getId();
	}

	public void update(UserAccount userAccount) {
		String query = String.format("UPDATE user_accounts SET s%= ?, s%=?, s%=? WHERE s%=?", databaseColumns[1],
				databaseColumns[2], databaseColumns[3], databaseColumns[4], databaseColumns[5], databaseColumns[6],
				databaseColumns[7], databaseColumns[8], databaseColumns[9], databaseColumns[0]);
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass()); PreparedStatement stmt = conn.prepareStatement(query);) {

			stmt.setString(1, userAccount.getFirstName());
			stmt.setString(2, userAccount.getLastName());
			stmt.setString(3, userAccount.getEmail());
			stmt.setString(4, userAccount.getPassword());
			stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setBoolean(6, true);
			stmt.setBoolean(7, false);
			stmt.setLong(8, 0L);
			stmt.setLong(9, userAccount.getAuthorityId());
			stmt.setLong(10, userAccount.getId());
			int i = stmt.executeUpdate();
			logger.info(i + " records updated");
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public void delete(UserAccount userAccount) {
		String query  = "DELETE FROM user_accounts WHERE id=?";
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass());PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setLong(1, userAccount.getId());
			int i = stmt.executeUpdate();
			logger.info(i + " records deleted");
		} catch (SQLException e) {
			logger.error(e);
		}
	}

}
