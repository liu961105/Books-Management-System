package com.book.domain;


/**
 * 图书分类
 * @author LZN
 *
 */
public class ClassInfo {
	
	private String  id;
	private String classId;
	private String  className;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
