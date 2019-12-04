package com.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.book.domain.Language;
import com.book.util.UUIDUtil;

@Repository
public class LanguageDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String GET_ALL_LANGUAGE_SQL = "select * from language ";

	private static final String SAVE_LANGUAGE_SQL = "insert into language values(?,?,?)";

	private static final String FIND_BYID_SQL = "select * from language  where id = ? ";
	
	
	private static final String EDIT_LANGUAGE_SQL = "update language set language_number = ? ,language_name = ? where id = ? ";
	
	private static final String DELETE_LANGUAGE_SQL = "delete from language where id = ?  ";

	public List<Language> getAllLanguages() {
		final List<Language> languages = new ArrayList<Language>();
		jdbcTemplate.query(GET_ALL_LANGUAGE_SQL, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				while (rs.next()) {
					Language language = new Language();
					language.setId(rs.getString("id"));
					language.setLanguageNumber(rs.getString("language_number"));
					language.setLanguageName(rs.getString("language_name"));
					languages.add(language);
				}
			}
		});
		return languages;
	}

	public int saveLanguage(Language language) {

		return jdbcTemplate.update(SAVE_LANGUAGE_SQL,
				new Object[] { UUIDUtil.expordUuid(), language.getLanguageNumber(), language.getLanguageName() });
	}

	public Language finById(String id) {
		Language language = new Language();
		jdbcTemplate.query(FIND_BYID_SQL, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				language.setId(rs.getString("id"));
				language.setLanguageNumber(rs.getString("language_number"));
				language.setLanguageName(rs.getString("language_name"));

			}
		});
		return language;
	}

	public int editLanguage(Language language) {
		
		return jdbcTemplate.update(EDIT_LANGUAGE_SQL,new Object[]{language.getLanguageNumber(),language.getLanguageName(),language.getId()});
	}

	public int deleteLanguage(String id) {
	
		return jdbcTemplate.update(DELETE_LANGUAGE_SQL,id);
	}

}
