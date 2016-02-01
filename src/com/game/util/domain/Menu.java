package com.game.util.domain;

import java.util.HashSet;
import java.util.Set;

public class Menu implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -928323406819770398L;
	private Long id;
	private String menuName;// 名称
	private String actionURL;// 提交路径
	private Integer isView;// 是否显示
	private String target; // �提交方式
	private Integer orderIndex;// 排列顺序
	private Menu parent;// 父类
	private Integer power;// 权限-2进制处理方式2的倍数
	private Set<Menu> child = new HashSet<Menu>(0);//

	public boolean checkPower(long roleId) {
		return (power != null && power != 0 && (power.intValue() & roleId) > 0);
	}

	// ~==================getting setting==========================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public String getActionURL() {
		return actionURL;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}

	public Integer getIsView() {
		return isView;
	}

	public void setIsView(Integer isView) {
		this.isView = isView;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public Set<Menu> getChild() {
		return child;
	}

	public void setChild(Set<Menu> child) {
		this.child = child;
	}
}
