package com.game.util.domain;

public class Order implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3754442777970218433L;
	private Long id; // 标识列
	private String orderNum; // 订单号
	private BizInfo bizInfo; // 挂卖信息外键
	private BizKind bizKind; // 挂卖类别
	private User owner; // '发布者（用户）'
	private User consumer; // 用户（User表外键）
	private String title;
	private String price; // 物品单价
	private String buyBum; // 购买数量
	private String sumPrice; // 总价格
	private String assureMoney; // 中间金额
	private Integer state; // 订单状态
	private Integer playerGrade; // 买家角色等级
	private String playerRole; // 买家角色名
	private String playQQ; // 买家QQ
	private String playPhoneNum; // 买家电话
	private String sellQQ; // 卖家QQ
	private String sellPhoneNum; // 卖家电话
	private String orderTime; // 下单时间
	private String succTime; // 成功时间
	private Integer isAssess; // 评论状态

	private Game game; // '游戏表外键'
	private Server server; // '服务器表外键'
	// ////////////////仅用于搜索条件///////////////////////
	private Integer buyType; // '交易类型（1.寄售、2.担保）'
	private String site; // 交易地点
	private String reason; // 申请内容
	private String outcome; // 处理结果
	private Integer overtime; // 是否超时 0,否 1,是
	private String costTime; // 寄售交易花费时间

	private Integer compensate;// 是否赔付 0,否 1,是
	private String quantity;// 赔偿金额

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public BizInfo getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(BizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}

	public User getConsumer() {
		return consumer;
	}

	public void setConsumer(User consumer) {
		this.consumer = consumer;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBuyBum() {
		return buyBum;
	}

	public void setBuyBum(String buyBum) {
		this.buyBum = buyBum;
	}

	public String getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getAssureMoney() {
		return assureMoney;
	}

	public void setAssureMoney(String assureMoney) {
		this.assureMoney = assureMoney;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPlayerGrade() {
		return playerGrade;
	}

	public void setPlayerGrade(Integer playerGrade) {
		this.playerGrade = playerGrade;
	}

	public String getPlayerRole() {
		return playerRole;
	}

	public void setPlayerRole(String playerRole) {
		this.playerRole = playerRole;
	}

	public String getPlayQQ() {
		return playQQ;
	}

	public void setPlayQQ(String playQQ) {
		this.playQQ = playQQ;
	}

	public String getPlayPhoneNum() {
		return playPhoneNum;
	}

	public void setPlayPhoneNum(String playPhoneNum) {
		this.playPhoneNum = playPhoneNum;
	}

	public String getSellQQ() {
		return sellQQ;
	}

	public void setSellQQ(String sellQQ) {
		this.sellQQ = sellQQ;
	}

	public String getSellPhoneNum() {
		return sellPhoneNum;
	}

	public void setSellPhoneNum(String sellPhoneNum) {
		this.sellPhoneNum = sellPhoneNum;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getSuccTime() {
		return succTime;
	}

	public void setSuccTime(String succTime) {
		this.succTime = succTime;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Integer getIsAssess() {
		return isAssess;
	}

	public void setIsAssess(Integer isAssess) {
		this.isAssess = isAssess;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public Integer getOvertime() {
		return overtime;
	}

	public void setOvertime(Integer overtime) {
		this.overtime = overtime;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	public BizKind getBizKind() {
		return bizKind;
	}

	public void setBizKind(BizKind bizKind) {
		this.bizKind = bizKind;
	}

	public Integer getCompensate() {
		return compensate;
	}

	public void setCompensate(Integer compensate) {
		this.compensate = compensate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
