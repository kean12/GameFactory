package com.game.util.admin.game.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Profession;

public interface ProfessionService extends GenericService<Profession, Long> {

	/**
	 * @name 根据ID删除游戏职业
	 */
	public void removeProfession(Long id) throws Exception;

	/**
	 * @name 根据游戏ID获得职业列表
	 */
	public List<Profession> findProfessionByGame(Long gameID, Integer isUse) throws Exception;

	/**
	 * @name 根据游戏ID和职业名称获得实体
	 */
	public Profession findProfessionByName(Long gameID, String professionName) throws Exception;

}
