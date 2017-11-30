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

	/* ���������û� */
	// @Ignore("���Բ��������û�")
	@Ignore("���������û�����")
	@Test
	public void testSave() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		User user = new User();
		user.setLoginName("332211");
		user.setPassword("112233");
		user.setUserSex(false);
		user.setUserTel("13687227069");
		user.setUserAddress("��ѧ·8��");
		user.setUserName("����addert");
		user.setUserAge(20);
		user.setUserType(1);
		boolean bl = ud.Save(user);
		System.out.println(bl);
		fail("Not yet implemented");
	}

	/* �޸��û���Ϣ */
	@Ignore("�����޸��û���Ϣ����")
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

	/* ����ɾ���û� */
	@Ignore("����ɾ���û�����")
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

	/* ����ͨ��ID�����û���Ϣ */
	@Ignore("����ͨ��ID�����û�����")
	@Test
	public void testFindObjectByID() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		User user = new User();
		user = ud.findObjectByID(1);
		System.out.println(user);
		// fail("Not yet implemented");
	}

	/* ���Ե�½��֤ */
	@Ignore("���Ե�½��֤����")
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
			System.out.println("������");
		}
		fail("Not yet implemented");
	}

	@Ignore("����")
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

	@Ignore("����")
	@Test
	public void testFindIDByLoginName() {
		ConnectionManager cm = new ConnectionManager();
		IUserDao ud = new UserDaoImpl(cm.GetConn());
		System.out.println(ud.findIDByLoginName("12"));
	}

	@Ignore("����")
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
			System.out.println("������");
		}
	}

	@Ignore("����")
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
