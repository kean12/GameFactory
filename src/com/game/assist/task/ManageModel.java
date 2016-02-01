package com.game.assist.task;

import java.io.Serializable;

public class ManageModel implements Serializable {
	private static final long serialVersionUID = -1536746015533271534L;
	private Long id;// 编号
	private String username;// 管理员登录名称
	private String password;// 登录密码
	private String name;// 真实姓名

	public ManageModel() {
	}

	public ManageModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public ManageModel(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
