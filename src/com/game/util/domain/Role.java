package com.game.util.domain;

public class Role implements Domain {

	private static final long serialVersionUID = 1L;
	/**
	 * =================SYS_ROLE================= ID INTEGER N 用户ID NAME
	 * VARCHAR2(20) N 角色英文名 DESCRIPTION VARCHAR2(80) Y 角色描述 STATE NUMBER Y 1
	 * 状态（1-启用，0-禁用） =================SYS_ROLE=================
	 */
	private long id;
	private String name;
	private String description;
	private int state;

	// ~=========getting setting=============
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
