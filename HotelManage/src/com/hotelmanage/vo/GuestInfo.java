package com.hotelmanage.vo;

import java.sql.Timestamp;

/*
 * 2016-4-5����
 * �ͻ�������Ϣ��
 * author:liangzhuang
 **/
public class GuestInfo {
	/*
	 * guestID int identity(1,1) primary key, --��� guestName nvarchar(50) not
	 * null, --���� guestSex bit not null, --�Ա� guestCardID nchar(18) not null,
	 * --�˿�֤�������֤�� guestAddress nvarchar(50), --��ַ guestTel nchar(11), --��ϵ��ʽ
	 * guestType nchar(1) not null, --�˿�����:P ��ͨ,H ��Ա,V VIP guestZip nchar(6),
	 * --�ʱ� guestBirthday date,--���� guestAge int --����
	 */
	private int guestID; // ���
	private String guestName; // ����
	private boolean guestSex; // �Ա�
	private String guestCardID; // �˿�֤�������֤��
	private String guestAddress; // ��ַ
	private String guestTel; // ��ϵ��ʽ
	private String guestType; // �˿�����:P ��ͨ,H ��Ա,V VIP
	private String guestZip; // �ʱ�
	private Timestamp guestBirthday; // ����
	private int guestAge; // ����

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
