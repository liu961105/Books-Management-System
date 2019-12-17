package com.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.book.domain.Lend;
import com.book.domain.ReaderInfo;

import java.math.BigInteger;
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

	private final static String BOOK_RETURN_SQL_ONE = "UPDATE lend_list SET back_date = ? WHERE sernum = ?  AND back_date is NULL";

	private final static String BOOK_RETURN_SQL_TWO = "UPDATE book_info SET state = 1,in_number =in_number+1,lend_number =lend_number-1  WHERE book_id = ? ";

	private final static String BOOK_LEND_SQL_ONE = "INSERT INTO lend_list (book_id,reader_id,lend_date,borrowingDay,book_name,reader_name,return_date) VALUES ( ? , ? , ? ,?,?,?,?)";

	private final static String BOOK_LEND_SQL_TWO = "UPDATE book_info SET state = 0,in_number =in_number -1,lend_number =lend_number+1   WHERE book_id = ? ";

	private final static String LEND_LIST_SQL = "SELECT * FROM lend_list";

	private final static String GET_READID_SQL = "select * from lend_list where book_id = ? ";
	
	private final static String getSernum = "select * from lend_list where book_id = ? and reader_id = ? ";

	private final static String MY_LEND_LIST_SQL = "SELECT * FROM lend_list WHERE reader_id = ? ";
	private static final String MATCH_LOG_SQL = "select count(*) from lend_list where  book_name like ? or reader_id like ? or reader_name like ? ";
	private static final String QUERY_LOG_SQL = "select * from lend_list where  book_name like ? or reader_id like ? or reader_name like ? ";

	private static final String MATCH_READER_LEND_SQL = "select count(*) from lend_list where book_name like ? or book_id like ?  AND  reader_id = ? ";

	private static final String QUERYREADERLEND_SQL = "select * from lend_list where book_name like ? or book_id like ?  AND  reader_id = ? ";
	
	private static final String CKECK_READER_SQL = "select count(*) from lend_list where book_id = ? and reader_id = ? and back_date is NULL";

	public int bookReturnOne(long sernum) {
		return jdbcTemplate.update(BOOK_RETURN_SQL_ONE, new Object[] { df.format(new Date()),sernum });
	}

	public int bookReturnTwo(long bookId) {
		return jdbcTemplate.update(BOOK_RETURN_SQL_TWO, new Object[] { bookId });
	}

	public int bookLendOne(long bookId, int readerId, String borrowingDay, String bookName, String readerName,String returnDate) {
		return jdbcTemplate.update(BOOK_LEND_SQL_ONE,
				new Object[] { bookId, readerId, df.format(new Date()), borrowingDay, bookName, readerName,returnDate });
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
					lend.setReaderId(resultSet.getString("reader_id"));
					lend.setSernum(resultSet.getLong("sernum"));
					lend.setBookName(resultSet.getString("book_name"));
					lend.setReaderName(resultSet.getString("reader_name"));
					lend.setReturnDate(resultSet.getString("return_date"));
					list.add(lend);
				}
			}
		});
		return list;
	}

	public ArrayList<Lend> myLendList(String  readerId) {
		final ArrayList<Lend> list = new ArrayList<Lend>();

		jdbcTemplate.query(MY_LEND_LIST_SQL, new Object[] { readerId }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					Lend lend = new Lend();
					lend.setBackDate(resultSet.getDate("back_date"));
					lend.setBookId(resultSet.getLong("book_id"));
					lend.setLendDate(resultSet.getDate("lend_date"));
					lend.setReaderId(resultSet.getString("reader_id"));
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
					lend.setReaderId(rs.getString("reader_id"));
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
					lend.setReaderId(rs.getString("reader_id"));
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
					lend.setReaderId(rs.getString("reader_id"));
					lend.setSernum(rs.getLong("sernum"));
					lend.setBookName(rs.getString("book_name"));
					lend.setReaderName(rs.getString("reader_name"));
					lends.add(lend);
				}
			}
		});
		return lends;
	}

	public int checkReaderLog(long bookId,int readerId) {
		return jdbcTemplate.queryForObject(CKECK_READER_SQL,new Object[]{bookId,readerId},Integer.class);
		
	}

	public Lend getSernum(long bookId, String readerId) {
		Lend lend = new Lend();
		jdbcTemplate.query(getSernum, new Object[] { bookId, readerId}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				while (rs.next()) {
					lend.setSernum(rs.getInt("sernum"));
				}
			}
		});
		return lend;
	}

}
