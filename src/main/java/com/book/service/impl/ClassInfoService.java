package com.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.dao.ClassInfoDao;
import com.book.domain.ClassInfo;

@Service
@Transactional
public class ClassInfoService {
	@Autowired
	private ClassInfoDao classInfoDao;

	public List<ClassInfo> getClassInfos() {
		return classInfoDao.getAllClassInfos();

	}

	public int classInfoSave(ClassInfo classInfo) {
	
		return classInfoDao.classInfoSave(classInfo);
	}
	
	public ClassInfo findByClassId(String  id){
		return classInfoDao.findByClassId(id);
	}
	public int  editClassInfo(ClassInfo classInfo) {
		return classInfoDao.editClassInfo(classInfo);
		
	}

	public boolean deleteClassInfo(String  id) {
		 return classInfoDao.deleteClassInfo(id)>0;
		
	}
}
