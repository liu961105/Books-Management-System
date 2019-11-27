package com.book.domain;


/**
 * 图书分类
 * @author LZN
 *
 */
public class ClassInfo {
	
	private Integer classId;
	private String  className;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}
