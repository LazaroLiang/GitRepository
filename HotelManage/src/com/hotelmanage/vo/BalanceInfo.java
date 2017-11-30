package com.hotelmanage.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/*
 * 2016-4-5����
 * ������Ϣ��
 * author:liangzhuang
 */
public class BalanceInfo {
	/*
	 * balanceID int identity(1,1) primary key, --���㵥�� orderID int not null,
	 * --������ guestID int not null, --�ͻ�ID roomID int not null, --�����
	 * earnestMoney money not null default('0'), --���� RoomCost money not null,
	 * --���� extraCost money default(0), --�������� totalCost money not null, --�ܷ���
	 * dueCost money not null, --Ӧ���� freeCost money default(0), --�Żݽ��
	 * balanceTime datetime not null, --����ʱ�� balanceOperate int not null,
	 * --����Ա��� OperateName nvarchar(50) --����Ա����
	 */
	private int balanceID;
	private int orderID;
	private int guestID;
	private int roomID;
	private BigDecimal earnestMoney;
	private BigDecimal roomCost;
	private BigDecimal extraCost;
	private BigDecimal totalCost;
	private BigDecimal dueCost;
	private BigDecimal freeCost;
	private Timestamp balanceTime;
	private int balanceOperate;
	private String operateName;

	public int getBalanceID() {
		return balanceID;
	}

	public void setBalanceID(int balanceID) {
		this.balanceID = balanceID;
	}

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

	public BigDecimal getEarnestMoney() {
		return earnestMoney;
	}

	public void setEarnestMoney(BigDecimal earnestMoney) {
		this.earnestMoney = earnestMoney;
	}

	public BigDecimal getRoomCost() {
		return roomCost;
	}

	public void setRoomCost(BigDecimal roomCost) {
		this.roomCost = roomCost;
	}

	public BigDecimal getExtraCost() {
		return extraCost;
	}

	public void setExtraCost(BigDecimal extraCost) {
		this.extraCost = extraCost;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getDueCost() {
		return dueCost;
	}

	public void setDueCost(BigDecimal dueCost) {
		this.dueCost = dueCost;
	}

	public BigDecimal getFreeCost() {
		return freeCost;
	}

	public void setFreeCost(BigDecimal freeCost) {
		this.freeCost = freeCost;
	}

	public Timestamp getBalanceTime() {
		return balanceTime;
	}

	public void setBalanceTime(Timestamp balanceTime) {
		this.balanceTime = balanceTime;
	}

	public int getBalanceOperate() {
		return balanceOperate;
	}

	public void setBalanceOperate(int balanceOperate) {
		this.balanceOperate = balanceOperate;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "�����ţ�" + this.balanceID + " �˿�ID:" + this.guestID + " ����ID:"
				+ this.roomID + " ����ID:" + this.orderID + " ����"
				+ this.earnestMoney + " ���ѣ�" + this.roomCost + " �������ã�"
				+ this.extraCost + " �ܷ��ã�" + this.totalCost + " Ӧ�����ã�"
				+ this.dueCost + " �Żݽ�" + this.freeCost + " ����ʱ��:"
				+ this.balanceTime + " ����ԱID:" + this.balanceOperate
				+ " ����Ա������" + this.operateName;
	}
}
