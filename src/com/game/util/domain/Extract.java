package com.game.util.domain;

public class Extract implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1353683384515494261L;
	private Long id;
	private String extractNum;// 交易编号
	private User user;// 提现用户
	private String managerName;// 操作管理员
	private String money; // 提现金额
	private String charge; // 手续费
	private String bank; // 银行
	private String account; // 银行账号
	private String time; // 申请时间
	private String processTime;// 处理时间
	private Integer state; // 状态 0.未处理，-1.拒绝，1.成功，2.处理中

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExtractNum() {
		return extractNum;
	}

	public void setExtractNum(String extractNum) {
		this.extractNum = extractNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

}
