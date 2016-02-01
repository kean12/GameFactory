package com.game.util.domain;

public class Server implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7217983966424582718L;
	private Long id;// 服务器编号
	private String serverName;//服务器名称
	private Area area;//所属分区
	private Integer state;//状态

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
