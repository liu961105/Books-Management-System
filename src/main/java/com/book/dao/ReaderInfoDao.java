package com.book.dao;

import com.book.domain.Book;
import com.book.domain.ReaderInfo;

import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ReaderInfoDao {

	private JdbcTemplate jdbcTemplate;

	private final static String ADD_READER_INFO_SQL = "INSERT INTO reader_info VALUES(?,?,?,?,?,?,?,?)";
	private final static String DELETE_READER_INFO_SQL = "DELETE FROM reader_info where reader_id = ? ";
	private final static String GET_READER_INFO_SQL = "SELECT * FROM reader_info where reader_id = ? ";
	private final static String UPDATE_READER_INFO = "UPDATE reader_info set name = ? ,sex = ? ,birth = ? ,address = ? ,telcode = ? where reader_id = ? ";
	private final static String ALL_READER_INFO_SQL = "SELECT * FROM reader_info";
	private final static String FIND_BY_NAME_SQL = "SELECT * FROM reader_info where name = ? ";

	// private final static String MATCH_BOOK_SQL="SELECT count(*) FROM
	// book_info WHERE book_id like ? or name like ? or ISBN like ? ";
	private final static String MATCH_READER_SQL = "select count(*) from reader_info where reader_id  like ? or name like ?";

	private static final String QUERY_READER_SQL = "select * from reader_info where reader_id  like ? or name like ? ";

	public ArrayList<ReaderInfo> getAllReaderInfo() {
		final ArrayList<ReaderInfo> readers = new ArrayList<ReaderInfo>();
		jdbcTemplate.query(ALL_READER_INFO_SQL, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					ReaderInfo reader = new ReaderInfo();
					reader.setAddress(resultSet.getString("address"));
					reader.setBirth(resultSet.getDate("birth"));
					reader.setName(resultSet.getString("name"));
					reader.setReaderId(resultSet.getString("reader_id"));
					reader.setSex(resultSet.getString("sex"));
					reader.setTelcode(resultSet.getString("telcode"));
					reader.setSchoolName(resultSet.getString("school_name"));
					reader.setClassName(resultSet.getString("class_name"));
					readers.add(reader);
				}
			}
		});
		return readers;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ReaderInfo findReaderInfoByReaderId(String readerId) {
		final ReaderInfo reader = new ReaderInfo();
		jdbcTemplate.query(GET_READER_INFO_SQL, new Object[] { readerId }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				reader.setAddress(resultSet.getString("address"));
				reader.setBirth(resultSet.getDate("birth"));
				reader.setName(resultSet.getString("name"));
				reader.setReaderId(resultSet.getString("reader_id"));
				reader.setSex(resultSet.getString("sex"));
				reader.setTelcode(resultSet.getString("telcode"));
				reader.setSchoolName(resultSet.getString("school_name"));
				reader.setClassName(resultSet.getString("class_name"));
			}
		});
		return reader;
	}

	/*
	 * 根据name查询
	 */
	public ReaderInfo findByName(String name) {
		final ReaderInfo reader = new ReaderInfo();
		jdbcTemplate.query(FIND_BY_NAME_SQL, new Object[] { name }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				reader.setAddress(resultSet.getString("address"));
				reader.setBirth(resultSet.getDate("birth"));
				reader.setName(resultSet.getString("name"));
				reader.setReaderId(resultSet.getString("reader_id"));
				reader.setSex(resultSet.getString("sex"));
				reader.setTelcode(resultSet.getString("telcode"));
				reader.setSchoolName(resultSet.getString("school_name"));
				reader.setClassName(resultSet.getString("class_name"));
			}
		});
		return reader;
	}

	public int deleteReaderInfo(String readerId) {
		return jdbcTemplate.update(DELETE_READER_INFO_SQL, readerId);
	}

	public int editReaderInfo(ReaderInfo readerInfo) {
		String address = readerInfo.getAddress();
		Date birth = readerInfo.getBirth();
		String name = readerInfo.getName();
		String readerId = readerInfo.getReaderId();
		String sex = readerInfo.getSex();
		String telcode = readerInfo.getTelcode();
		return jdbcTemplate.update(UPDATE_READER_INFO, new Object[] { name, sex, birth, address, telcode, readerId });
	}

	public int addReaderInfo(ReaderInfo readerInfo) {
		String address = readerInfo.getAddress();
		Date birth = readerInfo.getBirth();
		String name = readerInfo.getName();
		String sex = readerInfo.getSex();
		String telcode = readerInfo.getTelcode();
		String readerId = readerInfo.getReaderId();

		return jdbcTemplate.update(ADD_READER_INFO_SQL, new Object[] { readerId, name, sex, birth, address, telcode,
				readerInfo.getSchoolName(), readerInfo.getClassName() });
	}

	public int matchReader(String searchWord) {
		String swcx = "%" + searchWord + "%";
		return jdbcTemplate.queryForObject(MATCH_READER_SQL, new Object[] { swcx, swcx }, Integer.class);
	}

	public List<ReaderInfo> queryReader(String searchWord) {
		String swcx = "%" + searchWord + "%";
		final List<ReaderInfo> readerInfos = new ArrayList<ReaderInfo>();
		jdbcTemplate.query(QUERY_READER_SQL, new Object[] { swcx, swcx }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				while (rs.next()) {
					ReaderInfo readerInfo = new ReaderInfo();
					readerInfo.setAddress(rs.getString("address"));
					readerInfo.setBirth(rs.getDate("birth"));
					readerInfo.setName(rs.getString("name"));
					readerInfo.setReaderId(rs.getString("reader_id"));
					readerInfo.setSex(rs.getString("sex"));
					readerInfo.setTelcode(rs.getString("telcode"));
					readerInfo.setSchoolName(rs.getString("school_name"));
					readerInfo.setClassName(rs.getString("class_name"));
					readerInfos.add(readerInfo);
				}
			}
		});
		return readerInfos;
	}

}
