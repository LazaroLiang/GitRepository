package com.hotelmanage.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.User;

public class LoadUserInfoServelet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// methodCmd为取得前端传输回来的需要调用的方法类型

		String methodCmd = (String) request.getParameter("cmd");
		/* 若参数为query，表示查询用户信息 */
		if (methodCmd.equals("query")) {
			queryUserInfo(request, response);
		} else if (methodCmd.equals("update")) {// 更新用户
			// System.out.println("调用了！");
			updateUserInfo(request, response);
		} else if (methodCmd.equals("updatePassWord")) { // 更新密码
			updateUserPassWord(request, response);
			// System.out.println("修改密码！");
		} else if (methodCmd.equals("checkPassWord")) { // 检查原密码是否正确
			System.out.println("检查密码是否正确");
			CheckUserPassWord(request, response);
		} else if (methodCmd.equals("checkLoginName")) {
			CheckLoginName(request, response);// 检查用户名是否已经被占用
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void queryUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userID")); // 取得用户ID
		// System.out.println(userID);
		User user = new User();
		user = DaoFactory.getIUserDaoInstance().findObjectByID(userID); // 通过ID查找到User对象
		// System.out.println(user);
		JSONObject testJsonObject = JSONObject.fromObject(user); // 将user对象转换为json对象
		// System.out.println(testJsonObject);
		String json = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		if (user != null) {
			// }";//
			json = "{success:true,data:" + testJsonObject + "}";
			// System.out.println(json);
		} else {
			json = "{success:false}";
		}
		response.getWriter().write(json);
	}

	private void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		String loginName = (String) request.getParameter("loginName");
		String userName = (String) request.getParameter("userName");
		String userSex = (String) (request.getParameter("userSex"));
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		String userTel = (String) request.getParameter("userTel");
		String userAddress = (String) request.getParameter("userAddress");
		System.out.println(userID + " " + loginName + " " + userName + " " + userSex + " " + userAge + " " + userTel + " " + userAddress);
		Boolean sex = null;
		if (userSex.equals("true")) {
			sex = true;
		} else if (userSex.equals("false")) {
			sex = false;
		}
		User user = new User();
		user = DaoFactory.getIUserDaoInstance().findObjectByID(userID);
		user.setLoginName(loginName);
		user.setUserName(userName);
		user.setUserSex(sex);
		user.setUserAge(userAge);
		user.setUserTel(userTel);
		user.setUserAddress(userAddress);
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

	public void updateUserPassWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		String newPassword = (String) request.getParameter("newPasswordOne");
		System.out.println(newPassword);
		User user = new User();
		user = DaoFactory.getIUserDaoInstance().findObjectByID(userID);
		user.setPassword(newPassword);
		System.out.println(user);
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

	public void CheckUserPassWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		String oldPassword = (String) request.getParameter("oldPassword");
		System.out.println("前端输入密码：" + oldPassword + " " + oldPassword.length());
		User user = new User();
		user = DaoFactory.getIUserDaoInstance().findObjectByID(userID);
		// user.setPassword(newPassword);
		System.out.println("后台数据库存储密码：" + user.getPassword() + " " + user.getPassword().length());
		String json = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		if (user.getPassword().equals(oldPassword)) {
			json = "true";
		} else {
			json = "false";
		}
		response.getWriter().write(json);

		// if (result) {
		// json = "{success:true}";
		// } else {
		// json = "{success:false}";
		// }

	}

	public void CheckLoginName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		String loginName = (String) request.getParameter("loginName");

		boolean result = DaoFactory.getIUserDaoInstance().checkLoginName(userID, loginName);
		String json = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		if (result) {
			json = "true";
		} else {
			json = "false";
		}
		response.getWriter().write(json);
	}

}
