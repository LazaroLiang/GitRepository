package com.hotelmanage.dao;

import java.util.List;

import com.hotelmanage.vo.OrderInfo;

public interface IOrderDao extends ICommonDao<OrderInfo> {
	// 通过roomID查询订单信息（未结算）
	public OrderInfo getOrderInfoByroomID(int roomID);

	// 取固定start-end行的数据，主要是作为分页用
	public List<OrderInfo> findOrderInfoBetween(int start, int end);

	// 查找所有顾客总数
	public int CountAllOrder();
}
