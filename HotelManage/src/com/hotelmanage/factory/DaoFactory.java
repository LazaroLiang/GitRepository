package com.hotelmanage.factory;

import com.hotelmanage.dao.IBalanceDao;
import com.hotelmanage.dao.IGuestDao;
import com.hotelmanage.dao.IOrderDao;
import com.hotelmanage.dao.IRoomDao;
import com.hotelmanage.dao.IUserDao;
import com.hotelmanage.dao.proxy.BalanceDaoProxy;
import com.hotelmanage.dao.proxy.GuestDaoProxy;
import com.hotelmanage.dao.proxy.OrderDaoProxy;
import com.hotelmanage.dao.proxy.RoomDaoProxy;
import com.hotelmanage.dao.proxy.UserDaoProxy;

public class DaoFactory {
	public static IUserDao getIUserDaoInstance() {
		return new UserDaoProxy();
	}

	public static IGuestDao getIGuestDaoInstance() {
		return new GuestDaoProxy();
	}

	public static IRoomDao getIRoomDaoInstance() {
		return new RoomDaoProxy();
	}

	public static IOrderDao getIOrderDaoInstance() {
		return new OrderDaoProxy();
	}

	public static IBalanceDao getIBalanceDaoInstance() {
		return new BalanceDaoProxy();
	}
}
