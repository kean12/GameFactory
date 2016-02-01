package com.game.util.base.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.game.util.base.dao.BaseDAO;
import com.game.util.web.Page;

@SuppressWarnings("unchecked")
public class BaseDAOImpl<T, K extends Serializable> extends HibernateDaoSupport implements BaseDAO<T, K> {

	/**
	 * 加载实体
	 */
	public T getEntity(Class<T> clasz, K k) throws Exception {
		return (T) this.getHibernateTemplate().get(clasz, k);
	}

	/**
	 * 加载实体
	 */
	public T loadEntity(Class<T> clazz, K k) throws Exception {
		return (T) this.getHibernateTemplate().load(clazz, k);
	}

	/**
	 * 保存实体
	 */
	public T saveEntity(T t) throws Exception {
		this.getHibernateTemplate().save(t);
		return t;
	}

	/**
	 * 保存临时实体
	 */
	public T saveTempEntity(T t) throws Exception {
		this.getHibernateTemplate().save(this.prepObject(t));
		return t;
	}

	public T prepObject(T t) {
		return (T) this.getHibernateTemplate().merge(t);
	}

	/**
	 * 更新实体
	 */
	public T updateEntity(T t) throws Exception {
		getHibernateTemplate().update(t);
		return t;
	}

	/**
	 * 根据hql语句更新
	 */
	public void updateEntity(final String hql) throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery(hql).executeUpdate();
			}
		});
	}

	/**
	 * 添加或保存实体
	 */
	public T saveorupdate(T t) throws Exception {
		this.getHibernateTemplate().saveOrUpdate(t);
		return t;
	}

	/**
	 * 添加或保存实体集合
	 */
	public void saveorupdatecoll(Collection<T> co) throws Exception {
		this.getHibernateTemplate().saveOrUpdateAll(co);
	}

	/**
	 * 删除实体集合
	 */
	public void removeAllEntity(Collection<T> obj) throws Exception {
		this.getHibernateTemplate().deleteAll(obj);
	}

	/**
	 * 删除实体
	 */
	public void removeEntity(T t) throws Exception {
		this.getHibernateTemplate().delete(t);
	}

	/**
	 * 通过hql语句查找实体集合
	 */
	public List<T> findEntity(String hql) throws Exception {
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 通过hql语句查找实体集合
	 */
	public List<T> findEntity(String hql, Object obj) throws Exception {
		return this.getHibernateTemplate().find(hql, obj);
	}

	/**
	 * 通过hql语句查找实体集合
	 */
	public List<T> findEntity(String hql, Object[] obj) throws Exception {
		return this.getHibernateTemplate().find(hql, obj);
	}

	/**
	 * 根据hql查询 返回Object
	 */
	public List<Object> findList(String hql) throws Exception {
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 根据hql查询 返回Object
	 */
	public List<Object> findList(String hql, Object obj) throws Exception {
		return this.getHibernateTemplate().find(hql, obj);
	}
	
	/**
	 * 根据hql查询 返回Object
	 */
	public List<Object> findList(String hql, Object[] obj) throws Exception {
		return this.getHibernateTemplate().find(hql, obj);
	}

	/**
	 * 根据hql查询 返回Object数组
	 */
	public List<Object[]> findListArray(String hql) throws Exception {
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 根据hql查询 返回Object数组
	 */
	public List<Object[]> findListArray(final String hql, final int start, final int num) throws Exception {
		return (List<Object[]>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						return session.createQuery(hql).setFirstResult(start).setMaxResults(num).list();
					}
				});
	}

	// /**
	// * 根据hql查询 返回Object数组
	// */
	// public List<Object[]> findList(String hql, Object obj) throws Exception {
	// return this.getHibernateTemplate().find(hql, obj);
	// }
	//
	// /**
	// * 根据hql查询 返回Object数组
	// */
	// public List<Object[]> findList(String hql, Object[] obj) throws Exception
	// {
	// return this.getHibernateTemplate().find(hql, obj);
	// }
	//	
	/**
	 * 通过hql语句查找实体集合
	 */
	public List<T> findEntity(final String hql, final int start, final int num) throws Exception {
		return (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						return session.createQuery(hql).setFirstResult(start).setMaxResults(num).list();
					}
				});
	}

	/**
	 * 根据sql语句查询
	 */
	public List<T> findListBySql(final String sql) throws Exception {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						return session.createSQLQuery(sql).list();
					}
				});
	}

	/**
	 * 根据sql语句执行
	 */
	public void executeSQL(final String sql) throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			@SuppressWarnings("deprecation")
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.connection().createStatement().executeUpdate(sql);
			}
		});
	}

	/**
	 * 分页查询
	 */
	public Page<T> search(String hql, int size, int goPage) throws Exception {
		return this.searchForPager(hql, size, goPage);
	}

	/**
	 * 分页实现方法
	 * 
	 * @param hql
	 * @param pageLength 每页大小
	 * @param currentPage 当前页
	 * @return 分页对象
	 */
	private Page<T> searchForPager(String hql, int pageLength, int currentPage) throws Exception {
		int maxPage;
		List<T> results;
		List<Integer> pageNum = new ArrayList<Integer>();

		// sql数据库需要去除order by
		String thql = null;
		if (hql.indexOf("order by") == -1) {
			thql = "select count(*) " + hql;
		} else {
			thql = "select count(*) " + hql.substring(0, hql.indexOf("order by"));
		}

		int count = Integer.valueOf(this.findEntity(thql).get(0).toString());

		// 计算页数
		if (count % pageLength == 0) {
			maxPage = count / pageLength;
		} else {
			maxPage = count / pageLength + 1;
		}

		if (maxPage == 0) {
			maxPage = 1;
			currentPage = 1;
		} else {
			currentPage = currentPage <= maxPage ? (currentPage > 0 ? currentPage : 1) : maxPage;
		}

		// 页码
		if (currentPage < 6) {
			for (int i = 1; i < 10; i++) {
				if (i <= maxPage) {
					pageNum.add(i);
				} else {
					break;
				}
			}
		} else {
			for (int i = currentPage - 4; i <= currentPage + 4; i++) {
				if (i <= maxPage) {
					pageNum.add(i);
				} else {
					break;
				}
			}
		}
		results = this.findEntity(hql, (currentPage - 1) * pageLength, pageLength);
		Page<T> p = new Page<T>(currentPage, maxPage, pageNum, results);
		return p;
	}

}
