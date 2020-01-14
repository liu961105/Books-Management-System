package com.book.dao;

import com.book.domain.BookPressmark;
import com.book.domain.Language;
import com.book.util.UUIDUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookPressmarkDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_PRESSMARK = "SELECT * FROM book_pressmark";

    private static final String SAVE_BOOKPRESSMARK = "INSERT INTO book_pressmark VALUES(?,?)";

    private static final String FINDBOOKPRESSMARKBYID = "SELECT * FROM book_pressmark where id =?";

    private static final String EDIT_BOOKPRESSMARK_SQL = "update book_pressmark set code = ? where id = ? ";

    private static final String DELETE_BOOKPRESSMARK_SQL = "delete  from book_pressmark where id = ? ";

    /**
     * 查询全部的list
     *
     * @return
     */
    public List<BookPressmark> getAllPressmark() {
        final List<BookPressmark> list = new ArrayList<BookPressmark>();
        jdbcTemplate.query(GET_ALL_PRESSMARK, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet rs) throws SQLException {
                rs.beforeFirst();
                while (rs.next()) {
                    BookPressmark bookPressmark = new BookPressmark();
                    bookPressmark.setId(rs.getString("id"));
                    bookPressmark.setCode(rs.getString("code"));
                    list.add(bookPressmark);
                }
            }
        });
        return list;
    }

    /**
     * 保存
     *
     * @param bookPressmark
     * @return
     */
    public int saveBookPressmark(BookPressmark bookPressmark) {
        return jdbcTemplate.update(SAVE_BOOKPRESSMARK,
                new Object[]{UUIDUtil.expordUuid(), bookPressmark.getCode()});
    }

    /**
     * findById
     *
     * @param id
     * @return
     */
    public BookPressmark findById(String id) {
        BookPressmark bookPressmark = new BookPressmark();
        jdbcTemplate.query(FINDBOOKPRESSMARKBYID, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                bookPressmark.setId(rs.getString("id"));
                bookPressmark.setCode(rs.getString("code"));
            }
        });
        return bookPressmark;
    }

    /**
     * 修改
     *
     * @param bookPressmark
     * @return
     */
    public int editBookPressmark(BookPressmark bookPressmark) {

        return jdbcTemplate.update(EDIT_BOOKPRESSMARK_SQL, new Object[]{bookPressmark.getCode(), bookPressmark.getId()});
    }

    /**
     * 删除
     */
    public int deleteBookPressmark(String id) {
        return jdbcTemplate.update(DELETE_BOOKPRESSMARK_SQL, id);
    }


}
