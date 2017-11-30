package com.hotelmanage.junit;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Ignore;
import org.junit.Test;

import com.hotelmanage.CommonMethod;
import com.hotelmanage.dao.IOrderDao;
import com.hotelmanage.dao.impl.OrderDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.GuestInfo;
import com.hotelmanage.vo.OrderInfo;

public class TestOrderDaoImpl {

	@Ignore("����")
	@Test
	public void testSave() {

		ConnectionManager cn = new ConnectionManager();
		IOrderDao orderDao = new OrderDaoImpl(cn.GetConn());
		OrderInfo oi = new OrderInfo();
		oi.setGuestID(2);
		oi.setGuestName("�Ǻ�");
		oi.setRoomID(5);
		Timestamp inTime = Timestamp.valueOf("2016-04-07 16:46:50.000");
		Timestamp outTime = Timestamp.valueOf("2016-04-07 18:46:50.000");
		oi.setInTime(inTime);
		oi.setOutTime(outTime);
		oi.setOrderState(false);
		oi.setOrderType(true);
		oi.setOrderMoney(new BigDecimal(100));
		boolean bl = orderDao.Save(oi);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("����")
	@Test
	public void testUpdate() {
		ConnectionManager cn = new ConnectionManager();
		IOrderDao orderDao = new OrderDaoImpl(cn.GetConn());
		OrderInfo oi = new OrderInfo();
		oi = orderDao.findObjectByID(5);
		oi.setOrderState(true);
		oi.setOrderType(true);
		oi.setOrderMoney(new BigDecimal(120));
		boolean bl = orderDao.Update(oi);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("����")
	@Test
	public void testDelete() {
		ConnectionManager cn = new ConnectionManager();
		IOrderDao orderDao = new OrderDaoImpl(cn.GetConn());
		OrderInfo oi = new OrderInfo();
		oi.setOrderID(10);
		boolean bl = orderDao.Delete(oi);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("����")
	@Test
	public void testFindObjectByID() {
		ConnectionManager cn = new ConnectionManager();
		IOrderDao orderDao = new OrderDaoImpl(cn.GetConn());
		OrderInfo oi = new OrderInfo();
		oi = orderDao.findObjectByID(5);
		System.out.println(oi);
		fail("Not yet implemented");
	}

	@Ignore("����")
	@Test
	public void testFindAll() {
		ConnectionManager cn = new ConnectionManager();
		IOrderDao orderDao = new OrderDaoImpl(cn.GetConn());
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		list = orderDao.findAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		fail("Not yet implemented");
	}

	// @Ignore("����")
	@Test
	public void getOrderInfoByroomID() {
		ConnectionManager cn = new ConnectionManager();
		IOrderDao orderDao = new OrderDaoImpl(cn.GetConn());
		OrderInfo oi = new OrderInfo();
		oi = orderDao.getOrderInfoByroomID(3);
		if (oi != null) {
			System.out.println(oi);
		}
		fail("Not yet implemented");
	}

	@Test
	public void testLoadOrderInfo() {
		OrderInfo order = DaoFactory.getIOrderDaoInstance().getOrderInfoByroomID(8);
		GuestInfo guest = DaoFactory.getIGuestDaoInstance().findObjectByID(order.getGuestID());
		// System.out.println("id:" + roomID);
		JSONArray jsonItems = new JSONArray();
		JSONObject orderJson = JSONObject.fromObject(order);
		String inTime = String.valueOf(order.getInTime());
		inTime = inTime.substring(0, 19); // ȡ���ݿ��������յ�ʱ��
		String outTime = String.valueOf(order.getOutTime());
		inTime = outTime.substring(0, 19); // ȡ���ݿ��������յ�ʱ��
		String orderType = CommonMethod.orderTypeBoolToStr(order.isOrderType());
		String orderState = CommonMethod.orderStateBoolToStr(order.isOrderState());
		orderJson.remove("inTime");
		orderJson.put("inTime", inTime);
		orderJson.remove("outTime");
		orderJson.put("outTime", outTime);
		orderJson.remove("orderType");
		orderJson.put("orderType", orderType);
		orderJson.remove("orderState");
		orderJson.put("orderState", orderState);
		// System.out.println(orderJson);
		// JSONObject guestJson = JSONObject.fromObject(guest);

		String timeString = String.valueOf(guest.getGuestBirthday());
		timeString = timeString.substring(0, 10); // ȡ���ݿ��������յ�ʱ��
		Boolean sex = guest.isGuestSex(); // �����ݿ��е��Ա�true��Ӧ������е��У�false��Ӧ������е�Ů
		String sexString = null;
		if (sex) {
			sexString = "��";
		} else {
			sexString = "Ů";
		}
		// �����ݿ��д洢�Ĺ˿����ͽ��ж�Ӧ
		String guestType = CommonMethod.guestTypeChange(guest.getGuestType());
		JSONObject object = JSONObject.fromObject(guest);
		object.remove("guestBirthday");
		object.put("guestBirthday", timeString);
		object.remove("guestSex");
		object.put("guestSex", sexString);
		object.remove("guestType");
		object.put("guestType", guestType);

		jsonItems.add(orderJson);
		jsonItems.add(object);
		JSONObject json = new JSONObject();
		json.put("data", jsonItems);
		System.out.println(json);

	}
}
