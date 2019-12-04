package com.book.service.impl;

import com.book.dao.LendDao;
import com.book.dao.ReaderInfoDao;
import com.book.domain.Lend;
import com.book.domain.ReaderInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LendService {
	private LendDao lendDao;
	@Autowired
	private ReaderInfoDao readerInfoDao;

	@Autowired
	public void setLendDao(LendDao lendDao) {
		this.lendDao = lendDao;
	}

	public boolean bookReturn(long bookId) {
		return lendDao.bookReturnOne(bookId) > 0 && lendDao.bookReturnTwo(bookId) > 0;
	}

	public boolean bookLend(long bookId, int readerId, String borrowingDay,String bookName,String readerName) {
		return lendDao.bookLendOne(bookId, readerId, borrowingDay,bookName,readerName) > 0 && lendDao.bookLendTwo(bookId) > 0;
	}

	public ArrayList<Lend> lendList() {
		return lendDao.lendList();
	}

	public ArrayList<Lend> myLendList(int readerId) {
		return lendDao.myLendList(readerId);
	}
	/*
	 * 根据读者证号查询姓名
	 */
	public ReaderInfo getReadName(int readerId){
		return readerInfoDao.findReaderInfoByReaderId(readerId);
	}
	/**
	 * 根据bookid查询readId
	 */
	public Lend getReadId(long bookId){
		return lendDao.getReadId(bookId);
	}

	public boolean matchLog(String searchWord) {
		
		return lendDao.matchLog(searchWord)>0;
	}

	public List<Lend> queryLog(String searchWord) {
		
		return lendDao.queryLog(searchWord);
	}

	public boolean matchReaderLend(String searchWord, String readerId) {
		
		return lendDao.matchReaderLend(searchWord,readerId)>0;
	}

	public List<Lend> queryReaderLend(String searchWord, String readerId) {
		
		return lendDao.queryReaderLend(searchWord,readerId);
	}

	
}
