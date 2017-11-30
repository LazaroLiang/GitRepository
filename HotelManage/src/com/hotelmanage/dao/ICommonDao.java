package com.hotelmanage.dao;

import java.util.List;

public interface ICommonDao<T> {
	// 定义公共接口,数据库增删改查操作
	public boolean Save(T entity);

	public boolean Update(T entity);

	public boolean Delete(T entity);

	public T findObjectByID(int id);

	public List<T> findAll();
}
