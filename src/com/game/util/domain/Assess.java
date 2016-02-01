package com.game.util.domain;

/**
 * 评论类
 */
public class Assess implements Domain {
	private static final long serialVersionUID = 9192377967985224283L;

	/**
	 * 标识列
	 */
	private Long id;

	/**
	 * 被评用户
	 */
	private User passive;

	/**
	 * 评论人
	 */
	private User initiative;

	/**
	 * 订单外键
	 */
	private Order order;

	/**
	 * 是否买家评论
	 */
	private Integer type;

	/**
	 * 评论等级
	 */
	private Integer grade;

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 评论时间
	 */
	private String time;

	/**
	 * 解释备注
	 */
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getPassive() {
		return passive;
	}

	public void setPassive(User passive) {
		this.passive = passive;
	}

	public User getInitiative() {
		return initiative;
	}

	public void setInitiative(User initiative) {
		this.initiative = initiative;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	

}
