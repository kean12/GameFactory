package com.game.util.domain;

public class Credit implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4685261225399875537L;
	private Long id; // 编号
	private Integer grade; // 等级
	private Integer lower; // 积分最小值
	private Integer upper; // 积分最大值
	private Integer type; // 类型（1.商家积分设置 2.客户积分设置）

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getLower() {
		return lower;
	}

	public void setLower(Integer lower) {
		this.lower = lower;
	}

	public Integer getUpper() {
		return upper;
	}

	public void setUpper(Integer upper) {
		this.upper = upper;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
