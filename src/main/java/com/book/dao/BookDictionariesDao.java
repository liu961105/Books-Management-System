package com.book.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.book.domain.Book;
import com.book.domain.BookDictionaries;
import com.book.util.UUIDUtil;

@Repository
public class BookDictionariesDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String DELETE_BOOK_SQL = "delete from book_info where book_id = ?  ";
	private final static String EDIT_BOOK_SQL = "update book_dictionaries set name= ? ,author= ? ,publish= ? ,ISBN= ? ,introduction= ? ,language= ? ,price= ? ,pubdate= ? ,class_id= ? ,pressmark= ? ,state= ?   where id= ?  ";

	private final static String QUERY_ALL_BOOKS_SQL = "SELECT * FROM book_dictionaries";

	private final static String ADD_BOOK_SQL = "INSERT INTO book_dictionaries VALUES(? ,?,?,?,?,?,?,?,?,?,?,?,?)";

	private final static String GET_BOOK_SQL = "SELECT * FROM book_dictionaries where ISBN = ? ";

	private final static String find_BY_ID_SQL = "SELECT * FROM book_dictionaries where id = ?  ";

	private final static String QUERY_BOOK_SQL = "SELECT * FROM book_info WHERE book_id like  ?  or name like ?   or ISBN like ? ";
	// 查询匹配图书的个数
	private final static String MATCH_BOOK_SQL = "SELECT count(*) FROM book_info WHERE book_id like ?  or name like ?  or ISBN like ?  ";

	public List<BookDictionaries> getDictionaries() {
		final List<BookDictionaries> books = new ArrayList<BookDictionaries>();
		jdbcTemplate.query(QUERY_ALL_BOOKS_SQL, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				while (rs.next()) {
					BookDictionaries bookDictionaries = new BookDictionaries();
					bookDictionaries.setId(rs.getString("id"));
					bookDictionaries.setBookId(rs.getLong("book_id"));
					bookDictionaries.setAuthor(rs.getString("author"));
					bookDictionaries.setIntroduction(rs.getString("introduction"));
					bookDictionaries.setPressmark(rs.getString("pressmark"));
					bookDictionaries.setLanguage(rs.getString("language"));
					bookDictionaries.setClassId(rs.getString("class_id"));
					bookDictionaries.setState(rs.getInt("state"));
					bookDictionaries.setPrice(rs.getBigDecimal("price"));
					bookDictionaries.setPublish(rs.getString("publish"));
					bookDictionaries.setPubdate(rs.getDate("pubdate"));
					bookDictionaries.setName(rs.getString("name"));
					bookDictionaries.setIsbn(rs.getString("isbn"));
					books.add(bookDictionaries);
				}
			}
		});
		return books;
	}

	public int save(BookDictionaries bookDictionaries) {
		return jdbcTemplate.update(ADD_BOOK_SQL,
				new Object[] { UUIDUtil.expordUuid(), bookDictionaries.getBookId(), bookDictionaries.getName(),
						bookDictionaries.getAuthor(), bookDictionaries.getPublish(), bookDictionaries.getIsbn(),
						bookDictionaries.getIntroduction(), bookDictionaries.getLanguage(), bookDictionaries.getPrice(),
						bookDictionaries.getPubdate(), bookDictionaries.getClassId(), bookDictionaries.getPressmark(),
						"1" });
	}

	public BookDictionaries checkISBN(String isbn) {

		final BookDictionaries bookDictionaries = new BookDictionaries();

		jdbcTemplate.query(GET_BOOK_SQL, new Object[] { isbn }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				bookDictionaries.setAuthor(resultSet.getString("author"));
				bookDictionaries.setBookId(resultSet.getLong("book_id"));
				bookDictionaries.setClassId(resultSet.getString("class_id"));
				bookDictionaries.setIntroduction(resultSet.getString("introduction"));
				bookDictionaries.setIsbn(resultSet.getString("isbn"));
				bookDictionaries.setLanguage(resultSet.getString("language"));
				bookDictionaries.setName(resultSet.getString("name"));
				bookDictionaries.setPressmark(resultSet.getString("pressmark"));
				bookDictionaries.setPubdate(resultSet.getDate("pubdate"));
				bookDictionaries.setPrice(resultSet.getBigDecimal("price"));
				bookDictionaries.setState(resultSet.getInt("state"));
				bookDictionaries.setPublish(resultSet.getString("publish"));
			}

		});
		return bookDictionaries;
	}

	public BookDictionaries findById(String id) {
		final BookDictionaries book = new BookDictionaries();
		jdbcTemplate.query(find_BY_ID_SQL, new Object[] { id }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				book.setId(resultSet.getString("id"));
				book.setAuthor(resultSet.getString("author"));
				book.setBookId(resultSet.getLong("book_id"));
				book.setClassId(resultSet.getString("class_id"));
				book.setIntroduction(resultSet.getString("introduction"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setLanguage(resultSet.getString("language"));
				book.setName(resultSet.getString("name"));
				book.setPressmark(resultSet.getString("pressmark"));
				book.setPubdate(resultSet.getDate("pubdate"));
				book.setPrice(resultSet.getBigDecimal("price"));
				book.setState(resultSet.getInt("state"));
				book.setPublish(resultSet.getString("publish"));
			}
		});
		return book;
	}

	public int editBook(BookDictionaries book){
        String  id=book.getId();
        String name=book.getName();
        String author=book.getAuthor();
        String publish=book.getPublish();
        String isbn=book.getIsbn();
        String introduction=book.getIntroduction();
        String language=book.getLanguage();
        BigDecimal price=book.getPrice();
        Date pubdate=book.getPubdate();
        String  classId=book.getClassId();
        String  pressmark=book.getPressmark();
        int state=book.getState();
        return jdbcTemplate.update(EDIT_BOOK_SQL,new Object[]{name,author,publish,isbn,introduction,language,price,pubdate,classId,pressmark,state,id});
    }
}
