package com.game.util.base.dao;

import java.io.Serializable;
import java.util.Collection;

/**
 * 公用方法
 */
public interface GenericService<T, K extends Serializable> {
	/**
	 * 根据clazz，k 获得实体
	 */
	public T getEntity(Class<T> clazz, K k) throws Exception;

	/**
	 * 根据clazz，k 获得实体
	 */
	public T loadEntity(Class<T> clazz, K k) throws Exception;

	/**
	 * 删除集合实体
	 */
	public void removeAllEntity(Collection<T> colls) throws Exception;

	/**
	 * 删除一个实体
	 */
	public void removeEntity(T t) throws Exception;

	/**
	 * 保存一个实体
	 */
	public T createEntity(T t) throws Exception;

	/**
	 * 保存或更新一个集合实体 判断每个实体的主键是否为空，为空则新增，反之更新
	 */
	public void saveorupdatecoll(Collection<T> co) throws Exception;

	/**
	 * 保存或更新一个实体 判断实体的主键是否为空，为空则新增，反之更新
	 */
	public T saveorupdate(T t) throws Exception;

	/**
	 * 更新一个实体
	 */
	public T updateEntity(T t) throws Exception;

}
