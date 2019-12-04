package com.book.domain;

import org.springframework.stereotype.Repository;

/**
 * 图书语言
 * 
 * @author LZN
 *
 */
public class Language {
	private String id;
	private String languageNumber;
	private String languageName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLanguageNumber() {
		return languageNumber;
	}

	public void setLanguageNumber(String languageNumber) {
		this.languageNumber = languageNumber;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}
