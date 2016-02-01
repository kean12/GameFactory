package com.game.util.admin.credit.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Credit;

public interface CreditService extends GenericService<Credit, Long> {

	/**
	 * @name 根据ID删除积分实体
	 */
	public void removeCredit(Long id) throws Exception;

	/**
	 * @name 查询所有积分设置
	 */
	public List<Credit> findCredit(Integer type, Integer grade) throws Exception;

	/**
	 * @name 查询所有积分设置
	 */
	public Credit findCreditByGrade(Integer type, Integer grade) throws Exception;

	/**
	 * @name 查询出大于此等级的积分集合
	 */
	public List<Credit> findCreditGTGrade(Integer type, Integer grade) throws Exception;

	/**
	 * @name 设置最大等级的上限值
	 */
	public void updateCreditByMaxGrade(Integer maxCredit, Integer type) throws Exception;

}
