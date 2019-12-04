package com.book.service;

import java.util.List;

import com.book.domain.Language;

public interface LanguageService {
	
	public List<Language> getAllLanguage();

	public int saveLanguage(Language language);

	public Language findById(String id);

	public boolean editById(Language language);

	public boolean deleteLanguage(String id);

}
