package com.game.complaint.services;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Complaint;
import com.game.util.web.Page;

public interface ComplaintService extends GenericService<Complaint, Long> {

	public Complaint getComplaint(Long user_id, Long order_id) throws Exception;

	/**
	 * @name 根据ID删除一个投诉
	 */
	public void removeComplaint(Long id) throws Exception;

	/**
	 * @name 获得订单
	 * @param userID 投诉用户ID
	 * @param defendant 被投诉用户ID
	 * @param state 状态
	 * @param type 类型
	 * @param size 每页条数
	 * @param goPage 第几页
	 */
	public Page<Complaint> searchComplaint(Long userID, Long defendant, Integer state, Integer type, int size, int goPage) throws Exception;
}
