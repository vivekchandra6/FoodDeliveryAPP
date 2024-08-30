package com.fooddilivery.module;

public class User {
	private int UserID;
	private String Name;
	private String UserName;
	private String password;
	private String email;
	private long phoneNum;
	private String adress;
	private String role;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userID, String name, String userName, String password, String email, long phoneNum, String adress,
			String role) {
		super();
		this.UserID = userID;
		this.Name = name;
		this.UserName = userName;
		this.password = password;
		this.email = email;
		this.phoneNum = phoneNum;
		this.adress = adress;
		this.role = role;
	}
	public User( String name, String userName, String password, String email, long phoneNum, String adress,
			String role) {
		super();
//		UserID = userID;
		this.Name = name;
		this.UserName = userName;
		this.password = password;
		this.email = email;
		this.phoneNum = phoneNum;
		this.adress = adress;
		this.role = role;
	}



	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [UserID=" + this.UserID + ", Name=" + this.Name + ", UserName=" + this.UserName + ", password="
				+ this.password + ", email=" + this.email + ", phoneNum=" + this.phoneNum + ", adress=" + this.adress
				+ ", role=" + this.role + "]";
	}

}
