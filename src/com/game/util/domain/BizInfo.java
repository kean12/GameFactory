package com.game.util.domain;

import java.util.HashSet;
import java.util.Set;

public class BizInfo implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8352770273260275131L;
	private Long id; // '标识列';
	private String serial; // 商品编号
	private BizKind bizKind; // 类别外键（游戏币、装备）
	private Game game; // '游戏表外键'
	private Server server; // '服务器表外键'
	private Integer buyType; // '交易类型（1.寄售、2.担保）'
	private String stock; // '库存量'
	private String price; // '单价'
	private User owner; // '发布者（用户）'
	private Integer isBuy; // '是否上架(1、上架 0、下架)'
	private String bizCreTime; // '添加时间'
	private String proportion; // '比例'
	private String unit; // '单位（金、万金）'
	private Integer sellModel; // '交易方式（1.游戏中当面 2.邮寄 3,账号交易 4,自定义属性交易）'
	private String site; // 交易地点
	private String startSellTime;// '开始销售时间'
	private String endSellTime; // '结束销售时间'
	private String tradeStart; // 方便交易时间
	private String tradeEnd; //
	private String qq; // 联系QQ
	private String phoneNum; // 联系电话
	private String title; // '上架描述信息'
	private String info; // '上架描述信息'
	private String account; // 账户
	private String accountInfo; // 账号其他信息
	private String password; // 密码
	private String pwdSrc; // 密保卡路径
	private String roleName; // 账号角色名
	private String coded_lock; // 密码锁
	private String place; // 物品存放处
	private String amount; // 每件宝贝数量
	private Set<Picture> pictures = new HashSet<Picture>(); // 图片信息集
	private Set<Identity> identities = new HashSet<Identity>(); // 身份证图片信息集

	private Float unit_price; // 比例——价格
	private Integer term_of_validity; // 有效时间 7，15，30

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
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

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer e) {
		this.buyType = e;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Integer getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(Integer isBuy) {
		this.isBuy = isBuy;
	}

	public String getBizCreTime() {
		return bizCreTime;
	}

	public void setBizCreTime(String bizCreTime) {
		this.bizCreTime = bizCreTime;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getSellModel() {
		return sellModel;
	}

	public void setSellModel(Integer sellModel) {
		this.sellModel = sellModel;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getStartSellTime() {
		return startSellTime;
	}

	public void setStartSellTime(String startSellTime) {
		this.startSellTime = startSellTime;
	}

	public String getEndSellTime() {
		return endSellTime;
	}

	public void setEndSellTime(String endSellTime) {
		this.endSellTime = endSellTime;
	}

	public String getTradeStart() {
		return tradeStart;
	}

	public void setTradeStart(String tradeStart) {
		this.tradeStart = tradeStart;
	}

	public String getTradeEnd() {
		return tradeEnd;
	}

	public void setTradeEnd(String tradeEnd) {
		this.tradeEnd = tradeEnd;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Float getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Float unitPrice) {
		unit_price = unitPrice;
	}

	public Integer getTerm_of_validity() {
		return term_of_validity;
	}

	public void setTerm_of_validity(Integer termOfValidity) {
		term_of_validity = termOfValidity;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwdSrc() {
		return pwdSrc;
	}

	public void setPwdSrc(String pwdSrc) {
		this.pwdSrc = pwdSrc;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCoded_lock() {
		return coded_lock;
	}

	public void setCoded_lock(String codedLock) {
		coded_lock = codedLock;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public Set<Identity> getIdentities() {
		return identities;
	}

	public void setIdentities(Set<Identity> identities) {
		this.identities = identities;
	}

}
