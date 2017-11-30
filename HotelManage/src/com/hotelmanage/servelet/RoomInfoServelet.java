package com.hotelmanage.servelet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelmanage.CommonMethod;
import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.RoomInfo;

public class RoomInfoServelet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodCmd = (String) request.getParameter("cmd");
		// System.out.println(methodCmd);
		if (methodCmd.equals("save")) {
			SaveGuestInfo(request, response);
		} else if (methodCmd.equals("edit")) {
			EditGuestInfo(request, response);
		} else if (methodCmd.equals("delete")) {
			DeleteGuestInfo(request, response);
		} else if (methodCmd.equals("updateRoomState")) {
			updateRoomState(request, response);
		}
	}

	private void updateRoomState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		String roomState = (String) request.getParameter("roomState");
		boolean result = false;
		System.out.println(roomID + " " + roomState);
		RoomInfo room = new RoomInfo();
		room = DaoFactory.getIRoomDaoInstance().findObjectByID(roomID);
		if (room != null) {
			room.setRoomState(roomState);
			result = DaoFactory.getIRoomDaoInstance().Update(room);
		}
		String json = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		if (result) {
			json = "{success:true}";
		} else {
			json = "{success:false}";
		}
		response.getWriter().write(json);
	}

	private void DeleteGuestInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		RoomInfo room = new RoomInfo();
		room.setRoomID(roomID);
		Boolean result = DaoFactory.getIRoomDaoInstance().Delete(room);
		String json = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		if (result) {
			json = "{success:true}";
		} else {
			json = "{success:false}";
		}
		response.getWriter().write(json);
	}

	private void EditGuestInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		String roomName = (String) request.getParameter("roomName");
		String roomState = (String) request.getParameter("roomState");
		String roomType = (String) request.getParameter("roomType");
		float roomPPrice = Float.parseFloat(request.getParameter("roomPPrice"));
		float roomHPrice = Float.parseFloat(request.getParameter("roomHPrice"));
		float roomVPrice = Float.parseFloat(request.getParameter("roomVPrice"));
		float roomHourPrice = Float.parseFloat(request.getParameter("roomHourPrice"));

		roomState = CommonMethod.roomStateRefelct(roomState);
		roomType = CommonMethod.roomTypeRefelct(roomType);
		RoomInfo room = new RoomInfo();
		room.setRoomID(roomID);
		room.setRoomName(roomName);
		room.setRoomState(roomState);
		room.setRoomType(roomType);
		room.setRoomPPrice(new BigDecimal(roomPPrice));
		room.setRoomHPrice(new BigDecimal(roomHPrice));
		room.setRoomVPrice(new BigDecimal(roomVPrice));
		room.setRoomHourPrice(new BigDecimal(roomHourPrice));
		// System.out.println(room);
		Boolean result = DaoFactory.getIRoomDaoInstance().Update(room);
		String json = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		if (result) {
			json = "{success:true}";
		} else {
			json = "{success:false}";
		}
		response.getWriter().write(json);
	}

	private void SaveGuestInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String roomName = (String) request.getParameter("roomName");
		String roomState = (String) request.getParameter("roomState");
		String roomType = (String) request.getParameter("roomType");
		float roomPPrice = Float.parseFloat(request.getParameter("roomPPrice"));
		float roomHPrice = Float.parseFloat(request.getParameter("roomHPrice"));
		float roomVPrice = Float.parseFloat(request.getParameter("roomVPrice"));
		float roomHourPrice = Float.parseFloat(request.getParameter("roomHourPrice"));
		// System.out.println(roomName + " " + roomPPrice);
		roomState = CommonMethod.roomStateRefelct(roomState);
		roomType = CommonMethod.roomTypeRefelct(roomType);
		RoomInfo room = new RoomInfo();
		room.setRoomName(roomName);
		room.setRoomState(roomState);
		room.setRoomType(roomType);
		room.setRoomPPrice(new BigDecimal(roomPPrice));
		room.setRoomHPrice(new BigDecimal(roomHPrice));
		room.setRoomVPrice(new BigDecimal(roomVPrice));
		room.setRoomHourPrice(new BigDecimal(roomHourPrice));
		// System.out.println(room);
		Boolean result = DaoFactory.getIRoomDaoInstance().Save(room);
		String json = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		if (result) {
			json = "{success:true}";
		} else {
			json = "{success:false}";
		}
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
