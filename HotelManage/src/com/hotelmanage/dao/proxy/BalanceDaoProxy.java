package com.hotelmanage.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IBalanceDao;
import com.hotelmanage.dao.impl.BalanceDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.vo.BalanceInfo;

public class BalanceDaoProxy implements IBalanceDao {
	private ConnectionManager conn = null;
	private IBalanceDao balanceDao = null;

	public BalanceDaoProxy() {
		try {
			this.conn = new ConnectionManager();
		} catch (Exception e) {
			// TODO: handle exception
		}
		balanceDao = new BalanceDaoImpl(this.conn.GetConn());
	}

	public boolean Save(BalanceInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.balanceDao.Save(entity);
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

	public boolean Update(BalanceInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.balanceDao.Update(entity);
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

	public boolean Delete(BalanceInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.balanceDao.Delete(entity);
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

	public BalanceInfo findObjectByID(int id) {
		// TODO Auto-generated method stub
		BalanceInfo balance = new BalanceInfo();
		try {
			balance = this.balanceDao.findObjectByID(id);
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
		return balance;
	}

	public List<BalanceInfo> findAll() {
		// TODO Auto-generated method stub
		List<BalanceInfo> balancelist = new ArrayList<BalanceInfo>();
		try {
			balancelist = this.balanceDao.findAll();
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
		return balancelist;
	}

	public List<BalanceInfo> findBalanceInfoBetween(int start, int end) {
		// TODO Auto-generated method stub
		List<BalanceInfo> balancelist = new ArrayList<BalanceInfo>();
		try {
			balancelist = this.balanceDao.findBalanceInfoBetween(start, end);
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
		return balancelist;
	}

	public int CountAllBalance() {
		// TODO Auto-generated method stub
		int count = -1;
		try {
			count = this.balanceDao.CountAllBalance();
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
