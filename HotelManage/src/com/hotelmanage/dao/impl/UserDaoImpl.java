package com.hotelmanage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IUserDao;
import com.hotelmanage.vo.User;

/*
 * 2016-4-5
 * �û���Ϣ�����ݿ⽻����
 * author:liangzhuang
 * */
public class UserDaoImpl implements IUserDao {
	private Connection conn = null;
	private int userID;
	private String loginName;
	private String password;
	private boolean userSex;
	private String userName;
	private int userAge;
	private String userTel;
	private String userAddress;
	private int userType;

	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}

	// ��ȡ�û���Ϣ
	public void getUserInfo(User user) {
		userID = user.getUserID();
		loginName = user.getLoginName();
		password = user.getPassword();
		userSex = user.isUserSex();
		userName = user.getUserName();
		userAge = user.getUserAge();
		userAddress = user.getUserAddress();
		userTel = user.getUserTel();
		userType = user.getUserType();
	}

	// �����û���Ϣ
	public boolean Save(User entity) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		Boolean flag = false;
		getUserInfo(entity);
		String sql = "insert into UserInfo(loginName,userSex,userName,userAge,userTel,userAddress,userType) values ('" + loginName + "','" + userSex
				+ "','" + userName + "','" + userAge + "','" + userTel + "','" + userAddress + "','" + userType + "');";
		try {
			pstm = this.conn.prepareStatement(sql);
			int rs = pstm.executeUpdate(); // ִ�в�ѯ�õ������
			if (rs != 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	// �����û���Ϣ
	public boolean Update(User user) {
		// TODO Auto-generated method stub
		getUserInfo(user);
		boolean flag = false;
		PreparedStatement pstm = null;
		String sql = "update UserInfo set password=?,loginName=?,userName=?,userAge=?,userTel=?,userAddress=?,userSex=?,userType=? where userID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setString(1, password);
			pstm.setString(2, loginName);
			pstm.setString(3, userName);
			pstm.setInt(4, userAge);
			pstm.setString(5, userTel);
			pstm.setString(6, userAddress);
			pstm.setBoolean(7, userSex);
			pstm.setInt(8, userType);
			pstm.setInt(9, userID);
			int rs = pstm.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return flag;
	}

	// ɾ���û���Ϣ
	public boolean Delete(User user) {
		// TODO Auto-generated method stub
		int id = user.getUserID();
		boolean flag = false;
		PreparedStatement pstm = null;
		String sql = "delete userinfo where userid=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, id);
			int rs = pstm.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	// ͨ���û�ID�����û���Ϣ
	public User findObjectByID(int userID) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		User user = new User();
		String sql = "select * from UserInfo where userID=?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, userID);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				// flag=true;
				user.setLoginName(rs.getString("loginName"));
				user.setUserID(rs.getInt("userID"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setUserSex(rs.getBoolean("userSex"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserTel(rs.getString("userTel"));
				user.setUserType(rs.getInt("userType"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	// ��½ʱ��֤�û���������
	public boolean checkLogin(User user) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		Boolean flag = false;
		String nameString = user.getLoginName();
		String password = user.getPassword();
		String sql = "select userName from UserInfo where loginName=? and password =?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setString(1, nameString);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery(); // ִ�в�ѯ�õ������
			if (rs.next()) {
				flag = true;
				user.setUserName(rs.getString(1));// ȡ���û�����ʵ����
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	// ���������û���Ϣ
	public List<User> findAll() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;

		List<User> list = new ArrayList<User>();
		String sql = "select * from UserInfo";
		try {
			pstm = this.conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				User user = new User(); // ֮�����ڴ˴���������Ϊlist����Ӷ���ʱ�Ǵ�ŵĶ�������ã����ŵ�����������ǰ����ӵĶ���ᱻ������ӵĶ���������
				user.setLoginName(rs.getString("loginName"));
				user.setUserID(rs.getInt("userID"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setUserSex(rs.getBoolean("userSex"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserTel(rs.getString("userTel"));
				user.setUserType(rs.getInt("userType"));
				// System.out.println(user.getUserID() + " " +
				// user.getUserName());
				list.add(user);
				// user.setUserName(null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public int findIDByLoginName(String loginName) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		String sql = "select userID from UserInfo where loginName=?";
		int ID = -1;
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setString(1, loginName);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				ID = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ID;
	}

	// ����޸��û���¼��ʱ�Ƿ��ѱ�ռ��
	public boolean checkLoginName(int userID, String loginName) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		Boolean flag = false;
		String sql = "select userName from UserInfo where loginName=? and userID <>?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setString(1, loginName);
			pstm.setInt(2, userID);
			ResultSet rs = pstm.executeQuery(); // ִ�в�ѯ�õ������
			if (rs.next()) {
				flag = true;
				// user.setUserName(rs.getString(1));// ȡ���û�����ʵ����
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	public List<User> findUserInfoBetween(int start, int end) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		List<User> userList = new ArrayList<User>();
//		String sql = "select * from (select row_number() over (order by userID) as rowNum,* from UserInfo) as t where rowNum between ? and ?";
		String sql = "select * from UserInfo limit ?,?";
		try {
			pstm = this.conn.prepareStatement(sql);
			pstm.setInt(1, start-1);
			pstm.setInt(2, end-start+1);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				User user = new User(); // ֮�����ڴ˴���������Ϊlist����Ӷ���ʱ�Ǵ�ŵĶ�������ã����ŵ�����������ǰ����ӵĶ���ᱻ������ӵĶ���������
				user.setLoginName(rs.getString("loginName"));
				user.setUserID(rs.getInt("userID"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setUserSex(rs.getBoolean("userSex"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserTel(rs.getString("userTel"));
				user.setUserType(rs.getInt("userType"));
				// �޸ģ�����û�����
				userList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userList;
	}

	public int CountAllUser() {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		String sql = "select count(*) from UserInfo";
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
