package com.game.util.admin.bizkind.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.BizKind;

public interface BizKindService extends GenericService<BizKind, Long> {

	/**
	 * @name 根据ID删除一个挂卖类别
	 */
	public void removeBizKind(Long id) throws Exception;

	/**
	 * @name 获得所有挂卖类别
	 */
	public List<BizKind> findAllBizKind() throws Exception;

	/**
	 * @name 获得所有使用中的挂卖类别
	 */
	public List<BizKind> findBizKindByIsUse(Integer isUse) throws Exception;

	/**
	 * @name 获得默认挂卖类别
	 * @param isUse
	 */
	public List<BizKind> findBizKindByToleration(Integer isUse) throws Exception;

	/**
	 * @name 获得此游戏的所有挂卖类别
	 */
	public List<BizKind> findGameKindByGame(Long gameID, Integer isUse) throws Exception;

}
