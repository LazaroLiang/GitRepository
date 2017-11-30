package com.hotelmanage.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IBalanceDao;
import com.hotelmanage.vo.BalanceInfo;

/*
 * 2016-4-7创建
 * 结算信息与底层数据库交互的类
 * author:liangzhuang
 * */
public class BalanceDaoImpl implements IBalanceDao {
	private Connection conn = null;
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

	private void getBalanceInfo(BalanceInfo balanceInfo) {
		balanceID = balanceInfo.getBalanceID();
		orderID = balanceInfo.getOrderID();
		guestID = balanceInfo.getGuestID();
		roomID = balanceInfo.getRoomID();
		earnestMoney = balanceInfo.getEarnestMoney();
		roomCost = balanceInfo.getRoomCost();
		extraCost = balanceInfo.getExtraCost();
		totalCost = balanceInfo.getTotalCost();
		dueCost = balanceInfo.getDueCost();
		freeCost = balanceInfo.getFreeCost();
		balanceTime = balanceInfo.getBalanceTime();
		balanceOperate = balanceInfo.getBalanceOperate();
		operateName = balanceInfo.getOperateName();
	}

	public BalanceDaoImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean Save(BalanceInfo entity) {
		PreparedStatement pstm = null;
		boolean flag = false;
		getBalanceInfo(entity);
		String sql = "insert into BalanceInfo (orderID,guestID,roomID,earnestMoney,roomCost,extraCost,totalCost,dueCost,freeCost,balanceTime,balanceOperate,operateName) values('"
				+ orderID
				+ "','"
				+ guestID
				+ "','"
				+ roomID
				+ "','"
				+ earnestMoney
				+ "','"
				+ roomCost
				+ "','"
				+ extraCost
				+ "','"
				+ totalCost
				+ "','" + dueCost + "','" + freeCost + "','" + balanceTime + "','" + balanceOperate + "','" + operateName + "')";
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

	public boolean Update(BalanceInfo entity) {
		// TODO Auto-generated method stub
		getBalanceInfo(entity);
		PreparedStatement pstm = null;
		boolean flag = false;
		String sql = "update BalanceInfo set orderID=?,guestID=?,roomID=?,earnestMoney=?,roomCost=?,extraCost=?,totalCost=?,dueCost=?,freeCost=?,balanceTime=?,balanceOperate=?,operateName=? where balanceID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, orderID);
			pstm.setInt(2, guestID);
			pstm.setInt(3, roomID);
			pstm.setBigDecimal(4, earnestMoney);
			pstm.setBigDecimal(5, roomCost);
			pstm.setBigDecimal(6, extraCost);
			pstm.setBigDecimal(7, totalCost);
			pstm.setBigDecimal(8, dueCost);
			pstm.setBigDecimal(9, freeCost);
			pstm.setTimestamp(10, balanceTime);
			pstm.setInt(11, balanceOperate);
			pstm.setString(12, operateName);
			pstm.setInt(13, balanceID);
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

	public boolean Delete(BalanceInfo entity) {
		// TODO Auto-generated method stub
		getBalanceInfo(entity);
		PreparedStatement pstm = null;
		boolean flag = false;
		String sql = "delete BalanceInfo where balanceID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, balanceID);
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

	public BalanceInfo findObjectByID(int id) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		BalanceInfo balance = new BalanceInfo();
		String sql = "select * from BalanceInfo where balanceID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				balance.setBalanceID(rs.getInt("balanceID"));
				balance.setOrderID(rs.getInt("orderID"));
				balance.setGuestID(rs.getInt("guestID"));
				balance.setRoomID(rs.getInt("roomID"));
				balance.setRoomCost(rs.getBigDecimal("roomCost"));
				balance.setEarnestMoney(rs.getBigDecimal("earnestMoney"));
				balance.setExtraCost(rs.getBigDecimal("extraCost"));
				balance.setDueCost(rs.getBigDecimal("dueCost"));
				balance.setTotalCost(rs.getBigDecimal("totalCost"));
				balance.setFreeCost(rs.getBigDecimal("freeCost"));
				balance.setOperateName(rs.getString("operateName"));
				balance.setBalanceOperate(rs.getInt("balanceOperate"));
				balance.setBalanceTime(rs.getTimestamp("balanceTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return balance;
	}

	public List<BalanceInfo> findAll() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		List<BalanceInfo> list = new ArrayList<BalanceInfo>();
		String sql = "select * from BalanceInfo";
		try {
			pstm = this.conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				BalanceInfo balance = new BalanceInfo();
				balance.setBalanceID(rs.getInt("balanceID"));
				balance.setOrderID(rs.getInt("orderID"));
				balance.setGuestID(rs.getInt("guestID"));
				balance.setRoomID(rs.getInt("roomID"));
				balance.setRoomCost(rs.getBigDecimal("roomCost"));
				balance.setEarnestMoney(rs.getBigDecimal("earnestMoney"));
				balance.setExtraCost(rs.getBigDecimal("extraCost"));
				balance.setDueCost(rs.getBigDecimal("dueCost"));
				balance.setTotalCost(rs.getBigDecimal("totalCost"));
				balance.setFreeCost(rs.getBigDecimal("freeCost"));
				balance.setOperateName(rs.getString("operateName"));
				balance.setBalanceOperate(rs.getInt("balanceOperate"));
				balance.setBalanceTime(rs.getTimestamp("balanceTime"));
				list.add(balance);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<BalanceInfo> findBalanceInfoBetween(int start, int end) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		List<BalanceInfo> balanceList = new ArrayList<BalanceInfo>();
//		String sql = "select * from (select row_number() over (order by balanceTime desc) as rowNum,* from BalanceInfo) as t where rowNum between ? and ?";
		String sql = "select * from BalanceInfo limit ?,?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, start-1);
			pstm.setInt(2, end-start+1);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				BalanceInfo balance = new BalanceInfo();
				balance.setBalanceID(rs.getInt("balanceID"));
				balance.setOrderID(rs.getInt("orderID"));
				balance.setGuestID(rs.getInt("guestID"));
				balance.setRoomID(rs.getInt("roomID"));
				balance.setRoomCost(rs.getBigDecimal("roomCost"));
				balance.setEarnestMoney(rs.getBigDecimal("earnestMoney"));
				balance.setExtraCost(rs.getBigDecimal("extraCost"));
				balance.setDueCost(rs.getBigDecimal("dueCost"));
				balance.setTotalCost(rs.getBigDecimal("totalCost"));
				balance.setFreeCost(rs.getBigDecimal("freeCost"));
				balance.setOperateName(rs.getString("operateName"));
				balance.setBalanceOperate(rs.getInt("balanceOperate"));
				balance.setBalanceTime(rs.getTimestamp("balanceTime"));
				balanceList.add(balance);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return balanceList;
	}

	public int CountAllBalance() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		String sql = "select count(*) from BalanceInfo";
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
