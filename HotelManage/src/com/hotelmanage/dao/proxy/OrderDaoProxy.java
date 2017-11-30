package com.hotelmanage.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IOrderDao;
import com.hotelmanage.dao.impl.OrderDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.vo.OrderInfo;

public class OrderDaoProxy implements IOrderDao {
	private ConnectionManager conn = null;
	private IOrderDao orderDao = null;

	public OrderDaoProxy() {
		try {
			this.conn = new ConnectionManager();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.orderDao = new OrderDaoImpl(this.conn.GetConn());
	}

	public boolean Save(OrderInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.orderDao.Save(entity);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				this.conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean Update(OrderInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.orderDao.Update(entity);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				this.conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean Delete(OrderInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.orderDao.Delete(entity);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				this.conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public OrderInfo findObjectByID(int id) {
		// TODO Auto-generated method stub
		OrderInfo order = new OrderInfo();
		try {
			order = this.orderDao.findObjectByID(id);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				this.conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return order;
	}

	public List<OrderInfo> findAll() {
		// TODO Auto-generated method stub
		List<OrderInfo> orderlist = new ArrayList<OrderInfo>();
		try {
			orderlist = this.orderDao.findAll();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				this.conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orderlist;
	}

	public OrderInfo getOrderInfoByroomID(int roomID) {
		// TODO Auto-generated method stub
		OrderInfo order = new OrderInfo();
		try {
			order = this.orderDao.getOrderInfoByroomID(roomID);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				this.conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return order;
	}

	public List<OrderInfo> findOrderInfoBetween(int start, int end) {
		// TODO Auto-generated method stub
		List<OrderInfo> orderlist = new ArrayList<OrderInfo>();
		try {
			orderlist = this.orderDao.findOrderInfoBetween(start, end);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				this.conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orderlist;
	}

	public int CountAllOrder() {
		int count = -1;
		try {
			count = this.orderDao.CountAllOrder();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				this.conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

}
