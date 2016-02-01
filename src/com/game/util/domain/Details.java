package com.game.util.domain;

import java.util.HashSet;
import java.util.Set;

public class Details implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6944600250982489158L;
	private Long id;
	private GameKind gameKind; // 游戏挂卖种类外键
	private String attributeName;// 属性名
	private String formName; // 表单名
	private Integer type; // 类型1,账号属性 2,普通属性
	private Integer isUser; // 是否启用
	private Integer orderIndex; // 排列顺序
	private Details parent;// 父类
	private Set<Details> child = new HashSet<Details>(0);//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GameKind getGameKind() {
		return gameKind;
	}

	public void setGameKind(GameKind gameKind) {
		this.gameKind = gameKind;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsUser() {
		return isUser;
	}

	public void setIsUser(Integer isUser) {
		this.isUser = isUser;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Details getParent() {
		return parent;
	}

	public void setParent(Details parent) {
		this.parent = parent;
	}

	public Set<Details> getChild() {
		return child;
	}

	public void setChild(Set<Details> child) {
		this.child = child;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

}
