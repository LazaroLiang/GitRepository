package com.hotelmanage.vo;

import java.sql.Timestamp;

/*
 * 2016-4-5创建
 * 客户基本信息类
 * author:liangzhuang
 **/
public class GuestInfo {
	/*
	 * guestID int identity(1,1) primary key, --编号 guestName nvarchar(50) not
	 * null, --姓名 guestSex bit not null, --性别 guestCardID nchar(18) not null,
	 * --顾客证件（身份证） guestAddress nvarchar(50), --地址 guestTel nchar(11), --联系方式
	 * guestType nchar(1) not null, --顾客类型:P 普通,H 会员,V VIP guestZip nchar(6),
	 * --邮编 guestBirthday date,--生日 guestAge int --年龄
	 */
	private int guestID; // 编号
	private String guestName; // 姓名
	private boolean guestSex; // 性别
	private String guestCardID; // 顾客证件（身份证）
	private String guestAddress; // 地址
	private String guestTel; // 联系方式
	private String guestType; // 顾客类型:P 普通,H 会员,V VIP
	private String guestZip; // 邮编
	private Timestamp guestBirthday; // 生日
	private int guestAge; // 年龄

	public int getGuestID() {
		return guestID;
	}

	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public boolean isGuestSex() {
		return guestSex;
	}

	public void setGuestSex(boolean guestSex) {
		this.guestSex = guestSex;
	}

	public String getGuestCardID() {
		return guestCardID;
	}

	public void setGuestCardID(String guestCardID) {
		this.guestCardID = guestCardID;
	}

	public String getGuestAddress() {
		return guestAddress;
	}

	public void setGuestAdress(String guestAddress) {
		this.guestAddress = guestAddress;
	}

	public String getGuestTel() {
		return guestTel;
	}

	public void setGuestTel(String guestTel) {
		this.guestTel = guestTel;
	}

	public String getGuestType() {
		return guestType;
	}

	public void setGuestType(String guestType) {
		this.guestType = guestType;
	}

	public String getGuestZip() {
		return guestZip;
	}

	public void setGuestZip(String guestZip) {
		this.guestZip = guestZip;
	}

	public Timestamp getGuestBirthday() {
		return guestBirthday;
	}

	public void setGuestBirthday(Timestamp guestBirthday) {
		this.guestBirthday = guestBirthday;
	}

	public int getGuestAge() {
		return guestAge;
	}

	public void setGuestAge(int guestAge) {
		this.guestAge = guestAge;
	}

}
