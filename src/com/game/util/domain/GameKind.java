package com.game.util.domain;

public class GameKind implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2902962806016866653L;
	private Long id;
	private BizKind bizKind;// 所属种类
	private Game game; // 所属游戏
	private String unit;// 单位
	private Integer tradeType;// 挂卖方式0,普通方式 1,账号交易 2,自定义属性

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BizKind getBizKind() {
		return bizKind;
	}

	public void setBizKind(BizKind bizKind) {
		this.bizKind = bizKind;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

}
