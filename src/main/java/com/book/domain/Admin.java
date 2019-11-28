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
	private String gender;
	private String address;
	private String phone;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
