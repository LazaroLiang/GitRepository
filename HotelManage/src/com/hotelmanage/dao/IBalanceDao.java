package com.hotelmanage.dao;

import java.util.List;

import com.hotelmanage.vo.BalanceInfo;

public interface IBalanceDao extends ICommonDao<BalanceInfo> {
	// ȡ�̶�start-end�е����ݣ���Ҫ����Ϊ��ҳ��
	public List<BalanceInfo> findBalanceInfoBetween(int start, int end);

	// �������й˿�����
	public int CountAllBalance();
}
