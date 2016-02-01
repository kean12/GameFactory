package com.game.util.domain;

public class Message implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3941693788963256707L;
	private Long id;
	private String title; // 信息标题
	private String content; // 信息内容
	private String time; // 信息时间
	private String addresser; // 发布者
	private Integer type; // 信息类别：1.系统信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddresser() {
		return addresser;
	}

	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
