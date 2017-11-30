package com.hotelmanage.vo;

import java.math.BigDecimal;

/*
 * 2016-4-5����
 * ���������Ϣ��
 * author:liangzhuang
 * */
public class RoomInfo {
	/*
	 * roomID int identity(1,1) primary key, --������ roomName nchar(4) not null,
	 * --�������ƣ���ʽS001 roomType nchar(1) not null, --�������� S ���˼�,D ˫�˼�(��׼��),T ���˼�,B
	 * �󴲷� roomState nchar(1) not null, --����״̬ F ����,P Ԥ��,L ����(�й˿�����), roomPPrice
	 * money not null, --��ͨ�۸� roomHPrice money not null, --��Ա�۸� roomVPrice money
	 * not null,--VIP�۸� roomHourPrice money not null --�ӵ㷿�۸�/Сʱ ���ӵ㷿�۸�ͳһ���������Ż�
	 */
	private int roomID; // ������
	private String roomName; // �������ƣ���ʽS001
	private String roomType; // �������� S ���˼�,D ˫�˼�(��׼��),T ���˼�,B �󴲷�
	private String roomState; // ����״̬ F ����,P Ԥ��,L ����(�й˿�����)
	private BigDecimal roomPPrice; // ��ͨ�۸�
	private BigDecimal roomHPrice; // ��Ա�۸�
	private BigDecimal roomVPrice; // VIP�۸�
	private BigDecimal roomHourPrice; // �ӵ㷿�۸�/Сʱ ���ӵ㷿�۸�ͳһ���������Ż�

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomState() {
		return roomState;
	}

	public void setRoomState(String roomState) {
		this.roomState = roomState;
	}

	public BigDecimal getRoomPPrice() {
		return roomPPrice;
	}

	public void setRoomPPrice(BigDecimal roomPPrice) {
		this.roomPPrice = roomPPrice;
	}

	public BigDecimal getRoomHPrice() {
		return roomHPrice;
	}

	public void setRoomHPrice(BigDecimal roomHPrice) {
		this.roomHPrice = roomHPrice;
	}

	public BigDecimal getRoomVPrice() {
		return roomVPrice;
	}

	public void setRoomVPrice(BigDecimal roomVPrice) {
		this.roomVPrice = roomVPrice;
	}

	public BigDecimal getRoomHourPrice() {
		return roomHourPrice;
	}

	public void setRoomHourPrice(BigDecimal roomHourPrice) {
		this.roomHourPrice = roomHourPrice;
	}

}
