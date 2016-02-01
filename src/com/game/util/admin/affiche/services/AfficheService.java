package com.game.util.admin.affiche.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Affiche;
import com.game.util.web.Page;

public interface AfficheService extends GenericService<Affiche, Long> {

	/**
	 * @name 根据ID删除公告实体
	 */
	public void removeAffiche(Long id) throws Exception;

	/**
	 * @name 分页查询
	 */
	public Page<Affiche> searchAffiche(Integer state, int size, int goPage)
			throws Exception;

	/**
	 * @name 分页查询
	 */
	public Page<Affiche> searchAffiche(Integer type, Integer state, int size,
			int goPage) throws Exception;

	/**
	 * @name 根据类型和状态查询出N条记录
	 */
	public List<Affiche> findAfficheByCount(Integer type, Integer state, int count) throws Exception;

}
