package com.game.util.domain;

public class Particulars implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985481536777679066L;
	private Long id;
	private User user; // '用户外键'
	private String orderNum; // '商户订单号'
	private String runningNum; // '业务流水号'
	private String time; // '交易时间'
	private String bank; // '交易场所'
	private Integer type; // '交易类型 1.交易付款 2.交易收款 3.在线充值 4.退款 5.提现' 6.差额入账
							// 7.超时赔付
	private String income; // '收入'
	private String expense; // '支出'
	private String money; // '账户总额'
	private String synopsis; // '交易详情'
	private String remark; // '备注'

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getRunningNum() {
		return runningNum;
	}

	public void setRunningNum(String runningNum) {
		this.runningNum = runningNum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
