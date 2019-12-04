package com.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.book.domain.Lend;
import com.book.domain.ReaderInfo;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class LendDao {

	private JdbcTemplate jdbcTemplate;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final static String BOOK_RETURN_SQL_ONE = "UPDATE lend_list SET back_date = ? WHERE book_id = ? AND back_date is NULL";

	private final static String BOOK_RETURN_SQL_TWO = "UPDATE book_info SET state = 1 WHERE book_id = ? ";

	private final static String BOOK_LEND_SQL_ONE = "INSERT INTO lend_list (book_id,reader_id,lend_date,borrowingDay,book_name,reader_name) VALUES ( ? , ? , ? ,?,?,?)";

	private final static String BOOK_LEND_SQL_TWO = "UPDATE book_info SET state = 0 WHERE book_id = ? ";

	private final static String LEND_LIST_SQL = "SELECT * FROM lend_list";

	private final static String GET_READID_SQL = "select * from lend_list where book_id = ? ";

	private final static String MY_LEND_LIST_SQL = "SELECT * FROM lend_list WHERE reader_id = ? ";
	private static final String MATCH_LOG_SQL = "select count(*) from lend_list where  book_name like ? or reader_id like ? or reader_name like ? ";
	private static final String QUERY_LOG_SQL = "select * from lend_list where  book_name like ? or reader_id like ? or reader_name like ? ";

	private static final String MATCH_READER_LEND_SQL = "select count(*) from lend_list where book_name like ? or book_id like ?  AND  reader_id = ? ";

	private static final String QUERYREADERLEND_SQL = "select * from lend_list where book_name like ? or book_id like ?  AND  reader_id = ? ";

	public int bookReturnOne(long bookId) {
		return jdbcTemplate.update(BOOK_RETURN_SQL_ONE, new Object[] { df.format(new Date()), bookId });
	}

	public int bookReturnTwo(long bookId) {
		return jdbcTemplate.update(BOOK_RETURN_SQL_TWO, new Object[] { bookId });
	}

	public int bookLendOne(long bookId, int readerId, String borrowingDay, String bookName, String readerName) {
		return jdbcTemplate.update(BOOK_LEND_SQL_ONE,
				new Object[] { bookId, readerId, df.format(new Date()), borrowingDay, bookName, readerName });
	}

	public int bookLendTwo(long bookId) {
		return jdbcTemplate.update(BOOK_LEND_SQL_TWO, new Object[] { bookId });
	}

	public ArrayList<Lend> lendList() {
		final ArrayList<Lend> list = new ArrayList<Lend>();

		jdbcTemplate.query(LEND_LIST_SQL, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					Lend lend = new Lend();
					lend.setBackDate(resultSet.getDate("back_date"));
					lend.setBookId(resultSet.getLong("book_id"));
					lend.setLendDate(resultSet.getDate("lend_date"));
					lend.setReaderId(resultSet.getInt("reader_id"));
					lend.setSernum(resultSet.getLong("sernum"));
					lend.setBookName(resultSet.getString("book_name"));
					lend.setReaderName(resultSet.getString("reader_name"));
					list.add(lend);
				}
			}
		});
		return list;
	}

	public ArrayList<Lend> myLendList(int readerId) {
		final ArrayList<Lend> list = new ArrayList<Lend>();

		jdbcTemplate.query(MY_LEND_LIST_SQL, new Object[] { readerId }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					Lend lend = new Lend();
					lend.setBackDate(resultSet.getDate("back_date"));
					lend.setBookId(resultSet.getLong("book_id"));
					lend.setLendDate(resultSet.getDate("lend_date"));
					lend.setReaderId(resultSet.getInt("reader_id"));
					lend.setSernum(resultSet.getLong("sernum"));
					lend.setBookName(resultSet.getString("book_name"));
					lend.setReaderName(resultSet.getString("reader_name"));
					list.add(lend);
				}
			}
		});
		return list;

	}

	public Lend getReadId(Long bookId) {
		Lend lend = new Lend();
		jdbcTemplate.query(GET_READID_SQL, new Object[] { bookId }, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				while (rs.next()) {
					lend.setReaderId(rs.getInt("reader_id"));
				}
			}
		});
		return lend;
	}

	public int matchLog(String searchWord) {
		String swcx = "%" + searchWord + "%";
		return jdbcTemplate.queryForObject(MATCH_LOG_SQL, new Object[] { swcx, swcx, swcx }, Integer.class);

	}

	public List<Lend> queryLog(String searchWord) {
		String swcx = "%" + searchWord + "%";
		final List<Lend> lends = new ArrayList<Lend>();
		jdbcTemplate.query(QUERY_LOG_SQL, new Object[] { swcx, swcx, swcx }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				while (rs.next()) {
					Lend lend = new Lend();
					lend.setBackDate(rs.getDate("back_date"));
					lend.setBookId(rs.getLong("book_id"));
					lend.setLendDate(rs.getDate("lend_date"));
					lend.setReaderId(rs.getInt("reader_id"));
					lend.setSernum(rs.getLong("sernum"));
					lend.setBookName(rs.getString("book_name"));
					lend.setReaderName(rs.getString("reader_name"));
					lends.add(lend);
				}
			}
		});
		return lends;
	}

	public int matchReaderLend(String searchWord, String readerId) {
		String swcx = "%" + searchWord + "%";
		return jdbcTemplate.queryForObject(MATCH_READER_LEND_SQL, new Object[] { swcx, swcx, readerId }, Integer.class);

	}

	public List<Lend> queryReaderLend(String searchWord, String readerId) {
		String swcx = "%" + searchWord + "%";
		final List<Lend> lends = new ArrayList<Lend>();
		jdbcTemplate.query(QUERYREADERLEND_SQL, new Object[] { swcx, swcx, readerId }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				while (rs.next()) {
					Lend lend = new Lend();
					lend.setBackDate(rs.getDate("back_date"));
					lend.setBookId(rs.getLong("book_id"));
					lend.setLendDate(rs.getDate("lend_date"));
					lend.setReaderId(rs.getInt("reader_id"));
					lend.setSernum(rs.getLong("sernum"));
					lend.setBookName(rs.getString("book_name"));
					lend.setReaderName(rs.getString("reader_name"));
					lends.add(lend);
				}
			}
		});
		return lends;
	}

}
