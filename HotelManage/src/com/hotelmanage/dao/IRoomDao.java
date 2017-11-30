package com.hotelmanage.dao;

import java.util.List;

import com.hotelmanage.vo.RoomInfo;

public interface IRoomDao extends ICommonDao<RoomInfo> {
	// ȡ�̶�start-end�е����ݣ���Ҫ����Ϊ��ҳ��
	public List<RoomInfo> findRoomInfoBetween(int start, int end);

	// �������й˿�����
	public int CountAllRoom();

}
