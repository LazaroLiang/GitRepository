package com.hotelmanage.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelmanage.CommonMethod;
import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.User;

public class UserInfoServelet extends HttpServlet {
	public final static String FIRST_PASSWORD = "112233"; // 初始密码

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodCmd = (String) request.getParameter("cmd");
		// System.out.println(methodCmd);
		if (methodCmd.equals("save")) {
			SaveUserInfo(request, response);
		} else if (methodCmd.equals("resetPassword")) {
			ResetPassword(request, response);
		} else if (methodCmd.equals("delete")) {
			DeleteUserInfo(request, response);
		}
	}

	// 重置密码
	private void ResetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID = Integer.parseInt(request.getParameter("userID"));
		User user = new User();
		user = DaoFactory.getIUserDaoInstance().findObjectByID(userID);
		user.setPassword(FIRST_PASSWORD);
		boolean result = DaoFactory.getIUserDaoInstance().Update(user);
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

	// 删除用户信息
	private void DeleteUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID = Integer.parseInt(request.getParameter("userID"));
		User user = new User();
		user.setUserID(userID);
		// System.out.println(guestID + " " + guestName + " " + guestAge + " " +
		// guestType + " " + sex + " " + guestBir);
		boolean result = DaoFactory.getIUserDaoInstance().Delete(user);
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

	// 增加用户信息
	private void SaveUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginName = (String) request.getParameter("loginName");
		String userName = (String) request.getParameter("userName");
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		String sex = (String) request.getParameter("userSex");
		String type = (String) request.getParameter("userType");
		String userTel = (String) request.getParameter("userTel");
		String userAddress = (String) request.getParameter("userAddress");
		int userType;
		if (type.equals("管理员")) {
			userType = 1;
		} else {
			userType = 0;
		}
		boolean userSex = CommonMethod.StringToBool(sex);
		User user = new User();
		user.setLoginName(loginName);
		user.setUserName(userName);
		user.setUserAge(userAge);
		user.setUserType(userType);
		user.setUserAddress(userAddress);
		user.setUserSex(userSex);
		user.setUserTel(userTel);
		boolean result = DaoFactory.getIUserDaoInstance().Save(user);
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
