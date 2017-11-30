package com.hotelmanage.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/*
 * 2016-4-5����
 * ������Ϣ��
 * author:liangzhuang
 */
public class OrderInfo {
	/*
	 * orderID int identity(1,1) primary key,--������ guestID int not null, --�û�ID
	 * roomID int not null, --�����ź� guestName nvarchar(50) not null,--���� inTime
	 * datetime not null,--��סʱ�� outTime datetime ,--�˷�ʱ�� orderState bit not null
	 * default('0'),--����״̬ 1 �ѽ��� 0 δ���� orderType bit not null default('0')--0
	 * ���ӵ㷿 1 �ӵ㷿
	 */
	private int orderID;
	private int guestID;
	private int roomID;
	private String guestName;
	private Timestamp inTime;
	private Timestamp outTime;
	private boolean orderState;
	private boolean orderType;
	private BigDecimal orderMoney;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getGuestID() {
		return guestID;
	}

	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Timestamp getInTime() {
		return inTime;
	}

	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}

	public Timestamp getOutTime() {
		return outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	public boolean isOrderState() {
		return orderState;
	}

	public void setOrderState(boolean orderState) {
		this.orderState = orderState;
	}

	public boolean isOrderType() {
		return orderType;
	}

	public void setOrderType(boolean orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.orderID + " " + this.guestID + " " + this.roomID + " " + this.guestName + " " + this.inTime + " " + this.outTime + " "
				+ this.orderState + " " + this.orderType + " " + this.orderMoney;
	}

}
