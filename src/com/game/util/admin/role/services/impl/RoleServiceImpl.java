package com.game.util.admin.role.services.impl;

import java.util.List;

import com.game.util.admin.role.services.RoleService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Role;
import com.game.util.web.Validator;

public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {
	public long getNewId() throws Exception {
		String sql = "select max(a.id) from Role a";
		List<Object> l = baseDAO.findList(sql);
		if(!Validator.isEmpty(l)){
			return Long.parseLong(l.get(0).toString());
		}
		return -1;
	}
	
	public List<Role> getAllRole(int state) throws Exception {
		return baseDAO.findEntity("from Role r where r.state="+state);
	}
	
	public List<Role> getAll() throws Exception {
		return baseDAO.findEntity("from Role r");
	}

	public void remove(long id) throws Exception {
		baseDAO.updateEntity("delete from Role a where a.id="+id);
		//更新菜单表（sys_menu）power字段当power包含该角色时
		baseDAO.updateEntity("update Menu a set a.power=a.power-" + id	+ " where bitand(a.power," + id + ")>0");
	}
}
