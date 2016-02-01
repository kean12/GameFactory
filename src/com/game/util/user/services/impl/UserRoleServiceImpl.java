package com.game.util.user.services.impl;

import java.util.List;

import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.UserRole;
import com.game.util.user.services.UserRoleService;
import com.game.util.web.Validator;


public class UserRoleServiceImpl extends GenericServiceImpl<UserRole, Long> implements UserRoleService{
	
	public UserRole createUserRole(UserRole userRole) throws Exception {
		return (UserRole)baseDAO.saveEntity(userRole);
	}

	public void removeUserRole(Long id) throws Exception {
		baseDAO.removeEntity(getUserRoleById(id));
	}
	
	public UserRole getUserRoleById(Long id)throws Exception{
		return (UserRole)baseDAO.getEntity(UserRole.class, id);
	}

	public List<UserRole> findUserRoleByUser(Long userID) throws Exception {
		return baseDAO.findEntity("from UserRole a where a.user.id=? order by id",userID);
	}

	public UserRole getUserRole(Long userID, String roleName) throws Exception {
		String hql="from UserRole a where a.user.id=" + userID + " and a.roleName=?";
		List<UserRole> list=baseDAO.findEntity(hql,roleName);
		if(!Validator.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
}
