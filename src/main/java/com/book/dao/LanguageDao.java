package com.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.book.domain.Language;

public class LanguageDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String GET_ALL_LANGUAGE_SQL = "select * from language ";

	public List<Language> getAllLanguages() {
		final List<Language> languages = new ArrayList<Language>();
		jdbcTemplate.query(GET_ALL_LANGUAGE_SQL, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				while (rs.next()) {
					Language language = new Language();
					language.setLanguageNumber(rs.getString("language_number"));
					language.setLanguageName(rs.getString("language_name"));
					languages.add(language);
				}
			}
		});
		return languages;
	}

}
