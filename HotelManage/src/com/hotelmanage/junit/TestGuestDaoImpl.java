package com.hotelmanage.junit;

import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.junit.Ignore;
import org.junit.Test;

import com.hotelmanage.dao.IGuestDao;
import com.hotelmanage.dao.impl.GuestDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.factory.DaoFactory;
import com.hotelmanage.vo.GuestInfo;

public class TestGuestDaoImpl {

	@Ignore("忽略增加客户测试")
	@Test
	public void testSave() {

		ConnectionManager cn = new ConnectionManager();
		IGuestDao guestDao = new GuestDaoImpl(cn.GetConn());
		GuestInfo gi = new GuestInfo();
		gi.setGuestName("小黄");
		gi.setGuestSex(true);
		gi.setGuestCardID("123456789012345678");
		gi.setGuestTel("12345678901");
		gi.setGuestZip("654321");
		gi.setGuestAge(20);
		gi.setGuestAdress("宜昌三峡大学");
		Timestamp sqlDate = Timestamp.valueOf("2010-08-20 12:00:00");
		gi.setGuestBirthday(sqlDate);
		gi.setGuestType("H");
		boolean bl = guestDao.Save(gi);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("忽略更新客户测试")
	@Test
	public void testUpdate() {

		ConnectionManager cn = new ConnectionManager();
		IGuestDao guestDao = new GuestDaoImpl(cn.GetConn());
		GuestInfo gi = new GuestInfo();
		gi.setGuestID(2);
		gi.setGuestName("小黄");
		gi.setGuestSex(true);
		gi.setGuestCardID("123456789012345678");
		gi.setGuestTel("12345678901");
		gi.setGuestZip("654321");
		gi.setGuestAge(20);
		gi.setGuestAdress("宜昌三峡大学");
		Timestamp sqlDate = Timestamp.valueOf("2015-08-20 12:00:00");
		gi.setGuestBirthday(sqlDate);
		gi.setGuestType("H");
		Boolean bl = guestDao.Update(gi);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("忽略删除客户测试")
	@Test
	public void testDelete() {
		GuestInfo gi = new GuestInfo();
		gi.setGuestID(6);
		ConnectionManager cn = new ConnectionManager();
		IGuestDao guestDao = new GuestDaoImpl(cn.GetConn());
		boolean bl = guestDao.Delete(gi);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("忽略通过ID查找客户测试")
	@Test
	public void testFindObjectByID() {
		GuestInfo gi = new GuestInfo();
		ConnectionManager cn = new ConnectionManager();
		IGuestDao guestDao = new GuestDaoImpl(cn.GetConn());
		gi = guestDao.findObjectByID(1);
		System.out.println(gi.getGuestName());
		fail("Not yet implemented");
	}

//	@Ignore("忽略通过ID查找客户测试")
	@Test
	public void testFindAll() {
		// ConnectionManager cn = new ConnectionManager();
		// IGuestDao guestDao = new GuestDaoImpl(cn.GetConn());
		List<GuestInfo> list = new ArrayList<GuestInfo>();
		// list = guestDao.findAll();
		// 采用工厂方法模式
		list = new DaoFactory().getIGuestDaoInstance().findAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getGuestName() + " " + list.get(i).getGuestAge());
		}
	}

//	@Ignore()
	@Test
	public void testFindGuestInfoBetween() {
		ConnectionManager cn = new ConnectionManager();
		IGuestDao guestDao = new GuestDaoImpl(cn.GetConn());
		List<GuestInfo> list = new ArrayList<GuestInfo>();
		list = guestDao.findGuestInfoBetween(2, 4);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getGuestID() + " " + list.get(i).getGuestName() + " " + list.get(i).getGuestAge());
		}
	}

	@Ignore()
	@Test
	public void testCountAllGuest() {
		ConnectionManager cn = new ConnectionManager();
		IGuestDao guestDao = new GuestDaoImpl(cn.GetConn());
		int list;
		list = guestDao.CountAllGuest();
		System.out.println(list);
	}

	@Ignore()
	@Test
	public void testJSONDate() {

		ConnectionManager cn = new ConnectionManager();
		IGuestDao guestDao = new GuestDaoImpl(cn.GetConn());
		GuestInfo gi = new GuestInfo();
		gi.setGuestName("小黄");
		gi.setGuestSex(true);
		gi.setGuestCardID("123456789012345678");
		gi.setGuestTel("12345678901");
		gi.setGuestZip("654321");
		gi.setGuestAge(20);
		gi.setGuestAdress("宜昌三峡大学");
		Timestamp sqlDate = Timestamp.valueOf("2010-08-20 12:00:00");// 12:00:00
		gi.setGuestBirthday(sqlDate);
		gi.setGuestType("H");
		JSONObject object = JSONObject.fromObject(gi);
		String timeString = String.valueOf(gi.getGuestBirthday());
		timeString = timeString.substring(0, 10);
		System.out.println(timeString);
		object.remove("guestBirthday");
		object.put("guestBirthday", timeString);
		System.out.println(object);
		// boolean bl = guestDao.Save(gi);
		// System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore()
	@Test
	public void testSaveAndRetID() {
		ConnectionManager cn = new ConnectionManager();
		IGuestDao guestDao = new GuestDaoImpl(cn.GetConn());
		GuestInfo gi = new GuestInfo();
		gi.setGuestName("小黄");
		gi.setGuestSex(true);
		gi.setGuestCardID("123456789012345678");
		gi.setGuestTel("12345678901");
		gi.setGuestZip("654321");
		gi.setGuestAge(20);
		gi.setGuestAdress("宜昌");
		Timestamp sqlDate = Timestamp.valueOf("2010-08-20 12:00:00");
		gi.setGuestBirthday(sqlDate);
		gi.setGuestType("H");
		// boolean bl = guestDao.Save(gi);
		int bl = guestDao.SaveAndRetID(gi);
		System.out.println(bl);
	}
}
