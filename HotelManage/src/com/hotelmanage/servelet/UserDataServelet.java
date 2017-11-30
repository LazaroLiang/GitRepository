package com.hotelmanage.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.User;

public class UserDataServelet extends HttpServlet {
	public final static int PAGESIZE = 5; // 分页大小，即每页包含记录条数
	public int countSum = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> userList = new ArrayList<User>();
		User user = new User();
		userList = DaoFactory.getIUserDaoInstance().findUserInfoBetween(1, PAGESIZE);
		countSum = DaoFactory.getIUserDaoInstance().CountAllUser();
		JSONArray jsonItems = new JSONArray();
		Iterator<User> i = userList.iterator();
		while (i.hasNext()) {
			user = (User) i.next();
			// String timeString = String.valueOf(guest.getGuestBirthday());
			// timeString = timeString.substring(0, 10); // 取数据库中年月日的时间
			Boolean sex = user.isUserSex(); // 将数据库中的性别true对应到表格中的男，false对应到表格中的女
			String sexString = null;
			if (sex) {
				sexString = "男";
			} else {
				sexString = "女";
			}
			// 将数据库中存储的顾客类型进行对应
			String userType = null;
			int type = user.getUserType();
			if (1 == type) {
				userType = "管理员";
			} else if (0 == type) {
				userType = "普通用户";
			}
			JSONObject object = JSONObject.fromObject(user);
			object.remove("userSex");
			object.put("userSex", sexString);
			object.remove("userType");
			object.put("userType", userType);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("userList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<User> userList = new ArrayList<User>();
		User user = new User();
		userList = DaoFactory.getIUserDaoInstance().findUserInfoBetween(start + 1, start + limit);
		countSum = DaoFactory.getIUserDaoInstance().CountAllUser();
		JSONArray jsonItems = new JSONArray();
		Iterator<User> i = userList.iterator();
		while (i.hasNext()) {
			user = (User) i.next();
			// String timeString = String.valueOf(guest.getGuestBirthday());
			// timeString = timeString.substring(0, 10); // 取数据库中年月日的时间
			Boolean sex = user.isUserSex(); // 将数据库中的性别true对应到表格中的男，false对应到表格中的女
			String sexString = null;
			if (sex) {
				sexString = "男";
			} else {
				sexString = "女";
			}
			// 将数据库中存储的顾客类型进行对应
			String userType = null;
			int type = user.getUserType();
			if (1 == type) {
				userType = "管理员";
			} else if (0 == type) {
				userType = "普通用户";
			}
			JSONObject object = JSONObject.fromObject(user);
			object.remove("userSex");
			object.put("userSex", sexString);
			object.remove("userType");
			object.put("userType", userType);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("userList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
	}

}
