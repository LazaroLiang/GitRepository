package com.hotelmanage.junit;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.hotelmanage.dao.IUserDao;
import com.hotelmanage.dao.impl.UserDaoImpl;
import com.hotelmanage.dbc.ConnectionManager;
import com.hotelmanage.vo.User;

public class TestUserDaoImpl {

	@Ignore()
	@Test
	public void testUserDaoImpl() {
		fail("Not yet implemented");
	}

	/* 测试增加用户 */
	// @Ignore("忽略测试增加用户")
	@Ignore("忽略增加用户测试")
	@Test
	public void testSave() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		User user = new User();
		user.setLoginName("332211");
		user.setPassword("112233");
		user.setUserSex(false);
		user.setUserTel("13687227069");
		user.setUserAddress("大学路8号");
		user.setUserName("测试addert");
		user.setUserAge(20);
		user.setUserType(1);
		boolean bl = ud.Save(user);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	/* 修改用户信息 */
	@Ignore("忽略修改用户信息测试")
	@Test
	public void testUpdate() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		User user = new User();
		user = ud.findObjectByID(9);
		System.out.println(user.getPassword());
		user.setPassword("123123");
		boolean bl = ud.Update(user);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	/* 测试删除用户 */
	@Ignore("忽略删除用户测试")
	@Test
	public void testDelete() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		User user = new User();
		user.setUserID(9);
		boolean bl = ud.Delete(user);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	/* 测试通过ID查找用户信息 */
	@Ignore("忽略通过ID查找用户测试")
	@Test
	public void testFindObjectByID() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		User user = new User();
		user = ud.findObjectByID(1);
		System.out.println(user);
		// fail("Not yet implemented");
	}

	/* 测试登陆验证 */
	@Ignore("忽略登陆验证测试")
	@Test
	public void testCheckLogin() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		User user = new User();
		user.setLoginName("admin");
		user.setPassword("admin");
		try {
			boolean bl = ud.checkLogin(user);
			System.out.println(bl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("出错了");
		}
		fail("Not yet implemented");
	}

	@Ignore("忽略")
	@Test
	public void testFindAll() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		List<User> list = new ArrayList<User>();
		list = ud.findAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getUserID() + " " + list.get(i).getUserName());

		}
	}

	@Ignore("忽略")
	@Test
	public void testFindIDByLoginName() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		System.out.println(ud.findIDByLoginName("12"));
	}

	@Ignore("忽略")
	@Test
	public void testCheckLoginName() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		String loginName = "admin";
		int userID = 3;
		try {
			boolean bl = ud.checkLoginName(userID, loginName);
			System.out.println(bl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("出错了");
		}
	}

	@Ignore("忽略")
	@Test
	public void testFindUserInfoBetween() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		List<User> list = new ArrayList<User>();
		list = ud.findUserInfoBetween(2, 4);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void testCountAllGuest() {
		ConnectionManager cn = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cn.GetConn());
		int list;
		list = ud.CountAllUser();
		System.out.println(list);
	}
}
