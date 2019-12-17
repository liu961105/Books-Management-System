package com.book.service.impl;

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

	public boolean deleteAdmin(String  adminId) {
		return adminDao.deleteAdmin(adminId)>0;
		
	}

	public Admin findByAdminId(String  adminId) {
		 
		return adminDao.findByAdminId(adminId);
	}

	public boolean editAdmin(Admin admin) {
		
		return adminDao.editAdmin(admin)>0;
	}

}
