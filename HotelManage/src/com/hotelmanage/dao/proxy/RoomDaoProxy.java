package com.hotelmanage.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IRoomDao;
import com.hotelmanage.dao.impl.RoomDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.vo.RoomInfo;

public class RoomDaoProxy implements IRoomDao {
	private ConnectionManager conn = null;
	private IRoomDao roomDao = null;

	public RoomDaoProxy() {
		try {
			this.conn = new ConnectionManager();
		} catch (Exception e) {
			// TODO: handle exception
		}
		roomDao = new RoomDaoImpl(this.conn.GetConn());
	}

	public boolean Save(RoomInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.roomDao.Save(entity);
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

	public boolean Update(RoomInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.roomDao.Update(entity);
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

	public boolean Delete(RoomInfo entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.roomDao.Delete(entity);
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

	public RoomInfo findObjectByID(int id) {
		// TODO Auto-generated method stub
		RoomInfo room = new RoomInfo();
		try {
			room = this.roomDao.findObjectByID(id);
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
		return room;
	}

	public List<RoomInfo> findAll() {
		// TODO Auto-generated method stub
		List<RoomInfo> roomlist = new ArrayList<RoomInfo>();
		try {
			roomlist = this.roomDao.findAll();
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
		return roomlist;
	}

	public List<RoomInfo> findRoomInfoBetween(int start, int end) {
		// TODO Auto-generated method stub
		List<RoomInfo> roomlist = new ArrayList<RoomInfo>();
		try {
			roomlist = this.roomDao.findRoomInfoBetween(start, end);
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
		return roomlist;
	}

	public int CountAllRoom() {
		// TODO Auto-generated method stub
		int count = -1;
		try {
			count = this.roomDao.CountAllRoom();
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
