package com.iratao.thumb.client.model;

public class User {
	public Integer userID;
	public String userName;
	public String phoneNumber;
	public String email;

	public User() {
	}

	public User(Integer userID, String userName, String phoneNumber,
			String email) {
		this.userID = userID;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
