package com.hotelmanage.dao;

import java.util.List;

import com.hotelmanage.vo.BalanceInfo;

public interface IBalanceDao extends ICommonDao<BalanceInfo> {
	// 取固定start-end行的数据，主要是作为分页用
	public List<BalanceInfo> findBalanceInfoBetween(int start, int end);

	// 查找所有顾客总数
	public int CountAllBalance();
}
