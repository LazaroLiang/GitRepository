package com.hotelmanage.junit;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.hotelmanage.dao.IRoomDao;
import com.hotelmanage.dao.impl.RoomDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.vo.RoomInfo;

public class TestRoomDaoImpl {

	@Ignore("忽略增加房间信息测试")
	@Test
	public void testSave() {
		ConnectionManager cn = new ConnectionManager();
		IRoomDao roomDao = new RoomDaoImpl(cn.GetConn());
		RoomInfo ri = new RoomInfo();
		ri.setRoomName("D003");
		ri.setRoomType("D");
		ri.setRoomState("F");
		ri.setRoomPPrice(new BigDecimal(128));
		ri.setRoomHPrice(new BigDecimal(118));
		ri.setRoomVPrice(new BigDecimal(108));
		ri.setRoomHourPrice(new BigDecimal(25));
		boolean bl = roomDao.Save(ri);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("忽略更新房间信息测试")
	@Test
	public void testUpdate() {
		ConnectionManager cn = new ConnectionManager();
		IRoomDao roomDao = new RoomDaoImpl(cn.GetConn());
		RoomInfo ri = new RoomInfo();
		ri.setRoomID(9);
		ri.setRoomName("B003");
		ri.setRoomType("B");
		ri.setRoomState("L");
		ri.setRoomPPrice(new BigDecimal(128));
		ri.setRoomHPrice(new BigDecimal(118));
		ri.setRoomVPrice(new BigDecimal(108));
		ri.setRoomHourPrice(new BigDecimal(25));
		boolean bl = roomDao.Update(ri);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("忽略删除房间信息测试")
	@Test
	public void testDelete() {
		ConnectionManager cn = new ConnectionManager();
		IRoomDao roomDao = new RoomDaoImpl(cn.GetConn());
		RoomInfo ri = new RoomInfo();
		ri.setRoomID(9);
		boolean bl = roomDao.Delete(ri);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("忽略通过ID查找房间信息测试")
	@Test
	public void testFindObjectByID() {
		ConnectionManager cn = new ConnectionManager();
		IRoomDao roomDao = new RoomDaoImpl(cn.GetConn());
		RoomInfo ri = new RoomInfo();
		ri = roomDao.findObjectByID(8);
		System.out.println(ri.getRoomHourPrice());
		fail("Not yet implemented");
	}

	@Ignore()
	@Test
	public void testFindAll() {
		ConnectionManager cn = new ConnectionManager();
		IRoomDao roomDao = new RoomDaoImpl(cn.GetConn());
		List<RoomInfo> list = new ArrayList<RoomInfo>();
		list = roomDao.findAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getRoomID() + " " + list.get(i).getRoomName() + " " + list.get(i).getRoomPPrice());
		}
		fail("Not yet implemented");
	}

	@Ignore()
	@Test
	public void testFindRoomInfoBetween() {
		ConnectionManager cn = new ConnectionManager();
		IRoomDao roomDao = new RoomDaoImpl(cn.GetConn());
		List<RoomInfo> list = new ArrayList<RoomInfo>();
		list = roomDao.findRoomInfoBetween(1, 3);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getRoomID() + " " + list.get(i).getRoomName() + " " + list.get(i).getRoomPPrice());
		}
	}

}
