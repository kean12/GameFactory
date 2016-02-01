package com.game.util.admin.game.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Game;
import com.game.util.web.Page;

public interface GameService extends GenericService<Game, Long> {

	/**
	 * @name 根据ID删除游戏
	 */
	public void removeGame(Long id) throws Exception;

	/**
	 * @name 分页查询
	 */
	public Page<Game> searchGame(String gameName, int size, int goPage) throws Exception;

	/**
	 * 获得添加数据库的id值
	 */
	public Long getGameMaxID() throws Exception;

	/**
	 * 根据游戏名查询游戏
	 */
	public Game findGameByName(String gameName) throws Exception;

	public List<Game> findGameByName(String gameName, Integer state) throws Exception;

	public List<Game> findGameByState(Integer state) throws Exception;

	public List<String> findGameCompany(Integer state) throws Exception;

	public List<Game> findGameByIndex(String gameIndex, Integer state) throws Exception;

	/**
	 * @name 获得没有设置挂卖类别的游戏
	 */
	public List<Game> findGameNotSetBizKind(Long bizKindID) throws Exception;
}
