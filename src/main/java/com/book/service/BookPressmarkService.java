package com.book.service;

import com.book.domain.BookPressmark;

import java.util.List;

public interface BookPressmarkService {

    List<BookPressmark> getAllPressmark();

    int saveBookPressmark(BookPressmark bookPressmark);

    BookPressmark findById(String id);

    int editBookPressmark(BookPressmark bookPressmark);

    int deleteBookPressmark(String id);
}
