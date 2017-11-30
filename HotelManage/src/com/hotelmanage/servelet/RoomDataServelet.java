package com.hotelmanage.servelet;

import java.io.IOException;
import java.io.PrintWriter;
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

public class RoomDataServelet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoomInfo> roomList = DaoFactory.getIRoomDaoInstance().findAll();
		RoomInfo room = new RoomInfo();
		JSONArray jsonItems = new JSONArray();
		Iterator<RoomInfo> i = roomList.iterator();
		while (i.hasNext()) {
			room = (RoomInfo) i.next();
			JSONObject object = JSONObject.fromObject(room);
			String s = room.getRoomType();
			String roomImage, roomTypeString;
			roomTypeString = CommonMethod.roomTypeRefelct(s);
			char roomType = s.charAt(0);
			switch (roomType) {
			case 'S':
				roomImage = "image/rooms/single.gif";
				break;
			default:
				roomImage = "image/rooms/standardroom.gif";
				break;
			}
			String t = room.getRoomState();
			String roomInfoStateAsColor = null;
			char roomState = t.charAt(0);
			switch (roomState) {
			case 'F':
				roomInfoStateAsColor = "#008CD2";
				break;
			case 'R':
				roomInfoStateAsColor = "#FF7D00";
				break;
			case 'C':
				roomInfoStateAsColor = "#00AF4D";
				break;
			case 'L':
				roomInfoStateAsColor = "#E80033";
				break;
			default:
				break;
			}
			object.put("roomImage", roomImage);
			object.put("roomInfoStateAsColor", roomInfoStateAsColor);
			object.remove("roomType");
			object.put("roomType", roomTypeString);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("roomList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
