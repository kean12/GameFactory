package com.game.util.admin.server.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Server;
import com.game.util.web.Page;

public interface ServerService extends GenericService<Server, Long> {

	/**
	 * @name 分页查询
	 */
	public Page<Server> searchServerByArea(String serverName, Long areaID, int size, int goPage) throws Exception;

	/**
	 * 获得添加数据库的id值
	 */
	public Long getServerMaxID(Long areaID) throws Exception;

	/**
	 * 根据分区名查询分区
	 */
	public Server findServerByName(String serverName, Long areaID) throws Exception;

	public List<Server> findServerByState(Integer state) throws Exception;

	public List<Server> findServerByArea(Long areaID, Integer state) throws Exception;
}
