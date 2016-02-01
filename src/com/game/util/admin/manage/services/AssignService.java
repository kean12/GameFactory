package com.game.util.admin.manage.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Assign;
import com.game.util.web.Page;

public interface AssignService extends GenericService<Assign, Long> {

	public Assign getAssignByOrderNum(String orderNum, Long manageID, Integer state) throws Exception;

	/**
	 * @name 根据分配交易员查询出该交易员的单子
	 */
	public Page<Assign> findAssignByManage(Long manageID, Integer state, int size, int goPage) throws Exception;

	/**
	 * @name 根据分配交易员查询出该交易员的退款单子
	 */
	public Page<Assign> findAssignByRefundment(Long manageID, Integer orderState, int size, int goPage) throws Exception;

	/**
	 * @name 根据hql查询
	 */
	public List<Assign> findAssignByHql(String hql) throws Exception;

	/**
	 * @name 超时订单列表
	 */
	public Page<Assign> findAssignByOvertime(Long manageID, String orderNum, int size, int goPage) throws Exception;

	public Assign getAssignByOvertime(String orderNum, Long manageID, Integer overtime) throws Exception;

}
