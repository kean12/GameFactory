package com.game.assess.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Assess;
import com.game.util.domain.CreditCount;
import com.game.util.web.Page;

public interface AssessService extends GenericService<Assess, Long> {

	public List<Assess> findAssessByOrder(Long orderID) throws Exception;

	public Page<Assess> findAssess(Long userID, Integer type, Integer grade, Integer isSeller, int size, int goPage) throws Exception;

	public List<Object[]> getAssessByPassive(Long userID, Integer type, Integer span) throws Exception;
	
	/**
	 * 统计此用户的评论状况
	 * @param userID 所查询的用户ID
	 */
	public CreditCount findCountByTime(Long userID) throws Exception;
}
