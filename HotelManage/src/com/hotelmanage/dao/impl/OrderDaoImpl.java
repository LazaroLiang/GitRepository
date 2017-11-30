package com.hotelmanage.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IOrderDao;
import com.hotelmanage.vo.OrderInfo;

/*
 * 2016-4-7创建
 * 订单与数据库底层的数据交互类
 * author:liangzhuang
 * */
public class OrderDaoImpl implements IOrderDao {
	private Connection conn = null;
	private int orderID;
	private int guestID;
	private int roomID;
	private String guestName;
	private Timestamp inTime;
	private Timestamp outTime;
	private boolean orderState;
	private boolean orderType;
	private BigDecimal orderMoney;

	public OrderDaoImpl(Connection conn) {
		this.conn = conn;
	}

	// 获得订单信息
	public void getOrderInfo(OrderInfo orderInfo) {
		orderID = orderInfo.getOrderID();
		guestID = orderInfo.getGuestID();
		roomID = orderInfo.getRoomID();
		guestName = orderInfo.getGuestName();
		inTime = orderInfo.getInTime();
		outTime = orderInfo.getOutTime();
		orderState = orderInfo.isOrderState();
		orderType = orderInfo.isOrderType();
		orderMoney = orderInfo.getOrderMoney();
	}

	public boolean Save(OrderInfo entity) {
		// TODO Auto-generated method stub
		getOrderInfo(entity);
		PreparedStatement pstm = null;
		Boolean flag = false;
		String sql = "insert into OrderInfo(guestID,roomID,guestName,inTime,outTime,orderState,orderType,orderMoney) values (" + guestID + ","
				+ roomID + ",'" + guestName + "','" + inTime + "','" + outTime + "','" + orderState + "','" + orderType + "','" + orderMoney + "');";
		try {
			pstm = this.conn.prepareStatement(sql);
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

	public boolean Update(OrderInfo entity) {
		// TODO Auto-generated method stub
		getOrderInfo(entity);
		PreparedStatement pstm = null;
		Boolean flag = false;
		String sql = "update OrderInfo set guestID=?,roomID=?,guestName=?,inTime=?,outTime=?,orderState=?,orderType=?,orderMoney=? where orderID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, guestID);
			pstm.setInt(2, roomID);
			pstm.setString(3, guestName);
			pstm.setTimestamp(4, inTime);
			pstm.setTimestamp(5, outTime);
			pstm.setBoolean(6, orderState);
			pstm.setBoolean(7, orderType);
			pstm.setBigDecimal(8, orderMoney);
			pstm.setInt(9, orderID);

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

	public boolean Delete(OrderInfo entity) {
		// TODO Auto-generated method stub
		getOrderInfo(entity);
		PreparedStatement pstm = null;
		Boolean flag = false;
		String sql = "delete OrderInfo where orderID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, orderID);
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

	public OrderInfo findObjectByID(int id) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		OrderInfo orderInfo = new OrderInfo();
		String sql = "select * from OrderInfo where orderID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				orderInfo.setOrderID(rs.getInt("orderID"));
				orderInfo.setGuestID(rs.getInt("guestID"));
				orderInfo.setRoomID(rs.getInt("roomID"));
				orderInfo.setGuestName(rs.getString("guestName"));
				orderInfo.setInTime(rs.getTimestamp("inTime"));
				orderInfo.setOutTime(rs.getTimestamp("outTime"));
				orderInfo.setOrderState(rs.getBoolean("orderState"));
				orderInfo.setOrderType(rs.getBoolean("orderType"));
				orderInfo.setOrderMoney(rs.getBigDecimal("orderMoney"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderInfo;
	}

	public List<OrderInfo> findAll() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		String sql = "select * from OrderInfo";
		try {
			pstm = this.conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderID(rs.getInt("orderID"));
				orderInfo.setGuestID(rs.getInt("guestID"));
				orderInfo.setRoomID(rs.getInt("roomID"));
				orderInfo.setGuestName(rs.getString("guestName"));
				orderInfo.setInTime(rs.getTimestamp("inTime"));
				orderInfo.setOutTime(rs.getTimestamp("outTime"));
				orderInfo.setOrderState(rs.getBoolean("orderState"));
				orderInfo.setOrderType(rs.getBoolean("orderType"));
				orderInfo.setOrderMoney(rs.getBigDecimal("orderMoney"));
				list.add(orderInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// 通过roomID查询订单信息（未结算）
	public OrderInfo getOrderInfoByroomID(int roomID) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		OrderInfo orderInfo = new OrderInfo();
		String sql = "select * from OrderInfo where roomID=? and orderState=0";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, roomID);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				orderInfo.setOrderID(rs.getInt("orderID"));
				orderInfo.setGuestID(rs.getInt("guestID"));
				orderInfo.setRoomID(rs.getInt("roomID"));
				orderInfo.setGuestName(rs.getString("guestName"));
				orderInfo.setInTime(rs.getTimestamp("inTime"));
				orderInfo.setOutTime(rs.getTimestamp("outTime"));
				orderInfo.setOrderState(rs.getBoolean("orderState"));
				orderInfo.setOrderType(rs.getBoolean("orderType"));
				orderInfo.setOrderMoney(rs.getBigDecimal("orderMoney"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderInfo;
	}

	public List<OrderInfo> findOrderInfoBetween(int start, int end) {
		PreparedStatement pstm = null;
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
//		String sql = "select * from (select row_number() over (order by guestID) as rowNum,* from OrderInfo) as t where rowNum between ? and ?";
		String sql = "select * from OrderInfo limit ?,?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, start-1);
			pstm.setInt(2, end-start+1);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderID(rs.getInt("orderID"));
				orderInfo.setGuestID(rs.getInt("guestID"));
				orderInfo.setRoomID(rs.getInt("roomID"));
				orderInfo.setGuestName(rs.getString("guestName"));
				orderInfo.setInTime(rs.getTimestamp("inTime"));
				orderInfo.setOutTime(rs.getTimestamp("outTime"));
				orderInfo.setOrderState(rs.getBoolean("orderState"));
				orderInfo.setOrderType(rs.getBoolean("orderType"));
				orderInfo.setOrderMoney(rs.getBigDecimal("orderMoney"));
				orderList.add(orderInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return orderList;
	}

	public int CountAllOrder() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		String sql = "select count(*) from OrderInfo";
		int result = -1;
		try {
			pstm = this.conn.prepareStatement(sql);
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
