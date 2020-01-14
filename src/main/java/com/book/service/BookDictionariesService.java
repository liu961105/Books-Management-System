package com.book.service;

import java.util.List;

import com.book.domain.BookDictionaries;

public interface BookDictionariesService {
	
	
	/**
	 * 图书字典跳转
	 */
	List<BookDictionaries> getDictionaries();

	 int save(BookDictionaries bookDictionaries);

	 BookDictionaries checkISBN(String isbn);

	 BookDictionaries findById(String id);

	boolean editBook(BookDictionaries book);
 }
