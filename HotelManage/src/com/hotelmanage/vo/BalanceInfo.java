package com.hotelmanage.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/*
 * 2016-4-5创建
 * 结算信息类
 * author:liangzhuang
 */
public class BalanceInfo {
	/*
	 * balanceID int identity(1,1) primary key, --结算单号 orderID int not null,
	 * --订单号 guestID int not null, --客户ID roomID int not null, --房间号
	 * earnestMoney money not null default('0'), --定金 RoomCost money not null,
	 * --房费 extraCost money default(0), --其它费用 totalCost money not null, --总费用
	 * dueCost money not null, --应付款 freeCost money default(0), --优惠金额
	 * balanceTime datetime not null, --结算时间 balanceOperate int not null,
	 * --操作员编号 OperateName nvarchar(50) --操作员姓名
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
		return "结算编号：" + this.balanceID + " 顾客ID:" + this.guestID + " 房间ID:"
				+ this.roomID + " 订单ID:" + this.orderID + " 定金："
				+ this.earnestMoney + " 房费：" + this.roomCost + " 其它费用："
				+ this.extraCost + " 总费用：" + this.totalCost + " 应付费用："
				+ this.dueCost + " 优惠金额：" + this.freeCost + " 结算时间:"
				+ this.balanceTime + " 操作员ID:" + this.balanceOperate
				+ " 操作员姓名：" + this.operateName;
	}
}
