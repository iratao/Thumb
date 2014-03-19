package com.app.doublecheck.data;

public class User {
	public final int userID;
	public final String userName;
	public String phoneNumber;
	public String email;
	
	public User(int userID, String userName, String phoneNumber, String email){
		this.userID = userID;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

}
