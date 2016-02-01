package com.game.util.base.dao.impl;

import java.io.Serializable;
import java.util.Collection;

import com.game.util.base.dao.BaseDAO;
import com.game.util.base.dao.GenericService;

/**
 * 公用方法实现层
 */
public class GenericServiceImpl<T, K extends Serializable> implements GenericService<T, K> {
	protected BaseDAO<T, K> baseDAO;

	/**
	 * 根据clazz，k 获得实体
	 */
	public T getEntity(Class<T> clazz, K k) throws Exception {
		return baseDAO.getEntity(clazz, k);
	}

	/**
	 * 根据clazz，k 获得实体
	 */
	public T loadEntity(Class<T> clazz, K k) throws Exception {
		return baseDAO.loadEntity(clazz, k);
	}

	/**
	 * 删除集合实体
	 */
	public void removeAllEntity(Collection<T> colls) throws Exception {
		baseDAO.removeAllEntity(colls);
	}

	/**
	 * 删除一个实体
	 */
	public void removeEntity(T t) throws Exception {
		baseDAO.removeEntity(t);
	}

	/**
	 * 保存一个实体
	 */
	public T createEntity(T t) throws Exception {
		return baseDAO.saveEntity(t);
	}

	/**
	 * 保存或更新一个集合实体 判断每个实体的主键是否为空，为空则新增，反之更新
	 */
	public void saveorupdatecoll(Collection<T> co) throws Exception {
		baseDAO.saveorupdatecoll(co);
	}

	/**
	 * 保存或更新一个实体 判断实体的主键是否为空，为空则新增，反之更新
	 */
	public T saveorupdate(T t) throws Exception {
		return baseDAO.saveorupdate(t);
	}

	/**
	 * 更新一个实体
	 */
	public T updateEntity(T t) throws Exception {
		return baseDAO.updateEntity(t);
	}

	// ====================
	public void setBaseDAO(BaseDAO<T, K> baseDAO) {
		this.baseDAO = baseDAO;
	}

}