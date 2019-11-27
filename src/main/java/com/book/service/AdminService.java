package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dao.AdminDao;
import com.book.domain.Admin;
/**
 * 
 * @author LZN
 *
 */
@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	public List<Admin> getAllAdmin() {
		return adminDao.getAllAdmin();
	}
	
	public int addAdmin(Admin admin) {
		return adminDao.addAdmin(admin);
	}

}
