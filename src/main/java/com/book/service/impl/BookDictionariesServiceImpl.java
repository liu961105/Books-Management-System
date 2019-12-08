package com.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dao.BookDictionariesDao;
import com.book.domain.BookDictionaries;
import com.book.service.BookDictionariesService;

@Service
public class BookDictionariesServiceImpl  implements BookDictionariesService{
	
	@Autowired
	private BookDictionariesDao bookDictionariesDao;

	@Override
	public List<BookDictionaries> getDictionaries() {
		
		return bookDictionariesDao.getDictionaries();
	}

	@Override
	public int save(BookDictionaries bookDictionaries) {
		
		return bookDictionariesDao.save(bookDictionaries);
	}

	@Override
	public BookDictionaries checkISBN(String isbn) {
		
		return bookDictionariesDao.checkISBN(isbn);
	}

}
