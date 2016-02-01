package com.game.util.admin.bizkind.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Details;

public interface DetailsService extends GenericService<Details, Long> {

	/**
	 * @name 根据ID删除一个实体
	 */
	public void removeDetails(Long id) throws Exception;

	public List<Details> findDetailsByGameKind(Long gameKindID) throws Exception;

	public Details findDetailsByGameKind(Long gameKindID, Integer type) throws Exception;

	/**
	 * @param groupType 1.账号分组
	 */
	public Details findDetailsByAccountGroup(Long gameKindID, Integer groupType) throws Exception;

	/**
	 * @param groupType 2.属性分组
	 */
	public List<Details> findDetailsByAttributeGroup(Long gameKindID, Integer groupType) throws Exception;

	public Details findDetailsByFormName(Long gameKindID, String formName, Long notID) throws Exception;

}
