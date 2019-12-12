package com.book.service;

import java.util.List;

import com.book.domain.BookDictionaries;

public interface BookDictionariesService {
	
	
	/**
	 * 图书字典跳转
	 */
	public  List<BookDictionaries> getDictionaries();

	public int save(BookDictionaries bookDictionaries);

	public BookDictionaries checkISBN(String isbn);

	public BookDictionaries findById(String id);

	public boolean editBook(BookDictionaries book);
 }
