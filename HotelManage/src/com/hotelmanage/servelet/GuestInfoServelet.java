package com.hotelmanage.servelet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelmanage.CommonMethod;
import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.GuestInfo;

public class GuestInfoServelet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodCmd = (String) request.getParameter("cmd");
		// System.out.println(methodCmd);
		if (methodCmd.equals("save")) {
			SaveGuestInfo(request, response);
		} else if (methodCmd.equals("edit")) {
			EditGuestInfo(request, response);
		} else if (methodCmd.equals("delete")) {
			DeleteGuestInfo(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// 添加客户
	public void SaveGuestInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String guestName = (String) request.getParameter("guestName");
		int guestAge = Integer.parseInt(request.getParameter("guestAge"));
		String guestSex = (String) request.getParameter("guestSex");
		String guestCardID = (String) request.getParameter("guestCardID");
		String guestType = (String) request.getParameter("guestType");
		String guestTel = (String) request.getParameter("guestTel");
		String guestAddress = (String) request.getParameter("guestAddress");
		String guestBirthday = (String) request.getParameter("guestBirthday");
		String guestZip = (String) request.getParameter("guestZip");

		// Timestamp sqlDate = Timestamp.valueOf(guestBirthday);
		Boolean sex = CommonMethod.StringToBool(guestSex);
		Timestamp guestBir = CommonMethod.StringToDate(guestBirthday);
		guestType = CommonMethod.guestTypeChange(guestType);
		GuestInfo guestInfo = new GuestInfo();
		guestInfo.setGuestName(guestName);
		guestInfo.setGuestAge(guestAge);
		guestInfo.setGuestSex(sex);
		guestInfo.setGuestCardID(guestCardID);
		guestInfo.setGuestType(guestType);
		guestInfo.setGuestTel(guestTel);
		guestInfo.setGuestAdress(guestAddress);
		guestInfo.setGuestBirthday(guestBir);
		guestInfo.setGuestZip(guestZip);
		// System.out.println(guestName + " " + guestAge + " " + guestType + " "
		// + sex + " " + guestBir);
		boolean result = DaoFactory.getIGuestDaoInstance().Save(guestInfo);
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

	// 修改客户
	public void EditGuestInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int guestAge = Integer.parseInt(request.getParameter("guestAge"));
		String guestName = (String) request.getParameter("guestName");
		int guestID = Integer.parseInt(request.getParameter("guestID"));
		String guestSex = (String) request.getParameter("guestSex");
		String guestCardID = (String) request.getParameter("guestCardID");
		String guestType = (String) request.getParameter("guestType");
		String guestTel = (String) request.getParameter("guestTel");
		String guestAddress = (String) request.getParameter("guestAddress");
		String guestBirthday = (String) request.getParameter("guestBirthday");
		String guestZip = (String) request.getParameter("guestZip");
		
		System.out.println("********guestName:"+guestName);

		// Timestamp sqlDate = Timestamp.valueOf(guestBirthday);
		Boolean sex = CommonMethod.StringToBool(guestSex);
		Timestamp guestBir = CommonMethod.StringToDate(guestBirthday);
		guestType = CommonMethod.guestTypeChange(guestType);
		GuestInfo guestInfo = new GuestInfo();
		guestInfo.setGuestID(guestID);
		guestInfo.setGuestName(guestName);
		guestInfo.setGuestAge(guestAge);
		guestInfo.setGuestSex(sex);
		guestInfo.setGuestCardID(guestCardID);
		guestInfo.setGuestType(guestType);
		guestInfo.setGuestTel(guestTel);
		guestInfo.setGuestAdress(guestAddress);
		guestInfo.setGuestBirthday(guestBir);
		guestInfo.setGuestZip(guestZip);
		// System.out.println(guestID + " " + guestName + " " + guestAge + " " +
		// guestType + " " + sex + " " + guestBir);
		boolean result = DaoFactory.getIGuestDaoInstance().Update(guestInfo);
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

	// 删除客户
	public void DeleteGuestInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int guestID = Integer.parseInt(request.getParameter("guestID"));
		GuestInfo guestInfo = new GuestInfo();
		guestInfo.setGuestID(guestID);
		// System.out.println(guestID + " " + guestName + " " + guestAge + " " +
		// guestType + " " + sex + " " + guestBir);
		boolean result = DaoFactory.getIGuestDaoInstance().Delete(guestInfo);
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
}
