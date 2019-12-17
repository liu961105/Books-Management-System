package com.book.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.crypto.Data;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.handler.inter.IExcelModel;


public class BookImport implements IExcelModel {

	/*
	 * private long bookId; private String name; private String author; private
	 * String publish; private String isbn; private String introduction; private
	 * String language; private BigDecimal price; private Date pubdate; private
	 * int classId; private int pressmark; private int state;
	 */
	@Excel(name = "图书号")
	long bookId;
	@Excel(name="图书名称")
	String name;
	@Excel(name = "作者")
	String author;
	@Excel(name = "出版社")
	String publish;
	@Excel(name = "ISBN")
	String ISBN;
	@Excel(name = "简介")
	String introduction;
	@Excel(name = "语言")
	String language;
	@Excel(name = "价格")
	BigDecimal price;
	@Excel(name = "出版日期")
	Date  pubdate;
	@Excel(name = "分类号")
	String  classId;
	@Excel(name = "书架号")
	String pressmark;
	@Excel(name = "状态")
	Integer state;
	@Excel(name = "数量")
	Integer number;
	
	  private String errorMsg;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getPressmark() {
		return pressmark;
	}

	public void setPressmark(String pressmark) {
		this.pressmark = pressmark;
	}

	public int getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	@Override
	public String getErrorMsg() {

		return errorMsg;
	}

	@Override
	public void setErrorMsg(String errorMsg) {

	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
