package com.hotelmanage.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.hotelmanage.CommonMethod;
import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.BalanceInfo;
import com.hotelmanage.vo.GuestInfo;
import com.hotelmanage.vo.OrderInfo;
import com.hotelmanage.vo.RoomInfo;

public class MainInfoServelet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		if (cmd.equals("saveOrder")) {
			SaveOrder(request, response);
		} else if (cmd.equals("loadOrderInfo")) {// 展示订单信息
			LoadOrderInfro(request, response);
		} else if (cmd.equals("loadPartOrderInfo")) {// 结算时展示部分订单信息
			LoadPartOrderInfo(request, response);
		} else if (cmd.equals("saveBalance")) {
			SaveBalance(request, response);
		}

	}

	// 结算，保存结算信息
	private void SaveBalance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1 获得页面传递过来的参数
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		int guestID = Integer.parseInt(request.getParameter("guestID"));
		int balanceOperate = Integer.parseInt(request.getParameter("balanceOperate"));
		String outTime = request.getParameter("outTime");
		Timestamp outT = Timestamp.valueOf(outTime);
		String orderType = request.getParameter("orderType");
		float roomCost = Float.parseFloat(request.getParameter("roomCost"));
		float extraCost = Float.parseFloat(request.getParameter("extraCost"));
		float totalCost = Float.parseFloat(request.getParameter("totalCost"));
		float freeCost = Float.parseFloat(request.getParameter("freeCost"));
		float dueCost = Float.parseFloat(request.getParameter("dueCost"));
		String OperateName = request.getParameter("OperateName");

		// 2 更新订单信息、结算表插入数据、更新房间状态
		OrderInfo order = new OrderInfo();
		BalanceInfo balance = new BalanceInfo();
		Boolean result = false;
		order = DaoFactory.getIOrderDaoInstance().findObjectByID(orderID); // 查找相应订单信息
		if (order != null) {
			// 获得需要插入结算表中的结算对象
			balance.setOrderID(orderID);
			balance.setRoomID(roomID);
			balance.setGuestID(guestID);
			balance.setBalanceOperate(balanceOperate);
			balance.setBalanceTime(outT);
			balance.setRoomCost(new BigDecimal(roomCost));
			balance.setExtraCost(new BigDecimal(extraCost));
			balance.setTotalCost(new BigDecimal(totalCost));
			balance.setFreeCost(new BigDecimal(freeCost));
			balance.setDueCost(new BigDecimal(dueCost));
			balance.setOperateName(OperateName);
			balance.setEarnestMoney(order.getOrderMoney());

			order.setOutTime(outT);
			order.setOrderType(CommonMethod.orderTypeReflect(orderType));
			order.setOrderState(true); // 将订单状态置为已结算
			boolean updateOrderReulst = DaoFactory.getIOrderDaoInstance().Update(order); // 更新订单信息
			if (updateOrderReulst) {
				boolean saveBalanceResult = DaoFactory.getIBalanceDaoInstance().Save(balance); // 增加结算信息
				if (saveBalanceResult) {
					RoomInfo room = new RoomInfo();
					room = DaoFactory.getIRoomDaoInstance().findObjectByID(roomID); // 查找房间信息
					if (room != null) {
						room.setRoomState("C"); // 结算后将房间状态置为清洁
						boolean updateRoomResult = DaoFactory.getIRoomDaoInstance().Update(room);// 更新房间信息
						if (updateRoomResult) {
							result = true;
						} else {
							System.out.println("Order订单表已更新，Balance结算表已插入数据,查找Room记录成功,更新Room记录失败！");
						}
					} else {
						System.out.println("Order订单表已更新，Balance结算表已插入数据,查找Room记录失败！");
					}
				} else {
					System.out.println("Order订单表已更新，插入Balance结算表失败！");
				}
			} else {
				System.out.println("更新Order订单表失败！");
			}
		} else {
			System.out.println("未查找到订单信息!");
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

	// 加载部分订单信息，在结算时展示给用户
	private void LoadPartOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		OrderInfo order = DaoFactory.getIOrderDaoInstance().getOrderInfoByroomID(roomID);
		if (order.getOrderID() != 0) { // 查询到订单信息
			GuestInfo guest = DaoFactory.getIGuestDaoInstance().findObjectByID(order.getGuestID());

			/* 取得order相应数据 */
			String inTime = String.valueOf(order.getInTime());
			inTime = inTime.substring(0, 19); // 取数据库中年月日的时间
			String outTime = String.valueOf(order.getOutTime());
			outTime = outTime.substring(0, 19); // 取数据库中年月日的时间
			String orderType = CommonMethod.orderTypeBoolToStr(order.isOrderType());
			// String orderState =
			// CommonMethod.orderStateBoolToStr(order.isOrderState());
			// BigDecimal orderMoney = order.getOrderMoney();

			String guestType = CommonMethod.guestTypeChange(guest.getGuestType());

			// 将order及guest拼接成所需要的JSON格式数据
			JSONObject object = JSONObject.fromObject(order);
			object.put("guestType", guestType);
			object.remove("inTime");
			object.put("inTime", inTime);
			object.remove("outTime");
			object.put("outTime", outTime);
			object.remove("orderType");
			object.put("orderType", orderType);

			String json = null;
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			// response.getWriter().write(json);
			json = "{success:true,data:" + object + "}";
			PrintWriter out = response.getWriter();
			out.print(json);
			System.out.println(json);
		} else { // 未查询到相关订单信息
			String json = null;
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			// response.getWriter().write(json);
			json = "{success:false}";
			PrintWriter out = response.getWriter();
			out.print(json);
		}
	}

	// 加载订单信息
	private void LoadOrderInfro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		OrderInfo order = DaoFactory.getIOrderDaoInstance().getOrderInfoByroomID(roomID); // 此方法查询到的是未结算的订单信息
		if (order.getOrderID() != 0) { // 查询到订单信息
			GuestInfo guest = DaoFactory.getIGuestDaoInstance().findObjectByID(order.getGuestID());

			/* 取得order相应数据 */
			String inTime = String.valueOf(order.getInTime());
			inTime = inTime.substring(0, 19); // 取数据库中年月日的时间
			String outTime = String.valueOf(order.getOutTime());
			outTime = outTime.substring(0, 19); // 取数据库中年月日的时间
			String orderType = CommonMethod.orderTypeBoolToStr(order.isOrderType());
			String orderState = CommonMethod.orderStateBoolToStr(order.isOrderState());
			BigDecimal orderMoney = order.getOrderMoney();

			// 取得guest相应数据
			String timeString = String.valueOf(guest.getGuestBirthday());
			timeString = timeString.substring(0, 10); // 取数据库中年月日的时间
			Boolean sex = guest.isGuestSex(); // 将数据库中的性别true对应到表格中的男，false对应到表格中的女
			String sexString = null;
			if (sex) {
				sexString = "男";
			} else {
				sexString = "女";
			}
			String guestType = CommonMethod.guestTypeChange(guest.getGuestType());

			// 将order及guest拼接成所需要的JSON格式数据
			JSONObject object = JSONObject.fromObject(guest);
			object.remove("guestBirthday");
			object.put("guestBirthday", timeString);
			object.remove("guestSex");
			object.put("guestSex", sexString);
			object.remove("guestType");
			object.put("guestType", guestType);
			object.put("inTime", inTime);
			object.put("outTime", outTime);
			object.put("orderType", orderType);
			object.put("orderState", orderState);
			object.put("orderMoney", orderMoney);
			object.put("orderID", order.getOrderID());

			String json = null;
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			// response.getWriter().write(json);
			json = "{success:true,data:" + object + "}";
			PrintWriter out = response.getWriter();
			out.print(json);
			System.out.println(json);
		} else { // 未查询到相关订单信息
			String json = null;
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			// response.getWriter().write(json);
			json = "{success:false}";
			PrintWriter out = response.getWriter();
			out.print(json);
		}
	}

	// 增加订单信息
	private void SaveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		String inTime = request.getParameter("inTime");
		Timestamp outTimestamp = null;
		Timestamp inTimestamp = Timestamp.valueOf(inTime); // 转换为日期格式
		String outTime = request.getParameter("outTime");
		if (outTime.equals("") || outTime == null) {

		} else {
			outTimestamp = Timestamp.valueOf(outTime);
		}
		// String orderState = request.getParameter("orderState");
		String orderState = "未结算";// 未传递orderState参数
		String orderType = request.getParameter("orderType");
		float orderMoney = Float.parseFloat(request.getParameter("orderMoney"));
		String guestName = (String) request.getParameter("guestName");
		int guestAge = Integer.parseInt(request.getParameter("guestAge"));
		String guestSex = (String) request.getParameter("guestSex");
		String guestCardID = (String) request.getParameter("guestCardID");
		String guestType = (String) request.getParameter("guestType");
		String guestTel = (String) request.getParameter("guestTel");
		String guestAddress = (String) request.getParameter("guestAddress");
		String guestBirthday = (String) request.getParameter("guestBirthday");
		String guestZip = (String) request.getParameter("guestZip");

		OrderInfo order = new OrderInfo();
		boolean result = false;
		/* 将客户信息插入客户信息表，并返回该客户ID */
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
		int guestID = DaoFactory.getIGuestDaoInstance().SaveAndRetID(guestInfo);
		if (guestID != -1) {
			order.setGuestID(guestID);
			order.setGuestName(guestName);
			order.setInTime(inTimestamp);
			order.setOutTime(outTimestamp);
			order.setRoomID(roomID);
			order.setOrderMoney(new BigDecimal(orderMoney));
			order.setOrderState(CommonMethod.orderStateReflect(orderState));
			order.setOrderType(CommonMethod.orderTypeReflect(orderType));
			boolean flag = DaoFactory.getIOrderDaoInstance().Save(order);// 插入订单信息
			if (flag) {
				RoomInfo room = new RoomInfo();
				room = DaoFactory.getIRoomDaoInstance().findObjectByID(roomID);
				room.setRoomState("R");
				result = DaoFactory.getIRoomDaoInstance().Update(room);

			} else {
				System.out.println("出大事了，客户表已经插入了数据，我却不知道怎么回滚！");
			}
		} else {
			System.out.println("出大事了，客户表可能已经插入了数据，我却不知道怎么回滚！");
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
		System.out.println(roomID + " " + inTime + " " + outTime + " " + orderType + " " + orderMoney + " " + guestName + " " + guestSex);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
