package com.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.dao.LanguageDao;
import com.book.domain.Language;
import com.book.service.LanguageService;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageDao languageDao;

	@Override
	public List<Language> getAllLanguage() {

		return languageDao.getAllLanguages();
	}

}
