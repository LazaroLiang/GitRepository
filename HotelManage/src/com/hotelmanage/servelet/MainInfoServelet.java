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
		} else if (cmd.equals("loadOrderInfo")) {// չʾ������Ϣ
			LoadOrderInfro(request, response);
		} else if (cmd.equals("loadPartOrderInfo")) {// ����ʱչʾ���ֶ�����Ϣ
			LoadPartOrderInfo(request, response);
		} else if (cmd.equals("saveBalance")) {
			SaveBalance(request, response);
		}

	}

	// ���㣬���������Ϣ
	private void SaveBalance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1 ���ҳ�洫�ݹ����Ĳ���
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

		// 2 ���¶�����Ϣ�������������ݡ����·���״̬
		OrderInfo order = new OrderInfo();
		BalanceInfo balance = new BalanceInfo();
		Boolean result = false;
		order = DaoFactory.getIOrderDaoInstance().findObjectByID(orderID); // ������Ӧ������Ϣ
		if (order != null) {
			// �����Ҫ���������еĽ������
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
			order.setOrderState(true); // ������״̬��Ϊ�ѽ���
			boolean updateOrderReulst = DaoFactory.getIOrderDaoInstance().Update(order); // ���¶�����Ϣ
			if (updateOrderReulst) {
				boolean saveBalanceResult = DaoFactory.getIBalanceDaoInstance().Save(balance); // ���ӽ�����Ϣ
				if (saveBalanceResult) {
					RoomInfo room = new RoomInfo();
					room = DaoFactory.getIRoomDaoInstance().findObjectByID(roomID); // ���ҷ�����Ϣ
					if (room != null) {
						room.setRoomState("C"); // ����󽫷���״̬��Ϊ���
						boolean updateRoomResult = DaoFactory.getIRoomDaoInstance().Update(room);// ���·�����Ϣ
						if (updateRoomResult) {
							result = true;
						} else {
							System.out.println("Order�������Ѹ��£�Balance������Ѳ�������,����Room��¼�ɹ�,����Room��¼ʧ�ܣ�");
						}
					} else {
						System.out.println("Order�������Ѹ��£�Balance������Ѳ�������,����Room��¼ʧ�ܣ�");
					}
				} else {
					System.out.println("Order�������Ѹ��£�����Balance�����ʧ�ܣ�");
				}
			} else {
				System.out.println("����Order������ʧ�ܣ�");
			}
		} else {
			System.out.println("δ���ҵ�������Ϣ!");
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

	// ���ز��ֶ�����Ϣ���ڽ���ʱչʾ���û�
	private void LoadPartOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		OrderInfo order = DaoFactory.getIOrderDaoInstance().getOrderInfoByroomID(roomID);
		if (order.getOrderID() != 0) { // ��ѯ��������Ϣ
			GuestInfo guest = DaoFactory.getIGuestDaoInstance().findObjectByID(order.getGuestID());

			/* ȡ��order��Ӧ���� */
			String inTime = String.valueOf(order.getInTime());
			inTime = inTime.substring(0, 19); // ȡ���ݿ��������յ�ʱ��
			String outTime = String.valueOf(order.getOutTime());
			outTime = outTime.substring(0, 19); // ȡ���ݿ��������յ�ʱ��
			String orderType = CommonMethod.orderTypeBoolToStr(order.isOrderType());
			// String orderState =
			// CommonMethod.orderStateBoolToStr(order.isOrderState());
			// BigDecimal orderMoney = order.getOrderMoney();

			String guestType = CommonMethod.guestTypeChange(guest.getGuestType());

			// ��order��guestƴ�ӳ�����Ҫ��JSON��ʽ����
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
		} else { // δ��ѯ����ض�����Ϣ
			String json = null;
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			// response.getWriter().write(json);
			json = "{success:false}";
			PrintWriter out = response.getWriter();
			out.print(json);
		}
	}

	// ���ض�����Ϣ
	private void LoadOrderInfro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		OrderInfo order = DaoFactory.getIOrderDaoInstance().getOrderInfoByroomID(roomID); // �˷�����ѯ������δ����Ķ�����Ϣ
		if (order.getOrderID() != 0) { // ��ѯ��������Ϣ
			GuestInfo guest = DaoFactory.getIGuestDaoInstance().findObjectByID(order.getGuestID());

			/* ȡ��order��Ӧ���� */
			String inTime = String.valueOf(order.getInTime());
			inTime = inTime.substring(0, 19); // ȡ���ݿ��������յ�ʱ��
			String outTime = String.valueOf(order.getOutTime());
			outTime = outTime.substring(0, 19); // ȡ���ݿ��������յ�ʱ��
			String orderType = CommonMethod.orderTypeBoolToStr(order.isOrderType());
			String orderState = CommonMethod.orderStateBoolToStr(order.isOrderState());
			BigDecimal orderMoney = order.getOrderMoney();

			// ȡ��guest��Ӧ����
			String timeString = String.valueOf(guest.getGuestBirthday());
			timeString = timeString.substring(0, 10); // ȡ���ݿ��������յ�ʱ��
			Boolean sex = guest.isGuestSex(); // �����ݿ��е��Ա�true��Ӧ������е��У�false��Ӧ������е�Ů
			String sexString = null;
			if (sex) {
				sexString = "��";
			} else {
				sexString = "Ů";
			}
			String guestType = CommonMethod.guestTypeChange(guest.getGuestType());

			// ��order��guestƴ�ӳ�����Ҫ��JSON��ʽ����
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
		} else { // δ��ѯ����ض�����Ϣ
			String json = null;
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			// response.getWriter().write(json);
			json = "{success:false}";
			PrintWriter out = response.getWriter();
			out.print(json);
		}
	}

	// ���Ӷ�����Ϣ
	private void SaveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		String inTime = request.getParameter("inTime");
		Timestamp outTimestamp = null;
		Timestamp inTimestamp = Timestamp.valueOf(inTime); // ת��Ϊ���ڸ�ʽ
		String outTime = request.getParameter("outTime");
		if (outTime.equals("") || outTime == null) {

		} else {
			outTimestamp = Timestamp.valueOf(outTime);
		}
		// String orderState = request.getParameter("orderState");
		String orderState = "δ����";// δ����orderState����
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
		/* ���ͻ���Ϣ����ͻ���Ϣ�������ظÿͻ�ID */
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
			boolean flag = DaoFactory.getIOrderDaoInstance().Save(order);// ���붩����Ϣ
			if (flag) {
				RoomInfo room = new RoomInfo();
				room = DaoFactory.getIRoomDaoInstance().findObjectByID(roomID);
				room.setRoomState("R");
				result = DaoFactory.getIRoomDaoInstance().Update(room);

			} else {
				System.out.println("�������ˣ��ͻ����Ѿ����������ݣ���ȴ��֪����ô�ع���");
			}
		} else {
			System.out.println("�������ˣ��ͻ�������Ѿ����������ݣ���ȴ��֪����ô�ع���");
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
