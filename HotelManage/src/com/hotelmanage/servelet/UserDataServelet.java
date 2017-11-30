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
	public final static int PAGESIZE = 5; // ��ҳ��С����ÿҳ������¼����
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
			// timeString = timeString.substring(0, 10); // ȡ���ݿ��������յ�ʱ��
			Boolean sex = user.isUserSex(); // �����ݿ��е��Ա�true��Ӧ������е��У�false��Ӧ������е�Ů
			String sexString = null;
			if (sex) {
				sexString = "��";
			} else {
				sexString = "Ů";
			}
			// �����ݿ��д洢�Ĺ˿����ͽ��ж�Ӧ
			String userType = null;
			int type = user.getUserType();
			if (1 == type) {
				userType = "����Ա";
			} else if (0 == type) {
				userType = "��ͨ�û�";
			}
			JSONObject object = JSONObject.fromObject(user);
			object.remove("userSex");
			object.put("userSex", sexString);
			object.remove("userType");
			object.put("userType", userType);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // �ܼ�¼��
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
			// timeString = timeString.substring(0, 10); // ȡ���ݿ��������յ�ʱ��
			Boolean sex = user.isUserSex(); // �����ݿ��е��Ա�true��Ӧ������е��У�false��Ӧ������е�Ů
			String sexString = null;
			if (sex) {
				sexString = "��";
			} else {
				sexString = "Ů";
			}
			// �����ݿ��д洢�Ĺ˿����ͽ��ж�Ӧ
			String userType = null;
			int type = user.getUserType();
			if (1 == type) {
				userType = "����Ա";
			} else if (0 == type) {
				userType = "��ͨ�û�";
			}
			JSONObject object = JSONObject.fromObject(user);
			object.remove("userSex");
			object.put("userSex", sexString);
			object.remove("userType");
			object.put("userType", userType);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // �ܼ�¼��
		json.put("userList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
	}

}
