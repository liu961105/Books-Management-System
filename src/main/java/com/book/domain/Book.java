package com.book.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Book implements Serializable{
//新增
	
    private long bookId;
    private String name;
    private String author;
    private String publish;
    private String isbn;
    private String introduction;
    private String language;
    private BigDecimal price;
    private Date pubdate;
    private String  classId;
    private String pressmark;
    private int state;
    //新增
    private Integer number;
    private Integer inNumber;
    private Integer lendNumber;
    @Getter
    @Setter
    private String row;
    @Getter
    @Setter
    private String column;


    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPressmark(String pressmark) {
        this.pressmark = pressmark;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getBookId() {
        return bookId;
    }

    public String getClassId() {
        return classId;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public String getAuthor() {
        return author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getPressmark() {
        return pressmark;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getLanguage() {
        return language;
    }

    public int getState() {
        return state;
    }

    public String getPublish() {
        return publish;
    }

    @Override
    public String toString() {
        return "这本书的信息为"+pressmark+pubdate+bookId+name+author+publish+isbn+introduction+language+price+classId+state;
    }

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getInNumber() {
		return inNumber;
	}

	public void setInNumber(Integer inNumber) {
		this.inNumber = inNumber;
	}

	public Integer getLendNumber() {
		return lendNumber;
	}

	public void setLendNumber(Integer lendNumber) {
		this.lendNumber = lendNumber;
	}
}
