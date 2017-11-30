package com.hotelmanage.servelet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelmanage.CommonMethod;
import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.OrderInfo;

public class OrderInfoServelet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodCmd = (String) request.getParameter("cmd");
		// System.out.println(methodCmd);
		if (methodCmd.equals("save")) {
			// SaveGuestInfo(request, response);
		} else if (methodCmd.equals("edit")) {
			EditOrderInfo(request, response);
		} else if (methodCmd.equals("delete")) {
			DeleteOrderInfo(request, response);
		}
	}

	// 删除订单信息
	private void DeleteOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		OrderInfo order = new OrderInfo();
		order.setOrderID(orderID);
		Boolean result = DaoFactory.getIOrderDaoInstance().Delete(order);
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

	// 编辑订单信息
	private void EditOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		String outTime = request.getParameter("outTime");
		Timestamp outT = Timestamp.valueOf(outTime);
		String orderType = request.getParameter("orderType");
		float orderMoney = Float.parseFloat(request.getParameter("orderMoney"));
		OrderInfo order = new OrderInfo();
		order = DaoFactory.getIOrderDaoInstance().findObjectByID(orderID);
		if (order != null) {
			order.setOutTime(outT);
			order.setOrderType(CommonMethod.orderTypeReflect(orderType));
			order.setOrderMoney(new BigDecimal(orderMoney));
			boolean result = DaoFactory.getIOrderDaoInstance().Update(order);
			if (result) {
				String json = null;
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/json;charset=utf-8");
				if (result) {
					json = "{success:true}";
				} else {
					json = "{success:false}";
				}
				response.getWriter().write(json);
			} else {
				System.out.println("更新订单信息失败！");
			}
		} else {
			System.out.println("查找订单信息失败！");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
