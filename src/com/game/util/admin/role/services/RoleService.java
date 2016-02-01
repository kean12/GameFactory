package com.game.util.admin.role.services;

import java.util.List;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Role;

public interface RoleService extends GenericService<Role, Long> {
	long getNewId() throws Exception ;
	
	List<Role> getAllRole(int state) throws Exception ;
	
	List<Role> getAll() throws Exception ;
	
	void remove(long id) throws Exception ;
}
