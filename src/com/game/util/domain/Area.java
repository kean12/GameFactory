package com.game.util.domain;

public class Area implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -923935997473889973L;
	private Long id;// 游戏编号
	private String areaName;// 分区名称
	private Game game;// 所属游戏
	private Integer state;// 状态

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
