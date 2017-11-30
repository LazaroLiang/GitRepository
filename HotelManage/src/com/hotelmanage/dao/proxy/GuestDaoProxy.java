package com.hotelmanage.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IGuestDao;
import com.hotelmanage.dao.impl.GuestDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.vo.GuestInfo;

public class GuestDaoProxy implements IGuestDao {
	private ConnectionManager conn = null;
	private IGuestDao guestDao = null;

	public GuestDaoProxy() {
		try {
			this.conn = new ConnectionManager();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.guestDao = new GuestDaoImpl(conn.GetConn());
	}

	public boolean Save(GuestInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.guestDao.Save(entity);
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

	public boolean Update(GuestInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.guestDao.Update(entity);
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

	public boolean Delete(GuestInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.guestDao.Delete(entity);
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

	public GuestInfo findObjectByID(int id) {
		// TODO Auto-generated method stub
		GuestInfo guest = new GuestInfo();
		try {
			guest = this.guestDao.findObjectByID(id);
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
		return guest;
	}

	public List<GuestInfo> findAll() {
		// TODO Auto-generated method stub
		List<GuestInfo> guestlist = new ArrayList<GuestInfo>();
		try {
			guestlist = this.guestDao.findAll();
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
		return guestlist;
	}

	public List<GuestInfo> findGuestInfoBetween(int start, int end) {
		// TODO Auto-generated method stub
		List<GuestInfo> guestlist = new ArrayList<GuestInfo>();
		try {
			guestlist = this.guestDao.findGuestInfoBetween(start, end);
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
		return guestlist;
	}

	public int CountAllGuest() {
		// TODO Auto-generated method stub
		int count = -1;
		try {
			count = this.guestDao.CountAllGuest();
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

	public int SaveAndRetID(GuestInfo guest) {
		// TODO Auto-generated method stub
		int count = -1;
		try {
			count = this.guestDao.SaveAndRetID(guest);
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
