package com.game.bizinfo.services;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.BizInfo;
import com.game.util.web.Page;

public interface BizInfoService extends GenericService<BizInfo, Long> {

	public BizInfo getBizInfo(Long userID, Long bizInfoID) throws Exception;

	public BizInfo getBizInfo(Long userID, String serial) throws Exception;

	public void removeBizInfo(Long id) throws Exception;

	public void updateBizInfoByHql(String hql) throws Exception;

	/**
	 * @param userID 用户ID null为所有
	 * @param gameID 游戏ID
	 * @param areaID 分区ID
	 * @param serverID 服务器ID
	 * @param bizKindID 分类ID
	 * @param content 搜索内容
	 * @param state 状态 null 所有状态
	 * @param nowTime 现在时间 null
	 * @param order_by 排序字段 属性名
	 * @param sort 排序方式 降序 desc 升序为null
	 * @param size 每页显示数量
	 * @param goPage 第几页
	 */
	public Page<BizInfo> findBizInfoByState(Long userID, Long gameID,
			Long areaID, Long serverID, Long bizKindID, String content,
			Integer state, String nowTime, String order_by, String sort,
			int size, int goPage) throws Exception;
}
