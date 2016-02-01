package com.game.util.domain;

public class Complaint implements Domain {
	private static final long serialVersionUID = -5952811308023245322L;
	private Long id;
	private Order order; // 投诉订单号
	private User user; // 投诉用户
	private User defendant; // 被投诉用户
	private String content; // 投诉内容
	private String answer; // 响应内容
	private Integer type; // 类型，1.卖家投诉 2.买家投诉
	private Integer state; // 状态 0.未处理 1.已处理 2.处理中
	private String applyTime; // 投诉时间
	private String processTime; // 处理时间
	private String manageName; // 处理客服名称

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getDefendant() {
		return defendant;
	}

	public void setDefendant(User defendant) {
		this.defendant = defendant;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	public String getManageName() {
		return manageName;
	}

	public void setManageName(String manageName) {
		this.manageName = manageName;
	}

}
