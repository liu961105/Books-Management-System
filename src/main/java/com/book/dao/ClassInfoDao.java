package com.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.book.domain.ClassInfo;
import com.book.domain.Lend;
import com.book.util.UUIDUtil;

/**
 * 分类维护Dao
 * 
 * @author LZN
 *
 */
@Repository
public class ClassInfoDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 所有分类
	private final static String ALL_CLASS_INFO_SQL = "select * from class_info order by  class_id";
	// 添加分类
	private final static String ADD_CLASS_INFO_SQL = "insert into class_info values(?,?,?)";

	private final static String FIND_BY_CLASSID_SQL = "select * from class_info where id = ? ";
	private final static String EDIT_CLASS_INFO_SQL = "update class_info  set  class_name = ? where id = ?";
	private final static String DELETE_CLASS_INFO_SQL = "delete from class_info where id = ?";

	public List<ClassInfo> getAllClassInfos() {
		final List<ClassInfo> list = new ArrayList<ClassInfo>();
		jdbcTemplate.query(ALL_CLASS_INFO_SQL, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				while (rs.next()) {
					ClassInfo classInfo = new ClassInfo();
					classInfo.setId(rs.getString("id"));
					classInfo.setClassId(rs.getString("class_id"));
					classInfo.setClassName(rs.getString("class_name"));
					list.add(classInfo);
				}
			}
		});
		return list;
	}

	public int classInfoSave(ClassInfo classInfo) {

		return jdbcTemplate.update(ADD_CLASS_INFO_SQL,
				new Object[] { UUIDUtil.expordUuid(),classInfo.getClassId(), classInfo.getClassName() });
	}

	public ClassInfo findByClassId(String  id) {
		final ClassInfo classInfo = new ClassInfo();
		jdbcTemplate.query(FIND_BY_CLASSID_SQL, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				classInfo.setId(rs.getString("id"));
				classInfo.setClassId(rs.getString("class_id"));
				classInfo.setClassName(rs.getString("class_name"));
			}
		});
		return classInfo;
	}

	public int editClassInfo(ClassInfo classInfo) {
		return jdbcTemplate.update(EDIT_CLASS_INFO_SQL,
				new Object[] { classInfo.getClassName(), classInfo.getId() });

	}

	public int deleteClassInfo(String  id) {
		return jdbcTemplate.update(DELETE_CLASS_INFO_SQL, id);
	}

}
