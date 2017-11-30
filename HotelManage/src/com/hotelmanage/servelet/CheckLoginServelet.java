package com.hotelmanage.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.User;

public class CheckLoginServelet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "login.jsp";
		String userid = (String) request.getParameter("username").trim();
		String password = (String) request.getParameter("password").trim();
		int id = DaoFactory.getIUserDaoInstance().findIDByLoginName(userid);
		// User u= new User();

		User user = new User();
		user = DaoFactory.getIUserDaoInstance().findObjectByID(id);
		user.setLoginName(userid);
		user.setPassword(password);
		try {
			if (DaoFactory.getIUserDaoInstance().checkLogin(user)) {
				// System.out.println("µÇÂ½³É¹¦£¡");
				// System.out.println(user.getUserName());
				response.setContentType("text/json; charset=utf-8");
				response.getWriter().write("{success: true, msg:{username:'" + user.getUserName() + "'}}");
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getUserName());
				session.setAttribute("userID", id);
				session.setAttribute("userType", user.getUserType());
			} else {
				// System.out.println("µÇÂ½Ê§°Ü");
				// System.out.println("¹þ¹þ");
				// request.getRequestDispatcher("index.jsp").forward(request,
				// response);
				response.setContentType("text/json; charset=utf-8");
				response.getWriter().write("{success: false}");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
