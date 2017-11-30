package com.hotelmanage.dao;

import java.util.List;

import com.hotelmanage.vo.User;

public interface IUserDao extends ICommonDao<User> {
	// 登陆时验证用户名和密码
	public boolean checkLogin(User user) throws Exception;

	// 通过登录名获得用户ID
	public int findIDByLoginName(String loginName);

	// 检查用户名是否被占用
	public boolean checkLoginName(int userID, String loginName);

	// 取固定start-end行的数据，主要是作为分页用
	public List<User> findUserInfoBetween(int start, int end);

	// 查找所有顾客总数
	public int CountAllUser();
}
