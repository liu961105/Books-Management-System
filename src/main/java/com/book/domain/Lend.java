package com.book.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借还日志
 * 
 * @author LZN
 *
 */
public class Lend implements Serializable {

	private long sernum;
	private long bookId;
	private String  readerId;
	private Date lendDate;
	private Date backDate;
	private String borrowingDay;

	// 新增字段
	private String bookName;
	private String readerName;
	
	private String  returnDate;

	public void setReaderId(String  readerId) {
		this.readerId = readerId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public void setSernum(long sernum) {
		this.sernum = sernum;
	}

	public String getReaderId() {
		return readerId;
	}

	public long getBookId() {
		return bookId;
	}

	public Date getBackDate() {
		return backDate;
	}

	public Date getLendDate() {
		return lendDate;
	}

	public long getSernum() {
		return sernum;
	}

	public String getBorrowingDay() {
		return borrowingDay;
	}

	public void setBorrowingDay(String borrowingDay) {
		this.borrowingDay = borrowingDay;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
