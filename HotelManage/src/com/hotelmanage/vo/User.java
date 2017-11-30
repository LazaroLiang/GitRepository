package com.hotelmanage.vo;

/*
 * 2016-4-5修改
 * 用户基本信息类
 * author:liangzhuang
 * */
public class User {
	/*
	 * userID int identity(1,1) primary key,--用户编号 loginName nvarchar(10) not
	 * null,--登陆名 password nchar(6) not null, --登陆密码 userSex bit default(0),
	 * --用户性别 userName nvarchar(20) not null, --用户姓名 userAge int, --用户年龄 userTel
	 * nchar(11),--联系方式 userAddress nvarchar(50)--用户地址
	 */
	private int userID;
	private String loginName;
	private String password;
	private boolean userSex;
	private String userName;
	private int userAge;
	private String userTel;
	private String userAddress;
	private int userType;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isUserSex() {
		return userSex;
	}

	public void setUserSex(boolean userSex) {
		this.userSex = userSex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "用户编号：" + userID + " " + "用户姓名：" + userName + " " + "登陆名：" + loginName + " " + "登陆密码：" + password + " " + "用户性别：" + userSex + "\n"
				+ "联系方式：" + userTel + " " + "用户年龄：" + userAge + " " + "用户地址：" + userAddress + " 用户类型:" + userType;
	}

}
