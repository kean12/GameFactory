package com.game.util.user.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.User;
import com.game.util.web.Page;

public interface UserService extends GenericService<User, Long> {
	/**
	 * 根据u条件查找page集合
	 * 
	 * @param size
	 * @param goPage
	 * @param u条件对象
	 * @return
	 */
	Page<User> getAllUsers(int size, int goPage, User filterU) throws Exception;

	Page<User> getAllUsers(int size, int goPage, User filterU, String orderBy) throws Exception;

	/**
	 * @name 添加一个用户
	 */
	public User createUser(User user) throws Exception;

	/**
	 * @name 更新一个用户
	 */
	public void updateUser(User user) throws Exception;

	/**
	 * @name 根据ID获得用户实体
	 */
	public User getUserById(Long id) throws Exception;

	/**
	 * 获得添加数据库的id值
	 */
	public Long getUserMaxID() throws Exception;

	/**
	 * 根据用户名查询
	 */
	public User findUserByName(String username, Integer isUse) throws Exception;

	/**
	 * 根据状态查询用户集
	 */
	public List<User> findUserByIsUse(Integer isUse) throws Exception;

}
