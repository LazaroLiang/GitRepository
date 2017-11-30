package com.hotelmanage.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IRoomDao;
import com.hotelmanage.vo.RoomInfo;

/*
 * 2016-4-7创建
 * 房间信息与数据库底层数据交互类
 * author:liangzhuang
 * */
public class RoomDaoImpl implements IRoomDao {
	Connection coon = null;
	private int roomID; // 房间编号
	private String roomName; // 房间名称，形式S001
	private String roomType; // 房间类型 S 单人间,D 双人间(标准间),T 三人间,B 大床房
	private String roomState; // 房间状态 F 空闲,P 预定,L 锁房(有顾客租用)
	private BigDecimal roomPPrice; // 普通价格
	private BigDecimal roomHPrice; // 会员价格
	private BigDecimal roomVPrice; // VIP价格
	private BigDecimal roomHourPrice; // 钟点房价格/小时 且钟点房价格统一，不享受优惠

	public RoomDaoImpl(Connection conn) {
		this.coon = conn;
	}

	// 获取房间对象中的的各个字段属性值
	private void getRoomInfo(RoomInfo room) {
		roomID = room.getRoomID();
		roomName = room.getRoomName();
		roomType = room.getRoomType();
		roomState = room.getRoomState();
		roomPPrice = room.getRoomPPrice();
		roomHPrice = room.getRoomHPrice();
		roomVPrice = room.getRoomVPrice();
		roomHourPrice = room.getRoomHourPrice();
	}

	// 添加房间信息
	public boolean Save(RoomInfo entity) {
		// TODO Auto-generated method stub
		getRoomInfo(entity);
		PreparedStatement pstm = null;
		boolean flag = false;
		String sql = "insert into RoomInfo(roomName,roomType,roomState,roomPPrice,roomHPrice,roomVPrice,roomHourPrice) values ('" + roomName + "','"
				+ roomType + "','" + roomState + "','" + roomPPrice + "','" + roomHPrice + "','" + roomVPrice + "','" + roomHourPrice + "')";
		try {
			pstm = this.coon.prepareStatement(sql);
			int rs = pstm.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	// 更新房间信息
	public boolean Update(RoomInfo entity) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		boolean flag = false;
		getRoomInfo(entity);
		String sql = "update RoomInfo set roomName=?,roomType=?,roomState=?,roomPPrice=?,roomHPrice=?,roomVPrice=?,roomHourPrice=? where roomID=?";
		try {
			pstm = this.coon.prepareStatement(sql);
			pstm.setString(1, roomName);
			pstm.setString(2, roomType);
			pstm.setString(3, roomState);
			pstm.setBigDecimal(4, roomPPrice);
			pstm.setBigDecimal(5, roomHPrice);
			pstm.setBigDecimal(6, roomVPrice);
			pstm.setBigDecimal(7, roomHourPrice);
			pstm.setInt(8, roomID);
			int rs = pstm.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	// 删除房间信息
	public boolean Delete(RoomInfo entity) {
		// TODO Auto-generated method stub
		getRoomInfo(entity);
		PreparedStatement pstm = null;
		boolean flag = false;
		String sql = "delete RoomInfo where roomID=?";
		try {
			pstm = this.coon.prepareStatement(sql);
			pstm.setInt(1, roomID);
			int rs = pstm.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	// 通过房间ID查找房间信息
	public RoomInfo findObjectByID(int id) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		RoomInfo room = new RoomInfo();
		String sql = "select * from RoomInfo where roomID=?";
		try {
			pstm = this.coon.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				room.setRoomID(rs.getInt("roomID"));
				room.setRoomName(rs.getString("roomName"));
				room.setRoomType(rs.getString("roomType"));
				room.setRoomState(rs.getString("roomState"));
				room.setRoomPPrice(rs.getBigDecimal("roomPPrice"));
				room.setRoomHPrice(rs.getBigDecimal("roomHPrice"));
				room.setRoomVPrice(rs.getBigDecimal("roomVPrice"));
				room.setRoomHourPrice(rs.getBigDecimal("roomHourPrice"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}

	public List<RoomInfo> findAll() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		List<RoomInfo> list = new ArrayList<RoomInfo>();
		String sql = "select * from RoomInfo";
		try {
			pstm = this.coon.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				RoomInfo room = new RoomInfo();
				room.setRoomID(rs.getInt("roomID"));
				room.setRoomName(rs.getString("roomName"));
				room.setRoomType(rs.getString("roomType"));
				room.setRoomState(rs.getString("roomState"));
				room.setRoomPPrice(rs.getBigDecimal("roomPPrice"));
				room.setRoomHPrice(rs.getBigDecimal("roomHPrice"));
				room.setRoomVPrice(rs.getBigDecimal("roomVPrice"));
				room.setRoomHourPrice(rs.getBigDecimal("roomHourPrice"));
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<RoomInfo> findRoomInfoBetween(int start, int end) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		List<RoomInfo> roomList = new ArrayList<RoomInfo>();
//		String sql = "select * from (select row_number() over (order by roomID) as rowNum,* from RoomInfo) as t where rowNum between ? and ?";
		String sql = "select * from RoomInfo limit ?,?";
		try {
			pstm = this.coon.prepareStatement(sql);
			pstm.setInt(1, start-1);
			pstm.setInt(2, end-start+1);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				RoomInfo room = new RoomInfo();
				room.setRoomID(rs.getInt("roomID"));
				room.setRoomName(rs.getString("roomName"));
				room.setRoomType(rs.getString("roomType"));
				room.setRoomState(rs.getString("roomState"));
				room.setRoomPPrice(rs.getBigDecimal("roomPPrice"));
				room.setRoomHPrice(rs.getBigDecimal("roomHPrice"));
				room.setRoomVPrice(rs.getBigDecimal("roomVPrice"));
				room.setRoomHourPrice(rs.getBigDecimal("roomHourPrice"));
				roomList.add(room);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return roomList;
	}

	public int CountAllRoom() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		String sql = "select count(*) from RoomInfo";
		int result = -1;
		try {
			pstm = this.coon.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
