package com.game.util.domain;

public class Post implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3880236793415528698L;
	private Long id;
	private Message message; // 信息id
	private User user; // 用户id
	private Integer type; // 类别：1，收件，0，发件
	private Integer state; // 状态：1，已读

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
