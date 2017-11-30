package com.hotelmanage.junit;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.hotelmanage.dao.IBalanceDao;
import com.hotelmanage.dao.impl.BalanceDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.vo.BalanceInfo;

/*
 * 2016-4-8创建 
 * 结算信息类增删改查测试类
 * author:liangzhuang
 * */
public class TestBalanceDaoImpl {

	@Ignore("忽略")
	@Test
	public void testSave() {
		ConnectionManager conn = new ConnectionManager();
		IBalanceDao balanceDao = new BalanceDaoImpl(conn.GetConn());
		BalanceInfo balanceInfo = new BalanceInfo();
		balanceInfo.setOrderID(3);
		balanceInfo.setGuestID(1);
		balanceInfo.setRoomID(1);
		balanceInfo.setRoomCost(new BigDecimal(150));
		balanceInfo.setEarnestMoney(new BigDecimal(200));
		balanceInfo.setExtraCost(new BigDecimal(0));
		balanceInfo.setFreeCost(new BigDecimal(20));
		balanceInfo.setTotalCost(new BigDecimal(150));
		balanceInfo.setDueCost(new BigDecimal(130));
		balanceInfo.setBalanceOperate(1);
		balanceInfo.setOperateName("梁壮");
		Timestamp balanceTime = Timestamp.valueOf("2016-04-08 14:49:36");
		balanceInfo.setBalanceTime(balanceTime);
		boolean bl = balanceDao.Save(balanceInfo);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("忽略")
	@Test
	public void testUpdate() {
		ConnectionManager conn = new ConnectionManager();
		IBalanceDao balanceDao = new BalanceDaoImpl(conn.GetConn());
		BalanceInfo balanceInfo = new BalanceInfo();
		balanceInfo.setBalanceID(1);
		balanceInfo.setOrderID(1);
		balanceInfo.setGuestID(1);
		balanceInfo.setRoomID(1);
		balanceInfo.setRoomCost(new BigDecimal(150));
		balanceInfo.setEarnestMoney(new BigDecimal(200));
		balanceInfo.setExtraCost(new BigDecimal(0));
		balanceInfo.setFreeCost(new BigDecimal(20));
		balanceInfo.setTotalCost(new BigDecimal(150));
		balanceInfo.setDueCost(new BigDecimal(130));
		balanceInfo.setBalanceOperate(3);
		balanceInfo.setOperateName("梁壮壮");
		Timestamp balanceTime = Timestamp.valueOf("2016-04-08 14:49:36");
		balanceInfo.setBalanceTime(balanceTime);
		boolean bl = balanceDao.Update(balanceInfo);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("忽略")
	@Test
	public void testDelete() {
		ConnectionManager conn = new ConnectionManager();
		IBalanceDao balanceDao = new BalanceDaoImpl(conn.GetConn());
		BalanceInfo balanceInfo = new BalanceInfo();
		balanceInfo.setBalanceID(2);
		boolean bl = balanceDao.Delete(balanceInfo);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	@Ignore("忽略")
	@Test
	public void testFindObjectByID() {
		ConnectionManager conn = new ConnectionManager();
		IBalanceDao balanceDao = new BalanceDaoImpl(conn.GetConn());
		BalanceInfo balanceInfo = new BalanceInfo();
		balanceInfo = balanceDao.findObjectByID(1);
		System.out.println(balanceInfo);
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		ConnectionManager conn = new ConnectionManager();
		IBalanceDao balanceDao = new BalanceDaoImpl(conn.GetConn());
		List<BalanceInfo> list = new ArrayList<BalanceInfo>();
		list = balanceDao.findAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		fail("Not yet implemented");
	}

}
