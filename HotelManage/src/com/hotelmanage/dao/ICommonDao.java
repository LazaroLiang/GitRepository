package com.hotelmanage.dao;

import java.util.List;

public interface ICommonDao<T> {
	// ���幫���ӿ�,���ݿ���ɾ�Ĳ����
	public boolean Save(T entity);

	public boolean Update(T entity);

	public boolean Delete(T entity);

	public T findObjectByID(int id);

	public List<T> findAll();
}
