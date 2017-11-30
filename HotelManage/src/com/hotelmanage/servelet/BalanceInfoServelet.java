package com.hotelmanage.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.BalanceInfo;
import com.hotelmanage.vo.OrderInfo;

public class BalanceInfoServelet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodCmd = (String) request.getParameter("cmd");
		// System.out.println(methodCmd);
		if (methodCmd.equals("save")) {
			// SaveGuestInfo(request, response);
		} else if (methodCmd.equals("edit")) {
			// EditOrderInfo(request, response);
		} else if (methodCmd.equals("delete")) {
			DeleteBalanceInfo(request, response);
		}
	}

	private void DeleteBalanceInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int balanceID = Integer.parseInt(request.getParameter("balanceID"));
		BalanceInfo balance = new BalanceInfo();
		OrderInfo order = new OrderInfo();
		balance = DaoFactory.getIBalanceDaoInstance().findObjectByID(balanceID);
		Boolean result = false;
		if (balance != null) {
			order.setOrderID(balance.getOrderID());
			if (DaoFactory.getIOrderDaoInstance().Delete(order)) {
				if (DaoFactory.getIBalanceDaoInstance().Delete(balance)) {
					result = true;
				} else {
					System.out.println("删除订单信息成功，删除结算信息失败！");
				}
			} else {
				System.out.println("删除订单信息失败！");
			}
		} else {
			System.out.println("查找结算信息失败！");
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
