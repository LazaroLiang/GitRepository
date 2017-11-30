package com.hotelmanage.dao;

import java.util.List;

import com.hotelmanage.vo.OrderInfo;

public interface IOrderDao extends ICommonDao<OrderInfo> {
	// ͨ��roomID��ѯ������Ϣ��δ���㣩
	public OrderInfo getOrderInfoByroomID(int roomID);

	// ȡ�̶�start-end�е����ݣ���Ҫ����Ϊ��ҳ��
	public List<OrderInfo> findOrderInfoBetween(int start, int end);

	// �������й˿�����
	public int CountAllOrder();
}
