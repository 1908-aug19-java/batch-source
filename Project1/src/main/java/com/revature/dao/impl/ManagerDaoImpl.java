package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.dao.ManagerDao;
import com.revature.model.Manager;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao{
	private static Logger log = Logger.getRootLogger();

	@Override
	public Manager getManagerByUN(String un) {
		log.info("ManagerDaoImpl execute getManagerByUN");
		String sql = "select * from project1.manageracc where un = ?";
		Manager m = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, un);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int managerId = rs.getInt("id");
				String managerUN = rs.getString("un");
				String managerPW = rs.getString("pw");
				m = new Manager(managerId, managerUN, managerPW);
			}
			rs.close();
		} catch (SQLException e) {
			log.info(e);
		}
		
		return m;
	}

}
