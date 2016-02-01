package com.game.util.domain;

public class Affiche implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8228527382724809411L;
	private Long id;// 编号
	private String title;// 标题
	private String content;// 内容
	private String time;// 时间
	private Manage manage;// 管理员
	private Integer type;// 类别 1:'网站公告',2:'最新动态',3:'官方新闻'
	private Integer state;// 状态 是否显示

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

	public Manage getManage() {
		return manage;
	}

	public void setManage(Manage manage) {
		this.manage = manage;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
