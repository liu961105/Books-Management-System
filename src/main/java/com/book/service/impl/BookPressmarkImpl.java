package com.book.service.impl;

import com.book.dao.BookPressmarkDao;
import com.book.domain.BookPressmark;
import com.book.service.BookPressmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookPressmarkImpl implements BookPressmarkService {

    @Autowired
    private BookPressmarkDao bookPressmarkDao;

    @Override
    public List<BookPressmark> getAllPressmark() {
        return bookPressmarkDao.getAllPressmark();
    }

    @Override
    public int saveBookPressmark(BookPressmark bookPressmark) {
        return bookPressmarkDao.saveBookPressmark(bookPressmark);
    }

    @Override
    public BookPressmark findById(String id) {
        return bookPressmarkDao.findById(id);
    }

    @Override
    public int editBookPressmark(BookPressmark bookPressmark) {
        return bookPressmarkDao.editBookPressmark(bookPressmark);
    }

    @Override
    public int deleteBookPressmark(String id) {
        return bookPressmarkDao.deleteBookPressmark(id);
    }
}
