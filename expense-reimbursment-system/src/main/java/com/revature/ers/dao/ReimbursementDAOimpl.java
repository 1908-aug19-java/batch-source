package com.revature.ers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.ers.models.Reimbursement;
import com.revature.ers.security.DBCredentials;
import com.revature.ers.util.FilterPair;

public class ReimbursementDAOimpl implements ReimbursementDAO {

	private static final Logger LOGGER = Logger.getLogger(ReimbursementDAOimpl.class);
	private static final String[] databaseColumns = { "r_id", "amount", "status", "date_submitted", "user_accounts_id",
			"manager_accounts_id" };
	
	@Override
	public Optional<Reimbursement> findById(long id) {
		Optional<Reimbursement> reimbursementOptional = Optional.empty();
		String query = "SELECT * FROM reimbursements WHERE r_id=?";
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass()); PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Double amount = rs.getDouble(databaseColumns[1]);
				String status = rs.getString(databaseColumns[2]);
				Timestamp dateSubmitted = rs.getTimestamp(databaseColumns[3]);
				Long userAccountId = rs.getLong(databaseColumns[4]);
				Long managerAccountId = rs.getLong(databaseColumns[5]);
				Reimbursement reimbursement = new Reimbursement(id, amount, status, dateSubmitted, userAccountId,
						managerAccountId);
				reimbursementOptional = Optional.of(reimbursement);
			}
			rs.close();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return reimbursementOptional;
	}

	@Override
	public List<Reimbursement> findByParams(FilterPair[] pairs) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		StringBuilder query = new StringBuilder("SELECT * FROM reimbursements");
		for (int i = 0; i < pairs.length; i++) {
			if(i == 0) {
				query.append(" WHERE " + pairs[i].getKey() + "=" + pairs[i].getValue());
			}else {
				query.append(" AND " + pairs[i].getKey() + "=" + pairs[i].getValue());
			}
		}
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass());
				Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_SENSITIVE);
				ResultSet rs = stmt.executeQuery(query.toString());) {
			while (rs.next()) {
				Long id = rs.getLong(databaseColumns[0]);
				Double amount = rs.getDouble(databaseColumns[1]);
				String status = rs.getString(databaseColumns[2]);
				Timestamp dateSubmitted = rs.getTimestamp(databaseColumns[3]);
				Long userAccountId = rs.getLong(databaseColumns[4]);
				Long managerAccountId = rs.getLong(databaseColumns[5]);
				Reimbursement reimbursement = new Reimbursement(id, amount, status, dateSubmitted, userAccountId,
						managerAccountId);
				reimbursements.add(reimbursement);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return reimbursements;
	}
	
	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		String query = "SELECT * FROM reimbursements";
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass());
				Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_SENSITIVE);
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				Long id = rs.getLong(databaseColumns[0]);
				Double amount = rs.getDouble(databaseColumns[1]);
				String status = rs.getString(databaseColumns[2]);
				Timestamp dateSubmitted = rs.getTimestamp(databaseColumns[3]);
				Long userAccountId = rs.getLong(databaseColumns[4]);
				Long managerAccountId = rs.getLong(databaseColumns[5]);
				Reimbursement reimbursement = new Reimbursement(id, amount, status, dateSubmitted, userAccountId,
						managerAccountId);
				reimbursements.add(reimbursement);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return reimbursements;
	}

	@Override
	public Long save(Reimbursement reimbursement) {
		String query = "INSERT INTO reimbursements values(default,?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass());
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setDouble(1, reimbursement.getAmount());
			stmt.setString(2, reimbursement.getStatus());
			stmt.setTimestamp(3, reimbursement.getDateSubmitted());
			stmt.setLong(4, reimbursement.getUserAccountId());
			stmt.setLong(5, reimbursement.getManagerAccountId());

			int i = stmt.executeUpdate();

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					reimbursement.setId(generatedKeys.getLong("r_id"));
					LOGGER.info(i + " records inserted");
				} else {
					throw new SQLException("Creating UserAccount failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return reimbursement.getId();
	}

	@Override
	public void update(Reimbursement reimbursement) {
		String query = String.format("UPDATE reimbursements SET s%= ?, s%=?, s%=?, s%=?, s%=? WHERE s%=?", databaseColumns[1],
				databaseColumns[2], databaseColumns[3], databaseColumns[4], databaseColumns[5], databaseColumns[0]);
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass()); PreparedStatement stmt = conn.prepareStatement(query);) {

			stmt.setDouble(1, reimbursement.getAmount());
			stmt.setString(2, reimbursement.getStatus());
			stmt.setTimestamp(3, reimbursement.getDateSubmitted());
			stmt.setLong(4, reimbursement.getUserAccountId());
			stmt.setLong(5, reimbursement.getManagerAccountId());
			stmt.setLong(6, reimbursement.getId());
			int i = stmt.executeUpdate();
			LOGGER.info(i + " records updated");
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public void delete(Reimbursement reimbursement) {
		String query = "DELETE FROM reimbursements WHERE r_id=?";
		try (Connection conn = DriverManager.getConnection(DBCredentials.getUrl(), DBCredentials.getUser(),
				DBCredentials.getPass()); PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setLong(1, reimbursement.getId());
			int i = stmt.executeUpdate();
			LOGGER.info(i + " records deleted");
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

}
