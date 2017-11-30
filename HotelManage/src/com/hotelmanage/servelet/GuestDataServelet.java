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
import com.hotelmanage.vo.GuestInfo;

public class GuestDataServelet extends HttpServlet {

	public final static int PAGESIZE = 5; // 分页大小，即每页包含记录条数
	public int countSum = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String cmd = (String) request.getParameter("cmd");
		// System.out.println(cmd);
		List<GuestInfo> guestList = new ArrayList<GuestInfo>();
		GuestInfo guest = new GuestInfo();
		guestList = DaoFactory.getIGuestDaoInstance().findGuestInfoBetween(1, PAGESIZE);
		countSum = DaoFactory.getIGuestDaoInstance().CountAllGuest();
		JSONArray jsonItems = new JSONArray();
		Iterator<GuestInfo> i = guestList.iterator();
		while (i.hasNext()) {
			guest = (GuestInfo) i.next();
			String timeString = String.valueOf(guest.getGuestBirthday());
			timeString = timeString.substring(0, 10); // 取数据库中年月日的时间
			Boolean sex = guest.isGuestSex(); // 将数据库中的性别true对应到表格中的男，false对应到表格中的女
			String sexString = null;
			if (sex) {
				sexString = "男";
			} else {
				sexString = "女";
			}
			// 将数据库中存储的顾客类型进行对应
			String guestType = guest.getGuestType();
			if (guestType.equals("P")) {
				guestType = "普通客户";
			} else if (guestType.equals("H")) {
				guestType = "会员";
			} else if (guestType.equals("V")) {
				guestType = "VIP";
			}
			JSONObject object = JSONObject.fromObject(guest);
			object.remove("guestBirthday");
			object.put("guestBirthday", timeString);
			object.remove("guestSex");
			object.put("guestSex", sexString);
			object.remove("guestType");
			object.put("guestType", guestType);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("guestList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("请求了");
		// doGet(request, response);
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<GuestInfo> guestList = new ArrayList<GuestInfo>();
		GuestInfo guest = new GuestInfo();
		guestList = DaoFactory.getIGuestDaoInstance().findGuestInfoBetween(start + 1, start + limit);
		countSum = DaoFactory.getIGuestDaoInstance().CountAllGuest();
		JSONArray jsonItems = new JSONArray();
		Iterator<GuestInfo> i = guestList.iterator();
		while (i.hasNext()) {
			guest = (GuestInfo) i.next();
			String timeString = String.valueOf(guest.getGuestBirthday());
			timeString = timeString.substring(0, 10); // 取得日期的年月日格式
			Boolean sex = guest.isGuestSex(); // 将数据库中的性别true对应到表格中的男，false对应到表格中的女
			String sexString = null;
			if (sex) {
				sexString = "男";
			} else {
				sexString = "女";
			}
			// 将数据库中存储的顾客类型进行对应
			String guestType = guest.getGuestType();
			if (guestType.equals("P")) {
				guestType = "普通客户";
			} else if (guestType.equals("H")) {
				guestType = "会员";
			} else if (guestType.equals("V")) {
				guestType = "VIP";
			}
			JSONObject object = JSONObject.fromObject(guest);
			object.remove("guestBirthday");
			object.put("guestBirthday", timeString);
			object.remove("guestSex");
			object.put("guestSex", sexString);
			object.remove("guestType");
			object.put("guestType", guestType);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("guestList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
	}

}
