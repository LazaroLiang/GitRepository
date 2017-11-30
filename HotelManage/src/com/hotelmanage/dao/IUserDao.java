package com.hotelmanage.dao;

import java.util.List;

import com.hotelmanage.vo.User;

public interface IUserDao extends ICommonDao<User> {
	// ��½ʱ��֤�û���������
	public boolean checkLogin(User user) throws Exception;

	// ͨ����¼������û�ID
	public int findIDByLoginName(String loginName);

	// ����û����Ƿ�ռ��
	public boolean checkLoginName(int userID, String loginName);

	// ȡ�̶�start-end�е����ݣ���Ҫ����Ϊ��ҳ��
	public List<User> findUserInfoBetween(int start, int end);

	// �������й˿�����
	public int CountAllUser();
}
