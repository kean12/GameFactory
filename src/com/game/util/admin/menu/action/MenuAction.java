package com.game.util.admin.menu.action;

import java.util.List;

import com.game.util.admin.menu.services.MenuService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Menu;

public class MenuAction extends BaseAction {
	private static final long serialVersionUID = -4726616146336590004L;
	private MenuService menuService;
	private Menu menu;
	private Long menuID;
	private Long parentID;
	private List<Menu> menuList;

	/**
	 * @name 获得大类
	 */
	public String listMenu() throws Exception {
		menuList = menuService.findMenuByParent(null);
		return "listMenu";
	}

	/**
	 * @name 进入添加更改页面
	 */
	public String add() throws Exception {
		if (menuID != null) {
			menu = menuService.getEntity(Menu.class, menuID);
		}
		return "add";
	}

	/**
	 * @name 保存菜单
	 */
	public String saveMenu() throws Exception {
		if (menu.getId() != null) {
			menuService.updateEntity(menu);
		} else {
			menuService.createEntity(menu);
		}
		return "saveMenu";
	}

	/**
	 * @name 删除菜单
	 */
	public String delete() throws Exception {
		if (menuID != null) {
			menu = menuService.getEntity(Menu.class, menuID);
			menuService.removeEntity(menu);
		} else {
			return listMenu();
		}
		return "saveMenu";
	}

	/**
	 * @name 二级菜单
	 */
	public String listSecond() throws Exception {
		if (menuID != null) {
			menu = menuService.getEntity(Menu.class, menuID);
			return "listsecond";
		} else {
			return listMenu();
		}
	}

	/**
	 * @name 添加二级菜单
	 */
	public String addSecond() throws Exception {
		if (menuID != null) {
			menu = menuService.getEntity(Menu.class, menuID);
		}
		return "addsecond";
	}

	/**
	 * @name 保存二级菜单
	 */
	public String saveSecond() throws Exception {
		if (menu.getId() != null) {
			menuService.updateEntity(menu);
		} else {
			menuService.createEntity(menu);
		}
		return "savesecond";
	}

	/**
	 * @name 删除二级菜单
	 */
	public String deleteSecond() throws Exception {
		if (menuID != null) {
			menu = menuService.getEntity(Menu.class, menuID);
			menuService.removeEntity(menu);
		} else {
			return listMenu();
		}
		return "savesecond";
	}

	/**
	 * @name 三级菜单
	 */
	public String listThird() throws Exception {
		if (menuID != null) {
			menu = menuService.getEntity(Menu.class, menuID);
			return "listthird";
		} else {
			return listMenu();
		}
	}

	/**
	 * @name 添加三级菜单
	 */
	public String addThird() throws Exception {
		if (menuID != null) {
			menu = menuService.getEntity(Menu.class, menuID);
		}
		return "addthird";
	}

	/**
	 * @name 保存三级菜单
	 */
	public String saveThird() throws Exception {
		if (menu.getId() != null) {
			menuService.updateEntity(menu);
		} else {
			menuService.createEntity(menu);
		}
		return "savethird";
	}

	/**
	 * @name 删除三级菜单
	 */
	public String deleteThird() throws Exception {
		if (menuID != null) {
			menu = menuService.getEntity(Menu.class, menuID);
			menuService.removeEntity(menu);
		} else {
			return listMenu();
		}
		return "savethird";
	}

	public String updateMenu() throws Exception {
		return listMenu();
	}

	public String deleteMenu() throws Exception {
		return listMenu();
	}

	// 子菜单操作
	public String listMenuChild() throws Exception {
		return "listMenuChild";
	}

	public String updateMenuChild() throws Exception {
		return listMenuChild();
	}

	public String deleteMenuChild() throws Exception {
		return listMenuChild();
	}

	public String saveMenuChild() throws Exception {
		return listMenuChild();
	}

	// *******get/set方法*******
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public Long getMenuID() {
		return menuID;
	}

	public void setMenuID(Long menuID) {
		this.menuID = menuID;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public Long getParentID() {
		return parentID;
	}

	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}
}
