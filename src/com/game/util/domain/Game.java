package com.game.util.domain;

public class Game implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -38151471382798217L;
	private Long id;// 游戏编号
	private String gameName;// 游戏名称
	private String pinyin;// 全拼
	private Integer state;// 状态
	private String company;// 游戏厂商
	private String gameIndex;// 游戏代码（汉字首字母缩写）
	private Integer gameHot;// 是否热门：1、游戏列表显示2、热门游戏（网页头部）4、求购交易游戏8、游戏下拉菜单16、热门点卡
	private String gameNum;// 游戏编号 00123
	private Integer orderIndex;// 排列顺序

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getGameIndex() {
		return gameIndex;
	}

	public void setGameIndex(String gameIndex) {
		this.gameIndex = gameIndex;
	}

	public Integer getGameHot() {
		return gameHot;
	}

	public void setGameHot(Integer gameHot) {
		this.gameHot = gameHot;
	}

	public String getGameNum() {
		return gameNum;
	}

	public void setGameNum(String gameNum) {
		this.gameNum = gameNum;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

}
