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

import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.BalanceInfo;

public class BalanceDataServelet extends HttpServlet {
	public final static int PAGESIZE = 5; // 分页大小，即每页包含记录条数
	public int countSum = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BalanceInfo> balanceList = new ArrayList<BalanceInfo>();
		BalanceInfo balance = new BalanceInfo();
		balanceList = DaoFactory.getIBalanceDaoInstance().findBalanceInfoBetween(1, PAGESIZE);
		countSum = DaoFactory.getIBalanceDaoInstance().CountAllBalance();
		JSONArray jsonItems = new JSONArray();
		Iterator<BalanceInfo> i = balanceList.iterator();
		while (i.hasNext()) {
			balance = (BalanceInfo) i.next();
			String balanceTime = String.valueOf(balance.getBalanceTime());
			balanceTime = balanceTime.substring(0, 19); // 取数据库中年月日的时间

			JSONObject object = JSONObject.fromObject(balance);
			object.remove("balanceTime");
			object.put("balanceTime", balanceTime);

			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("balanceList", jsonItems);

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
		List<BalanceInfo> balanceList = new ArrayList<BalanceInfo>();
		BalanceInfo balance = new BalanceInfo();
		balanceList = DaoFactory.getIBalanceDaoInstance().findBalanceInfoBetween(start + 1, start + limit);
		countSum = DaoFactory.getIBalanceDaoInstance().CountAllBalance();
		JSONArray jsonItems = new JSONArray();
		Iterator<BalanceInfo> i = balanceList.iterator();
		while (i.hasNext()) {
			balance = (BalanceInfo) i.next();
			String balanceTime = String.valueOf(balance.getBalanceTime());
			balanceTime = balanceTime.substring(0, 19); // 取数据库中年月日的时间

			JSONObject object = JSONObject.fromObject(balance);
			object.remove("balanceTime");
			object.put("balanceTime", balanceTime);

			jsonItems.add(object);
		}
		JSONObject json = new JSONObject();
		json.put("rowCount", countSum); // 总记录数
		json.put("balanceList", jsonItems);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
	}

}
