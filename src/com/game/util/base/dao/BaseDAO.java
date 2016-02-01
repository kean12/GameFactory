package com.game.util.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.game.util.web.Page;

public interface BaseDAO<T, K extends Serializable> extends DAO {

	/**
	 * 加载实体
	 */
	public T loadEntity(Class<T> clasz, K id) throws Exception;

	/**
	 * 加载实体
	 */
	public T getEntity(Class<T> clasz, K id) throws Exception;

	/**
	 * 删除实体
	 */
	public void removeEntity(T obj) throws Exception;

	/**
	 * 删除实体集合
	 */
	public void removeAllEntity(Collection<T> obj) throws Exception;

	/**
	 * 保存实体
	 */
	public T saveEntity(T t) throws Exception;

	/**
	 * 保存临时实体
	 */
	public T saveTempEntity(T t) throws Exception;

	/**
	 * 更新实体
	 */
	public T updateEntity(T t) throws Exception;

	/**
	 * 根据hql语句更新
	 */
	public void updateEntity(String hql) throws Exception;

	/**
	 * 添加或保存实体
	 */
	public T saveorupdate(T obj) throws Exception;

	/**
	 * 添加或保存实体集合
	 */
	public void saveorupdatecoll(Collection<T> co) throws Exception;

	/**
	 * 通过hql语句查找实体集合
	 */
	public List<T> findEntity(String hql, Object[] obj) throws Exception;

	/**
	 * 通过hql语句查找实体集合
	 */
	public List<T> findEntity(String hql) throws Exception;

	/**
	 * 通过hql语句查找实体集合
	 */
	public List<T> findEntity(String hql, Object obj) throws Exception;

	/**
	 * 根据hql查询 返回Object集合
	 */
	public List<Object> findList(String hql) throws Exception;

	/**
	 * 根据hql查询 返回Object集合
	 */
	public List<Object> findList(String hql, Object obj) throws Exception;
	
	/**
	 * 根据hql查询 返回Object
	 */
	public List<Object> findList(String hql, Object[] obj) throws Exception;

	/**
	 * 根据hql查询 返回Object数组
	 */
	public List<Object[]> findListArray(String hql) throws Exception;

	/**
	 * 根据hql查询 返回Object数组
	 */
	public List<Object[]> findListArray(String hql, int start, int num)
			throws Exception;

	/**
	 * 通过hql语句查找实体集合
	 */
	public List<T> findEntity(String hql, int start, int num) throws Exception;

	/**
	 * 根据sql语句执行
	 */
	public void executeSQL(String sql) throws Exception;

	/**
	 * 根据sql语句查询
	 */
	public List<T> findListBySql(final String sql) throws Exception;

	/**
	 * 分页查询
	 */
	public Page<T> search(String hql, int size, int goPage) throws Exception;

}