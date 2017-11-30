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

import com.hotelmanage.CommonMethod;
import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.RoomInfo;

public class RoomListDataServelet extends HttpServlet {
	public final static int PAGESIZE = 5; // 分页大小，即每页包含记录条数
	public int countSum = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoomInfo> roomList = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		roomList = DaoFactory.getIRoomDaoInstance().findRoomInfoBetween(1, PAGESIZE);
		countSum = DaoFactory.getIRoomDaoInstance().CountAllRoom();
		JSONArray jsonItems = new JSONArray();
		Iterator<RoomInfo> i = roomList.iterator();
		while (i.hasNext()) {
			room = (RoomInfo) i.next();
			String roomType = CommonMethod.roomTypeRefelct(room.getRoomType());
			String roomState = CommonMethod.roomStateRefelct(room.getRoomState());
			JSONObject object = JSONObject.fromObject(room);
			object.remove("roomType");
			object.put("roomType", roomType);
			object.remove("roomState");
			object.put("roomState", roomState);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("roomList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		// System.out.println(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<RoomInfo> roomList = new ArrayList<RoomInfo>();
		RoomInfo room = new RoomInfo();
		roomList = DaoFactory.getIRoomDaoInstance().findRoomInfoBetween(start + 1, start + limit);
		countSum = DaoFactory.getIRoomDaoInstance().CountAllRoom();
		JSONArray jsonItems = new JSONArray();
		Iterator<RoomInfo> i = roomList.iterator();
		while (i.hasNext()) {
			room = (RoomInfo) i.next();
			String roomType = CommonMethod.roomTypeRefelct(room.getRoomType());
			String roomState = CommonMethod.roomStateRefelct(room.getRoomState());
			JSONObject object = JSONObject.fromObject(room);
			object.remove("roomType");
			object.put("roomType", roomType);
			object.remove("roomState");
			object.put("roomState", roomState);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("roomList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		// System.out.println(json);
	}

}
