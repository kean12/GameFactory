package com.game.util.user.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.UserRole;

public interface UserRoleService extends GenericService<UserRole, Long> {
	/**
	 * 添加一个收获角色名
	 */
	public UserRole createUserRole(UserRole userRole) throws Exception;

	/**
	 * 删除一个收获角色名
	 */
	public void removeUserRole(Long id) throws Exception;

	/**
	 * 获得一个收获角色名
	 */
	public UserRole getUserRoleById(Long id) throws Exception;

	public List<UserRole> findUserRoleByUser(Long userID) throws Exception;

	public UserRole getUserRole(Long userID, String roleName) throws Exception;

}
