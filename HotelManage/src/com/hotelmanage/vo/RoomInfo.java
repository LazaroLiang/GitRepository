package com.hotelmanage.vo;

import java.math.BigDecimal;

/*
 * 2016-4-5创建
 * 房间基本信息类
 * author:liangzhuang
 * */
public class RoomInfo {
	/*
	 * roomID int identity(1,1) primary key, --房间编号 roomName nchar(4) not null,
	 * --房间名称，形式S001 roomType nchar(1) not null, --房间类型 S 单人间,D 双人间(标准间),T 三人间,B
	 * 大床房 roomState nchar(1) not null, --房间状态 F 空闲,P 预定,L 锁房(有顾客租用), roomPPrice
	 * money not null, --普通价格 roomHPrice money not null, --会员价格 roomVPrice money
	 * not null,--VIP价格 roomHourPrice money not null --钟点房价格/小时 且钟点房价格统一，不享受优惠
	 */
	private int roomID; // 房间编号
	private String roomName; // 房间名称，形式S001
	private String roomType; // 房间类型 S 单人间,D 双人间(标准间),T 三人间,B 大床房
	private String roomState; // 房间状态 F 空闲,P 预定,L 锁房(有顾客租用)
	private BigDecimal roomPPrice; // 普通价格
	private BigDecimal roomHPrice; // 会员价格
	private BigDecimal roomVPrice; // VIP价格
	private BigDecimal roomHourPrice; // 钟点房价格/小时 且钟点房价格统一，不享受优惠

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
