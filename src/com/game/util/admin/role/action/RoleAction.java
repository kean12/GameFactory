package com.game.util.admin.role.action;

import java.util.List;

import com.game.util.admin.role.services.RoleService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Role;

public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 58562441419821922L;

	private Role role ;
	private RoleService roleService;

	private List<Role> roleList ;
	private long id ;

	public String list() throws Exception{
		roleList = roleService.getAll() ;
		return "list" ;
	}
	
	public String add() throws Exception{
		if(id > 0){
			role = roleService.getEntity(Role.class,id);
		}
		return "add";
	}
	
	public String save() throws Exception{
		if(role.getId() != null && role.getId() > 0){
			roleService.updateEntity(role);
		}else{
			long newId = roleService.getNewId() ;
			if(newId < 0){
				throw new Exception("获得角色最大ID时抛出异常！");
			}
			if(newId == 0){
				newId = 1 ;
			}
			role.setId(newId << 1); //设置ID为2的位数
			roleService.createEntity(role);
		}
		return SUCCESS;
	}
	
	public String remove() throws Exception{
		if(id > 0){
			roleService.remove(id);
		}
		return SUCCESS ;
	}
	//~=================================
	public void setRole(Role role) {
		this.role = role;
	}
	public RoleService getRoleService() {
		return roleService;
	}

	public long getId() {
		return id;
	}
	public Role getRole() {
		return role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public void setId(long id) {
		this.id = id;
	}
}
