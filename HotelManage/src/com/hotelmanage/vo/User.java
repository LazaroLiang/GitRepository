package com.hotelmanage.vo;

/*
 * 2016-4-5�޸�
 * �û�������Ϣ��
 * author:liangzhuang
 * */
public class User {
	/*
	 * userID int identity(1,1) primary key,--�û���� loginName nvarchar(10) not
	 * null,--��½�� password nchar(6) not null, --��½���� userSex bit default(0),
	 * --�û��Ա� userName nvarchar(20) not null, --�û����� userAge int, --�û����� userTel
	 * nchar(11),--��ϵ��ʽ userAddress nvarchar(50)--�û���ַ
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
		return "�û���ţ�" + userID + " " + "�û�������" + userName + " " + "��½����" + loginName + " " + "��½���룺" + password + " " + "�û��Ա�" + userSex + "\n"
				+ "��ϵ��ʽ��" + userTel + " " + "�û����䣺" + userAge + " " + "�û���ַ��" + userAddress + " �û�����:" + userType;
	}

}
