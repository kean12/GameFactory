package com.game.util.admin.area.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Area;
import com.game.util.web.Page;

public interface AreaService extends GenericService<Area, Long> {

	/**
	 * @name 分页查询
	 */
	public Page<Area> searchAreaByGame(String areaName, Long gameID, int size, int goPage) throws Exception;

	/**
	 * 获得添加数据库的id值
	 */
	public Long getAreaMaxID(Long gameID) throws Exception;

	/**
	 * 根据分区名查询分区
	 */
	public Area findAreaByName(String areaName, Long gameID) throws Exception;

	public List<Area> findAreaByState(Integer state) throws Exception;

	public List<Area> finfAreaByGame(Long gameID, Integer state) throws Exception;

}
