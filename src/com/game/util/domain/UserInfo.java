package com.game.util.domain;

public class UserInfo implements Domain {
	private static final long serialVersionUID = -4950691981485603948L;
	private Long id;
	private User user;
	private String money; // 账户可用余额
	private Integer buyerCredit; // 买家积分
	private Integer buyerGrade; // 买家信誉等级
	private Integer sellerCredit; // 卖家积分
	private Integer sellerGrade; // 卖家信誉等级
	private String freemoney; // 冻结金额
	private String applyPwd; // 支付密码
	private String ip; // 登陆地址
	private String loginTime; // 登陆时间
	private String tmpIp; // 上次登陆地址
	private String tmpTime; // 上次登陆时间
	private String sellerPositiveRatio; // 好评率
	private String buyerPositiveRatio; // 好评率
	private String bankName; // 银行名称
	private String bankSite; // 开户所在地
	private String bankNum; // 银行账号
	private String route; // 头像路径

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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Integer getBuyerCredit() {
		return buyerCredit;
	}

	public void setBuyerCredit(Integer buyerCredit) {
		this.buyerCredit = buyerCredit;
	}

	public Integer getBuyerGrade() {
		return buyerGrade;
	}

	public void setBuyerGrade(Integer buyerGrade) {
		this.buyerGrade = buyerGrade;
	}

	public Integer getSellerCredit() {
		return sellerCredit;
	}

	public void setSellerCredit(Integer sellerCredit) {
		this.sellerCredit = sellerCredit;
	}

	public Integer getSellerGrade() {
		return sellerGrade;
	}

	public void setSellerGrade(Integer sellerGrade) {
		this.sellerGrade = sellerGrade;
	}

	public String getFreemoney() {
		return freemoney;
	}

	public void setFreemoney(String freemoney) {
		this.freemoney = freemoney;
	}

	public String getApplyPwd() {
		return applyPwd;
	}

	public void setApplyPwd(String applyPwd) {
		this.applyPwd = applyPwd;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getTmpIp() {
		return tmpIp;
	}

	public void setTmpIp(String tmpIp) {
		this.tmpIp = tmpIp;
	}

	public String getTmpTime() {
		return tmpTime;
	}

	public void setTmpTime(String tmpTime) {
		this.tmpTime = tmpTime;
	}

	public String getSellerPositiveRatio() {
		return sellerPositiveRatio;
	}

	public void setSellerPositiveRatio(String sellerPositiveRatio) {
		this.sellerPositiveRatio = sellerPositiveRatio;
	}

	public String getBuyerPositiveRatio() {
		return buyerPositiveRatio;
	}

	public void setBuyerPositiveRatio(String buyerPositiveRatio) {
		this.buyerPositiveRatio = buyerPositiveRatio;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankSite() {
		return bankSite;
	}

	public void setBankSite(String bankSite) {
		this.bankSite = bankSite;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

}
