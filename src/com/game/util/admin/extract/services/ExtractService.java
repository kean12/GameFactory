package com.game.util.admin.extract.services;

import java.util.Map;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Extract;
import com.game.util.web.Page;

public interface ExtractService extends GenericService<Extract, Long> {

	/**
	 * @name 根据ID删除一条提现记录
	 */
	public void removeExtract(Long id) throws Exception;

	/**
	 * @name 提现记录查询
	 */
	public Page<Extract> searchExtract(Long userID, String bank, String account, Integer state, String beginTime, String endTime, int size, int goPage) throws Exception;

	/**
	 * @name 提现记录查询---后台
	 */
	public Page<Extract> searchExtract(Map<String, Object> map, int size, int goPage) throws Exception;

	public Integer getExtractCount(Long userID) throws Exception;

}
