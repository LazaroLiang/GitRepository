package com.hotelmanage.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.hotelmanage.dao.IUserDao;
import com.hotelmanage.dao.impl.UserDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.vo.User;

//用户操作代理类
public class UserDaoProxy implements IUserDao {
	private ConnectionManager conn = null;
	private IUserDao dao = null;

	public UserDaoProxy() {
		try {
			this.conn = new ConnectionManager();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.dao = new UserDaoImpl(conn.GetConn());
	}

	public boolean Save(User entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.dao.Save(entity);
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

	public boolean Update(User entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.dao.Update(entity);
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

	public boolean Delete(User entity) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.dao.Delete(entity);
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

	public User findObjectByID(int id) {
		// TODO Auto-generated method stub
		User user = new User();
		try {
			user = this.dao.findObjectByID(id);
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
		return user;
	}

	public boolean checkLogin(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.dao.checkLogin(user);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.conn.close();
		}
		return flag;
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> userlist = new ArrayList<User>();
		try {
			userlist = this.dao.findAll();
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
		return userlist;
	}

	public int findIDByLoginName(String loginName) {
		// TODO Auto-generated method stub
		int id = -1;
		try {
			id = this.dao.findIDByLoginName(loginName);
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
		return id;

	}

	public boolean checkLoginName(int userID, String loginName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.dao.checkLoginName(userID, loginName);
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

	public List<User> findUserInfoBetween(int start, int end) {
		// TODO Auto-generated method stub
		List<User> userlist = new ArrayList<User>();
		try {
			userlist = this.dao.findUserInfoBetween(start, end);
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
		return userlist;
	}

	public int CountAllUser() {
		// TODO Auto-generated method stub
		int count = -1;
		try {
			count = this.dao.CountAllUser();
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
