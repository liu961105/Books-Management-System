package com.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.dao.LanguageDao;
import com.book.domain.Language;
import com.book.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageDao languageDao;

	@Override
	public List<Language> getAllLanguage() {

		return languageDao.getAllLanguages();
	}

	@Override
	public int saveLanguage(Language language) {
		
		return languageDao.saveLanguage(language);
	}

	@Override
	public Language findById(String id) {
		
		return languageDao.finById(id);
	}

	@Override
	public boolean editById(Language language) {
		
		return languageDao.editLanguage(language)>0;
	}

	@Override
	public boolean deleteLanguage(String id) {
		
		return languageDao.deleteLanguage(id)>0;
	}

}
