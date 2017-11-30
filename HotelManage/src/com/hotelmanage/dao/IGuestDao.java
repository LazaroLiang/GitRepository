package com.hotelmanage.dao;

import java.util.List;

import com.hotelmanage.vo.GuestInfo;

public interface IGuestDao extends ICommonDao<GuestInfo> {
	// 取固定start-end行的数据，主要是作为分页用
	public List<GuestInfo> findGuestInfoBetween(int start, int end);

	// 查找所有顾客总数
	public int CountAllGuest();

	public int SaveAndRetID(GuestInfo guest);
}
