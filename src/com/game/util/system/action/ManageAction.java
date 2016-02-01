package com.game.util.system.action;

import java.util.List;

import com.game.util.admin.menu.services.MenuService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Menu;

public class ManageAction extends BaseAction {

	private static final long serialVersionUID = -3984225133090736698L;

	private MenuService menuService;

	private List<Menu> menuList;

	/**
	 * @name 后台首页
	 */
	public String index() throws Exception {
		return "index";
	}

	/**
	 * @name 后台框架-头
	 */
	public String top() throws Exception {
		return "top";
	}

	/**
	 * @name 后台框架-左侧菜单
	 */
	public String left() throws Exception {
		menuList = menuService.findMenuByParent(null);
		return "left";
	}

	/**
	 * @name 后台框架-主要页面
	 */
	public String main() throws Exception {
		return "main";
	}

	// *********get/set方法************
	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
}