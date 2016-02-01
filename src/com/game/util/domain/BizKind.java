package com.game.util.domain;

public class BizKind implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8646507680570859769L;
	private Long id;// 编号
	private String kindName;// 类别名称
	private Integer isUse;// 是否使用
	private Integer orderIndex;// 排列顺序
	private Integer toleration;// 是否默认选项 1,是 0,否
	private String unit; // 默认单位
	private String tradeTime;// 交易时间限定
	private Integer tradeType;// 默认挂卖方式0,普通方式 1,账号交易 2,自定义属性
	private String remark;// 备注

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Integer getToleration() {
		return toleration;
	}

	public void setToleration(Integer toleration) {
		this.toleration = toleration;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

}
