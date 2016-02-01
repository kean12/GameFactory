package com.game.util.user.services;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Particulars;
import com.game.util.web.Page;

public interface ParticularsService extends GenericService<Particulars, Long> {
	/**
	 * @name 添加一个明细
	 */
	public Particulars createParticulars(Particulars particulars) throws Exception;

	/**
	 * @name 更新一个明细
	 */
	public void updateParticulars(Particulars particulars) throws Exception;

	/**
	 * @name 根据ID获得明细实体
	 */
	public Particulars getParticularsById(Long id) throws Exception;

	/**
	 * 根据用户ID查询该用户的明细查询
	 * @param userID 用户ID
	 * @param orderNum 商户订单号
	 * @param runningNum 业务流水号
	 * @param bank 交易场所(工商、游戏买卖网)
	 * @param type 交易类型 1.交易付款 2.交易收款 3.在线充值 4.退款 5.提现
	 * @param beginTime 交易时间1
	 * @param endTime 交易时间2
	 * @param size 每页显示条数
	 * @param goPage 第几页
	 */
	public Page<Particulars> findParticulars(Long userID, String orderNum, String runningNum, String bank, Integer type, String beginTime, String endTime, int size, int goPage) throws Exception;

	/**
	 * @name 根据订单号和业务流水号获得明细实体
	 */
	public Particulars getParticularsNum(String orderNum, String runningNum) throws Exception;

}
