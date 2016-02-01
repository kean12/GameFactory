package com.game.util.admin.bizkind.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.GameKind;

public interface GameKindService extends GenericService<GameKind, Long> {
	/**
	 * @name 根据ID删除一个此游戏挂卖类别
	 */
	public void removeGameKind(Long id) throws Exception;

	/**
	 * @name 获得此游戏的所有挂卖类别
	 */
	public List<GameKind> findGameKindByGame(Long gameID) throws Exception;

	/**
	 * @name 根据挂卖种类和所属游戏获得对象
	 */
	public GameKind getGameKind(Long bizKind, Long gameID) throws Exception;

}
