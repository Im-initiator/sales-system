package com.leminhtien.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
	
	 
	private String userName;
	 
	private String password;
	 
	private String fullName;
	 
	private String phoneNumber;
	 
	private String address;
	 
	private String email;
	
	private List<UserDTO> list = new ArrayList<UserDTO>();
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<UserDTO> getList() {
		return list;
	}

	public void setList(List<UserDTO> list) {
		this.list = list;
	}

	private Integer status;
	

}
