package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public ArrayList<Book> queryBook(String searchWord,String searchISBN){
        return  bookDao.queryBook(searchWord, searchISBN);
    }

    public ArrayList<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public int deleteBook(long bookId){
        return bookDao.deleteBook(bookId);
    }

    public boolean matchBook(String searchWord,String searchISBN){
        return bookDao.matchBook(searchWord)>0;
    }

    public boolean addBook(Book book){
        return bookDao.addBook(book)>0;
    }

    public Book getBook(Long bookId){
        Book book=bookDao.getBook(bookId);
        return book;
    }
    public Book findByISBN(String isbn){
        return bookDao.findByISBN(isbn);
    }
    public boolean editBook(Book book){
        return bookDao.editBook(book)>0;
    }

    public void addBookNumber(String isbn, Integer number) {
        bookDao.addBookNumber(isbn,number);
    }
}
