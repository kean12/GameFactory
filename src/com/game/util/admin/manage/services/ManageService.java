package com.game.util.admin.manage.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Manage;
import com.game.util.web.Page;

public interface ManageService extends GenericService<Manage, Long> {

	/**
	 * @name 根据管理员登录名查询
	 */
	public Manage findManageByName(String username) throws Exception;

	/**
	 * @name 根据id删除实体
	 */
	public void removeManage(Long id) throws Exception;

	/**
	 * @name 获得所有管理员
	 */
	public Page<Manage> getAllManage(int size, int goPage) throws Exception;

	/**
	 * @name 根据角色名查询后台用户
	 */
	public List<Manage> findManageByRole(String name, Integer state) throws Exception;

	/**
	 * 根据用户登录名查询后台用户
	 * 
	 * @param name
	 *            用户真实姓名
	 * @param roleName
	 *            角色名
	 * @throws Exception
	 */
	public Manage findManageByName(String name, String roleName) throws Exception;

	/**
	 * 根据客户端传递过来的用户名和密码登陆
	 * 
	 * @param name
	 *            用户真实姓名
	 * @param roleName
	 *            角色名
	 * @throws Exception
	 */
	public Manage findManageByClientLogin(String username, String password) throws Exception;

}
