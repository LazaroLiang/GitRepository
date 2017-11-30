package com.hotelmanage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IGuestDao;
import com.hotelmanage.vo.GuestInfo;

/*
 * 2016-4-6
 * 客户信息与数据库交互类
 * author:liangzhuang
 * */
public class GuestDaoImpl implements IGuestDao {
	private Connection conn = null;
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

	public GuestDaoImpl(Connection conn) {
		this.conn = conn;
	}

	// 获取客户信息
	public void getGuestInfo(GuestInfo guest) {
		guestID = guest.getGuestID();
		guestName = guest.getGuestName();
		guestSex = guest.isGuestSex();
		guestAge = guest.getGuestAge();
		guestCardID = guest.getGuestCardID();
		guestTel = guest.getGuestTel();
		guestType = guest.getGuestType();
		guestAddress = guest.getGuestAddress();
		guestZip = guest.getGuestZip();
		guestBirthday = guest.getGuestBirthday();
	}

	// 增加客户信息
	public boolean Save(GuestInfo guest) {
		PreparedStatement pstm = null;
		boolean flag = false;
		getGuestInfo(guest);
		String sql = "insert into GuestInfo (guestName,guestSex,guestCardID,guestAddress,guestTel,guestType,guestZip,guestBirthday,guestAge) values ('"
				+ guestName
				+ "','"
				+ guestSex
				+ "','"
				+ guestCardID
				+ "','"
				+ guestAddress
				+ "','"
				+ guestTel
				+ "','"
				+ guestType
				+ "','"
				+ guestZip
				+ "','" + guestBirthday + "','" + guestAge + "');";
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

	public boolean Update(GuestInfo entity) {
		// TODO Auto-generated method stub
		getGuestInfo(entity);
		PreparedStatement pstm = null;
		boolean flag = false;
		String sql = "update GuestInfo set guestName=?,guestSex=?,guestAge=?,guestCardID=?,guestTel=?,guestType=?,guestAddress=?,guestBirthday=?,guestZip=? where guestID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setString(1, guestName);
			pstm.setBoolean(2, guestSex);
			pstm.setInt(3, guestAge);
			pstm.setString(4, guestCardID);
			pstm.setString(5, guestTel);
			pstm.setString(6, guestType);// .setCharacterStream(6, guestType);
			pstm.setString(7, guestAddress);
			pstm.setTimestamp(8, guestBirthday);
			// pstm.setDate(8, guestBirthday);
			pstm.setString(9, guestZip);
			pstm.setInt(10, guestID);
			int rs = pstm.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	public boolean Delete(GuestInfo entity) {
		// TODO Auto-generated method stub
		getGuestInfo(entity);
		boolean flag = false;
		PreparedStatement pstm = null;
		String sql = "delete GuestInfo where guestID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, guestID);
			int rs = pstm.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	public GuestInfo findObjectByID(int id) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		GuestInfo guestInfo = new GuestInfo();
		String sql = "select * from GuestInfo where guestID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				guestInfo.setGuestID(rs.getInt("guestID"));
				guestInfo.setGuestName(rs.getString("guestName"));
				guestInfo.setGuestSex(rs.getBoolean("guestSex"));
				guestInfo.setGuestCardID(rs.getString("guestCardID"));
				guestInfo.setGuestAdress(rs.getString("guestAddress"));
				guestInfo.setGuestTel(rs.getString("guestTel"));
				guestInfo.setGuestType(rs.getString("guestType"));
				guestInfo.setGuestZip(rs.getString("guestZip"));
				guestInfo.setGuestBirthday(rs.getTimestamp("guestBirthday"));
				guestInfo.setGuestAge(rs.getInt("guestAge"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return guestInfo;
	}

	public List<GuestInfo> findAll() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		List<GuestInfo> guestList = new ArrayList<GuestInfo>();
		String sql = "select * from GuestInfo";
		try {
			pstm = this.conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				GuestInfo guestInfo = new GuestInfo();
				guestInfo.setGuestID(rs.getInt("guestID"));
				guestInfo.setGuestName(rs.getString("guestName"));
				guestInfo.setGuestSex(rs.getBoolean("guestSex"));
				guestInfo.setGuestCardID(rs.getString("guestCardID"));
				guestInfo.setGuestAdress(rs.getString("guestAddress"));
				guestInfo.setGuestTel(rs.getString("guestTel"));
				guestInfo.setGuestType(rs.getString("guestType"));
				guestInfo.setGuestZip(rs.getString("guestZip"));
				guestInfo.setGuestBirthday(rs.getTimestamp("guestBirthday"));
				guestInfo.setGuestAge(rs.getInt("guestAge"));
				guestList.add(guestInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return guestList;
	}

	public List<GuestInfo> findGuestInfoBetween(int start, int end) {
		PreparedStatement pstm = null;
		List<GuestInfo> guestList = new ArrayList<GuestInfo>();
//		String sql = "select * from (select row_number() over (order by guestID) as rowNum,* from GuestInfo) as t where rowNum between ? and ?";
		String sql = "select * from GuestInfo limit ?,?";  //mysql:   para1:Start point   para2:record counts  
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, start-1);
			pstm.setInt(2, end-start+1);
//			pstm.setInt(2, end);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				GuestInfo guestInfo = new GuestInfo();
				guestInfo.setGuestID(rs.getInt("guestID"));
				guestInfo.setGuestName(rs.getString("guestName"));
				guestInfo.setGuestSex(rs.getBoolean("guestSex"));
				guestInfo.setGuestCardID(rs.getString("guestCardID"));
				guestInfo.setGuestAdress(rs.getString("guestAddress"));
				guestInfo.setGuestTel(rs.getString("guestTel"));
				guestInfo.setGuestType(rs.getString("guestType"));
				guestInfo.setGuestZip(rs.getString("guestZip"));
				guestInfo.setGuestBirthday(rs.getTimestamp("guestBirthday"));
				guestInfo.setGuestAge(rs.getInt("guestAge"));
				guestList.add(guestInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return guestList;
	}

	public int CountAllGuest() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		String sql = "select count(*) from GuestInfo";
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

	public int SaveAndRetID(GuestInfo guest) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		// boolean flag = false;
		int id = -1;
		getGuestInfo(guest);
		String sql = "insert into GuestInfo (guestName,guestSex,guestCardID,guestAddress,guestTel,guestType,guestZip,guestBirthday,guestAge) values ('"
				+ guestName
				+ "','"
				+ guestSex
				+ "','"
				+ guestCardID
				+ "','"
				+ guestAddress
				+ "','"
				+ guestTel
				+ "','"
				+ guestType
				+ "','"
				+ guestZip
				+ "','" + guestBirthday + "','" + guestAge + "')";
		try {
			pstm = this.conn.prepareStatement(sql);
			int rs = pstm.executeUpdate();
			if (rs > 0) { // 插入操作执行成功
				String sqlString = "select IDENT_CURRENT('GuestInfo')";
				pstm = this.conn.prepareStatement(sqlString);
				ResultSet resultSet = pstm.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return flag;
		return id;
	}
}
