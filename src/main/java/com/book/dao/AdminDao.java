package com.book.dao;

import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.book.domain.Admin;
import com.book.domain.ReaderInfo;

@Repository
public class AdminDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String MATCH_ADMIN_SQL = "SELECT COUNT(*) FROM admin where admin_id = ? and password = ? ";
	private static final String RE_PASSWORD_SQL = "UPDATE admin set password = ? where admin_id = ? ";
	private static final String GET_PASSWD_SQL = "SELECT password from admin where admin_id = ?";
	private static final String ADMIN_ADD_SQL = "insert into admin values(?,'111111',?,?,?,?,?)";

	private static final String DELETE_ADMIN_SQL = "delete from admin where admin_id = ? ";

	private static final String GET_ALL_ADMIN_SQL = "SELECT * FROM admin";

	private static final String FIND_BY_ADMINID_SQL = "select * from admin where admin_id = ?";

	private static final String EDIT_ADMIN_SQL = "UPDATE admin set admin_name = ?,admin_age = ?, gender = ?, address = ?,phone = ? where admin_id = ?";

	public int getMatchCount(String  adminId, String password) {
		return jdbcTemplate.queryForObject(MATCH_ADMIN_SQL, new Object[] { adminId, password }, Integer.class);
	}

	public int rePassword(String  adminId, String newPasswd) {
		return jdbcTemplate.update(RE_PASSWORD_SQL, new Object[] { newPasswd, adminId });
	}

	public String getPasswd(String id) {
		return jdbcTemplate.queryForObject(GET_PASSWD_SQL, new Object[] { id }, String.class);
	}

	/*
	 * 管理员列表
	 */
	public List<Admin> getAllAdmin() {
		final List<Admin> adminList = new ArrayList<Admin>();
		jdbcTemplate.query(GET_ALL_ADMIN_SQL, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					Admin admin = new Admin();
					admin.setAdminName(resultSet.getString("admin_name"));
					admin.setAdminId(resultSet.getString("admin_id"));
					admin.setPassword(resultSet.getString("password"));
					admin.setAdminage(resultSet.getString("admin_age"));
					admin.setGender(resultSet.getString("gender"));
					admin.setPhone(resultSet.getString("phone"));
					admin.setAddress(resultSet.getString("address"));
					adminList.add(admin);
				}
			}
		});
		return adminList;
	}

	/*
	 * 添加管理员
	 */
	public int addAdmin(Admin admin) {
		return jdbcTemplate.update(ADMIN_ADD_SQL, new Object[] { admin.getAdminId(), admin.getAdminName(),
				admin.getAdminage(), admin.getGender(), admin.getAddress(), admin.getPhone() });
	}

	public int deleteAdmin(String adminId) {

		return jdbcTemplate.update(DELETE_ADMIN_SQL, adminId);
	}

	public Admin findByAdminId(String adminId) {
		final Admin admin = new Admin();
		jdbcTemplate.query(FIND_BY_ADMINID_SQL, new Object[] { adminId }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				admin.setAdminId(rs.getString("admin_id"));
				admin.setAdminName(rs.getString("admin_name"));
				admin.setAdminage(rs.getString("admin_age"));
				admin.setGender(rs.getString("gender"));
				admin.setAddress(rs.getString("address"));
				admin.setPhone(rs.getString("phone"));
			}
		});
		
		
		return admin;
	}

	public int editAdmin(Admin admin) {
		return jdbcTemplate.update(EDIT_ADMIN_SQL, new Object[] { admin.getAdminName(), admin.getAdminage(),
				admin.getGender(), admin.getAddress(), admin.getPhone(), admin.getAdminId() });

	}

}
