package com.game.util.domain;

/**
 * 游戏职业
 */
public class Profession implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8772472739755082969L;
	private Long id;
	private String professionName;// 职业名称
	private Game game;// 所属游戏
	private Integer isUse;// 是否启用
	private Integer orderIndex;// 排列顺序

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

}
