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
	private final static String ALL_CLASS_INFO_SQL = "select * from class_info";
	//添加分类
	private final static String ADD_CLASS_INFO_SQL = "insert into class_info values(?,?)";

	public List<ClassInfo> getAllClassInfos(){
		 final List<ClassInfo> list = new ArrayList<ClassInfo>();
 		 jdbcTemplate.query(ALL_CLASS_INFO_SQL, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				rs.beforeFirst();
				  while (rs.next()){
					  ClassInfo classInfo = new ClassInfo();
					  classInfo.setClassId(rs.getInt("class_id"));
					  classInfo.setClassName(rs.getString("class_name"));
					  list.add(classInfo);
				  }
			}
		 } );
		return list;
	}

	public int classInfoSave(ClassInfo classInfo) {
		
		 return jdbcTemplate.update(ADD_CLASS_INFO_SQL, new Object[]{classInfo.getClassId(),classInfo.getClassName()});
	}

}
