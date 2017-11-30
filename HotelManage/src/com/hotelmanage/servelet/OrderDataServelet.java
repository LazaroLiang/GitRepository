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
import com.hotelmanage.vo.OrderInfo;

public class OrderDataServelet extends HttpServlet {
	public final static int PAGESIZE = 5; // 分页大小，即每页包含记录条数
	public int countSum = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		OrderInfo order = new OrderInfo();
		orderList = DaoFactory.getIOrderDaoInstance().findOrderInfoBetween(1, PAGESIZE);
		countSum = DaoFactory.getIOrderDaoInstance().CountAllOrder();
		JSONArray jsonItems = new JSONArray();
		Iterator<OrderInfo> i = orderList.iterator();
		while (i.hasNext()) {
			order = (OrderInfo) i.next();
			String inTime = String.valueOf(order.getInTime());
			inTime = inTime.substring(0, 19); // 取数据库中年月日的时间
			String outTime = String.valueOf(order.getOutTime());
			outTime = outTime.substring(0, 19); // 取数据库中年月日的时间
			String orderType = CommonMethod.orderTypeBoolToStr(order.isOrderType());
			String orderState = CommonMethod.orderStateBoolToStr(order.isOrderState());
			JSONObject object = JSONObject.fromObject(order);
			object.remove("inTime");
			object.put("inTime", inTime);
			object.remove("outTime");
			object.put("outTime", outTime);
			object.remove("orderType");
			object.put("orderType", orderType);
			object.remove("orderState");
			object.put("orderState", orderState);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("orderList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		OrderInfo order = new OrderInfo();
		orderList = DaoFactory.getIOrderDaoInstance().findOrderInfoBetween(start + 1, start + limit);
		countSum = DaoFactory.getIOrderDaoInstance().CountAllOrder();
		JSONArray jsonItems = new JSONArray();
		Iterator<OrderInfo> i = orderList.iterator();
		while (i.hasNext()) {
			order = (OrderInfo) i.next();
			String inTime = String.valueOf(order.getInTime());
			inTime = inTime.substring(0, 19); // 取数据库中年月日的时间
			String outTime = String.valueOf(order.getOutTime());
			outTime = outTime.substring(0, 19); // 取数据库中年月日的时间
			String orderType = CommonMethod.orderTypeBoolToStr(order.isOrderType());
			String orderState = CommonMethod.orderStateBoolToStr(order.isOrderState());
			JSONObject object = JSONObject.fromObject(order);
			object.remove("inTime");
			object.put("inTime", inTime);
			object.remove("outTime");
			object.put("outTime", outTime);
			object.remove("orderType");
			object.put("orderType", orderType);
			object.remove("orderState");
			object.put("orderState", orderState);
			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("orderList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
	}

}
