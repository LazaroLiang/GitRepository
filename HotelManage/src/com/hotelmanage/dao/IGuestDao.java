package com.hotelmanage.dao;

import java.util.List;

import com.hotelmanage.vo.GuestInfo;

public interface IGuestDao extends ICommonDao<GuestInfo> {
	// ȡ�̶�start-end�е����ݣ���Ҫ����Ϊ��ҳ��
	public List<GuestInfo> findGuestInfoBetween(int start, int end);

	// �������й˿�����
	public int CountAllGuest();

	public int SaveAndRetID(GuestInfo guest);
}
