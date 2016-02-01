package com.game.util.admin.role.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.game.util.admin.menu.services.MenuService;
import com.game.util.admin.role.services.RoleService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Menu;
import com.game.util.domain.Role;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

/**
 * 后台权限控制 -- 显示层
 * @author rplees
 */
public class RoleLimitAction extends BaseAction {
	
	private static final long serialVersionUID = 58562441419821922L;
	
	private MenuService menuService ;
	private RoleService roleService ;

	List<Menu> menuList ;
	List<Role> roleList ;
	
	private String strRoleLimit ;
	public String checkLimit() throws Exception{
		return "roleLimitsSetup";
	}
	
	public String saveRoleLimits() throws Exception{
		if(Validator.isBlank(strRoleLimit)){
			setErrorMessage("一个都没有选,不能被保存!");
			return "roleLimitsSetup";
		}
		//格式为 '208,208_29,208_30,208_31,208_32,208_33,208_36,208_37,208_38,208_39'
		Map<Long, List<Long> > map = new HashMap<Long, List<Long> >();
		List<Long> list ;
		String[] arrs = strRoleLimit.split(","), tempArrs ;
		Long menuId , roleId ;
		for (int i = 0; i < arrs.length; i++) {
			if(!Validator.isBlank(arrs[i]) && arrs[i].indexOf("_") > 0){
				tempArrs = arrs[i].split("_");
				menuId = Long.parseLong(tempArrs[1]) ;
				roleId = Long.parseLong(tempArrs[0]) ;
				
				if(map.containsKey(menuId)){
					list = map.get(menuId);
					if(!list.contains(roleId)){
						list.add(roleId);
					}
				}else {
					list = new ArrayList<Long>(4);
					list.add(roleId) ;
					map.put(menuId, list);
				}
			}
		}
		if(map.size() > 1){
			menuService.updatePowers(map);
		}
		setErrorMessage("成功被保存!");
		return "roleLimitsSetup";
	}
	/*
	 * {id:0, item:[
	 * 			{id:1,text:"first"},
	 * 			{id:2, text:"middle", item:[
	 * 				{id:"21", text:"child",checked:'true'}
	 * 			]}, 
	 * 			{id:3,text:"last"}
	 * 			]
	 * }
	 * */
	public String dhtmlxTreeJSON() throws Exception{
		HttpServletResponse response = Struts2Util.getResponse();
		response.setContentType("text/x-json");
		response.setCharacterEncoding("utf-8");
		StringBuffer buff = new StringBuffer("{id:0, item:[") ;
		
		roleList = roleService.getAllRole(1) ;
		menuList = menuService.findMenuByParent( null );
		
		for (Role r : roleList) {
			buff.append("{id:'" + r.getId() + "',text:'" + r.getDescription() + "',color:'red'");
			menuTreeJSON(r.getId(), buff);
			buff.append("},");
		}
		buff.deleteCharAt(buff.length() - 1).append("]}");
		PrintWriter out = response.getWriter();
		out.print(buff.toString());
		out.close();
		return NONE;
	}
	/** 三级菜单树形结构，JSON格式
	 * @param roleId
	 * @param buff
	 * @throws Exception
	 */
	private void menuTreeJSON(long roleId , StringBuffer buff) throws Exception{
		if( Validator.isEmpty(menuList) ) return ;
		
		String id ;
		Menu m ;
		Set<Menu> childSet , set;
		buff.append(",item:[");
		
		for (int i = 0; i < menuList.size(); i++) {
			m = menuList.get(i);
			id = (int)roleId + "_" + m.getId().intValue() ;
			buff.append("{id:'"+id+"',text:'"+m.getMenuName()+"'");
	
			if( !Validator.isEmpty( m.getChild() ) ){
				set = m.getChild();
				buff.append(",item:[");
				for (Menu chidMenu : set) {
					id = (int)roleId + "_" + chidMenu.getId().intValue() ;
					buff.append("{id:'"+id+"',text:'"+chidMenu.getMenuName()+"'");
					
					if( !Validator.isEmpty( chidMenu.getChild() ) ){
						childSet = chidMenu.getChild();
						buff.append(",item:[");
						for (Menu chidChildMenu : childSet) {
							id = (int)roleId + "_" + chidChildMenu.getId().intValue() ;
							buff.append("{id:'"+id+"',text:'"+chidChildMenu.getMenuName()+"',checked:"+chidChildMenu.checkPower(roleId)+"},");
						}
						buff.deleteCharAt(buff.length() - 1);

						buff.append("]");
					}
					
					buff.append("},");
					
				}
				buff.deleteCharAt(buff.length() - 1);
				
				buff.append("]");
				
			}
			
			buff.append("},");

		}
		buff.deleteCharAt(buff.length() - 1).append("]");
	}
	//~===============getting setting===============================
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public String getStrRoleLimit() {
		return strRoleLimit;
	}

	public void setStrRoleLimit(String strRoleLimit) {
		this.strRoleLimit = strRoleLimit;
	}
}
