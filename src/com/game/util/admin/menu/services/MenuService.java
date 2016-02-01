package com.game.util.admin.menu.services;

import java.util.List;
import java.util.Map;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Menu;

public interface MenuService extends GenericService<Menu, Long> {

	public void updatePowers(Map<Long, List<Long>> powersMap) throws Exception;

	/**
	 * @param menuName 菜单名称
	 * @param parentID
	 * @name 根据菜单名查询
	 */
	public Menu findMenuByName(String menuName, Long parentID) throws Exception;

	/**
	 * @param parentID 父节点ID
	 * @name 根据菜单名查询
	 */
	public List<Menu> findMenuByParent(Long parentID) throws Exception;

	/**
	 * @name 根据id删除实体
	 */
	public void removeMenu(Long id) throws Exception;

	public List<Menu> getAllMenu() throws Exception;

	public List<Menu> findMenuByView(Integer isView) throws Exception;

	public List<Menu> findMenuByHql(String hql) throws Exception;

}
