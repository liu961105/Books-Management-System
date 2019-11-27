package com.book.domain;

/**
 * 管理员
 * 
 * @author LZN
 *
 */
public class Admin {

	private int adminId;
	private String password;
	private String adminName;
	private String adminage;

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminage() {
		return adminage;
	}

	public void setAdminage(String adminage) {
		this.adminage = adminage;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

}
