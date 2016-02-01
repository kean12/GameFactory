package com.game.bizinfo.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.game.bizinfo.services.BizInfoService;
import com.game.util.admin.bizkind.services.DetailsService;
import com.game.util.admin.bizkind.services.GameKindService;
import com.game.util.admin.game.services.GameService;
import com.game.util.admin.game.services.ProfessionService;
import com.game.util.admin.server.services.ServerService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.BizInfo;
import com.game.util.domain.BizKind;
import com.game.util.domain.Details;
import com.game.util.domain.Game;
import com.game.util.domain.GameKind;
import com.game.util.domain.Identity;
import com.game.util.domain.Picture;
import com.game.util.domain.Profession;
import com.game.util.domain.Server;
import com.game.util.domain.User;
import com.game.util.web.Arith;
import com.game.util.web.ConfigUtil;
import com.game.util.web.Constant;
import com.game.util.web.CryptTool;
import com.game.util.web.DateUtil;
import com.game.util.web.Help;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class PublishAction extends BaseAction {
	private static final long serialVersionUID = 8247457736356267335L;
	private BizInfoService bizInfoService;
	private GameService gameService;
	private ServerService serverService;
	private GameKindService gameKindService;
	private List<Game> gameList;
	private Server server;
	private Game game;
	private BizKind bizKind;
	private GameKind gameKind;
	private BizInfo bizInfo;
	private String gameName;
	private Long gameID;
	private Integer typeID;
	private Long serverID;
	private Long bizKindID;
	private Long gameKindID;

	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	private String allowTypes;
	
	private List<File> file;
	private List<String> fileContentType;
	private List<String> fileFileName;

	private String flag = "true";

	private DetailsService detailsService;
	private ProfessionService professionService;
	private Details details;
	private Long detailsID;
	private Long parentID;
	private AccountInfo accountInfo;
	private AttributeInfo attributeInfo;
	private CustomInfo customInfo;
	private List<Profession> professionList;
	private List<Details> detailsList;
	private List<String> tradeType;
	
	/**
	 * 商家—-发布商品——类别
	 */
	public String publish() throws Exception {
		if (gameName != null && !gameName.equals("")) {
			gameList = gameService.findGameByName(gameName, 1);
		}
		
		tradeType = Arrays.asList(ConfigUtil.getInstance(Constant.SYS_CONFIG_PATH).getKeyValue("trade_Type").split(","));
		return "publish";
	}

	/**
	 * 商家—-发布商品--数据
	 */
	public String publishData() throws Exception {
		if (bizKindID == null || typeID == null || gameID == null && serverID == null) {
			return "error";
		}

		if (flag.equals("true")) {
			clearBizInfoSession();// 清除session
		}

		if (serverID != null) {
			server = serverService.getEntity(Server.class, serverID);
			gameKind = gameKindService.getGameKind(bizKindID, server.getArea().getGame().getId());
		} else {
			game = gameService.getEntity(Game.class, gameID);
			gameKind = gameKindService.getGameKind(bizKindID, game.getId());
		}
		if (gameKind.getTradeType() != null && gameKind.getTradeType() == 1) {
			details = detailsService.findDetailsByAccountGroup(gameKind.getId(), 1);
			return "publishAccountData";// 账号交易
		} else if (gameKind.getTradeType() != null && gameKind.getTradeType() == 2) {
			if (Struts2Util.getSession("customInfo") != null) {
				customInfo = (CustomInfo) Struts2Util.getSession().get("customInfo");
			}
			detailsList = detailsService.findDetailsByAttributeGroup(gameKind.getId(), 2);
			return "publishDefinitionData";// 自定义属性
		} else {
			return "publishDefaultData";// 默认
		}
	}

	/**
	 * 商家—-发布商品--数据--自定义属性方式
	 */
	public String publishDefinitionDataSave() throws Exception {
		if (gameKindID == null || typeID == null || gameID == null && serverID == null || bizInfo == null) {
			return ERROR;
		}
		gameKind = gameKindService.getEntity(GameKind.class, gameKindID);
		bizKindID = gameKind.getBizKind().getId();
		if (!VerifyPublishCustom.verify(bizInfo, customInfo, file, fileFileName)) {
			flag = "false";
			return ERROR;
		}

		User user = Struts2Util.getUserSession();
		Random rd = new java.util.Random(System.currentTimeMillis());
		long num = rd.nextLong();
		num = Math.abs(num);
		bizInfo.setSerial("SPBH" + num);// 商品编号
		bizInfo.setBizKind(gameKind.getBizKind());// 类别外键（游戏币、装备）
		if (serverID != null) {// '游戏/服务表表外键'
			bizInfo.setGame(null);
			bizInfo.setServer(serverService.getEntity(Server.class, serverID));
		} else if (gameID != null) {
			bizInfo.setGame(gameService.getEntity(Game.class, gameID));
			bizInfo.setServer(null);
		}
		
		if(ConfigUtil.getInstance(Constant.SYS_CONFIG_PATH).getKeyValue("trade_Type").indexOf(String.valueOf(typeID)) != -1){
			bizInfo.setBuyType(typeID);// '交易类型（1.寄售、2.担保）'
		}else{
			throw new Exception("不存在此交易类型");
		}
		
		
		bizInfo.setOwner(user);// '发布者（用户）'
		bizInfo.setIsBuy(1);// '是否上架(1、上架 0、下架)'
		
		bizInfo.setBizCreTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));// '添加时间'
		bizInfo.setAmount("1");// 每件宝贝数量
		bizInfo.setProportion(bizInfo.getPrice());// '比例'
		bizInfo.setUnit(gameKind.getUnit());// '单位（金、万金）'
		bizInfo.setStartSellTime(bizInfo.getBizCreTime());// '开始销售时间'
		bizInfo.setEndSellTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM, DateUtil.Ds(7)));// '结束销售时间'
		bizInfo.setTradeStart("00:00");// 方便交易时间
		bizInfo.setTradeEnd("23:59");
		detailsList = detailsService.findDetailsByAttributeGroup(gameKind.getId(), 2);// '上架描述信息'
		Map<String, String> map = customInfo.getMap();
		String info = "<dl class=\"zhmm_spxx\">";
		for (Details dts : detailsList) {
			if (dts.getIsUser() == 1 && dts.getParent().getIsUser() == 1) {
				info += "<dt>" + dts.getAttributeName() + ":</dt>";
				for (Details dt : dts.getChild()) {
					if (dt.getIsUser() == 1) {
						if (map.get(dt.getAttributeName()) == null) {
							info += "<dd><em class=\"mintxt\">" + dt.getAttributeName() + ":</em>&nbsp;</dd>";
						} else {
							info += "<dd><em class=\"mintxt\">" + dt.getAttributeName() + ":</em>" + map.get(dt.getAttributeName()) + "</dd>";
						}
					}
				}
			}
		}
		info += "</dl>";
		bizInfo.setInfo(info);
		// 图片信息集 文件上传
		Set<Picture> pictureSet = null;
		
		Help.chkImage(customInfo.getFile(), customInfo.getFileFileName(), Constant.IMAGE_SIZE);//图片验证
		List<String> fileNameList = Help.uploadImageToUserPath(customInfo.getFile(), customInfo.getFileFileName(), user.getUsername() + "/bizInfo");
		Picture picture = null;
		if (!Validator.isEmpty(fileNameList)) {
			pictureSet = new HashSet<Picture>();
			for (String str : fileNameList) {
				picture = new Picture();
				picture.setBizInfo(bizInfo);
				picture.setRoute(str);
				pictureSet.add(picture);
			}
		}
		bizInfo.setPictures(pictureSet);
		// 密码加密
		bizInfo.setPassword(CryptTool.base64Encode(bizInfo.getPassword()));
		if (Validator.isBlank(bizInfo.getCoded_lock())) {
			bizInfo.setCoded_lock(CryptTool.base64Encode(bizInfo.getCoded_lock()));
		}

		// 密保卡上传
		Help.chkImage(file, fileFileName, Constant.IMAGE_SIZE);//图片验证
		List<String> pwdFileNameList = Help.uploadImageToUserPath(file, fileFileName, user.getUsername() + "/bizInfo/secure");
		if (!Validator.isEmpty(pwdFileNameList)) {
			bizInfo.setPwdSrc(pwdFileNameList.get(0));
		} else {
			bizInfo.setPwdSrc(null);
		}
		bizInfoService.createEntity(bizInfo);
		clearBizInfoSession();// 清空Session
		flag = bizInfo.getSerial();
		return "publishDefinitionDataSave";
	}

	/**
	 * 商家—-发布商品--数据--账号交易跳转至第二页
	 */
	public String publishAccountSecondPage() throws Exception {
		if (flag.equals("true")) {
			Struts2Util.removeSession("attributeInfo");
		}
		if (gameKindID == null || typeID == null || gameID == null && serverID == null) {
			flag = "false";
			return ERROR;
		}
		gameKind = gameKindService.getEntity(GameKind.class, gameKindID);
		bizKindID = gameKind.getBizKind().getId();
		Iterator<Details> it = detailsService.getEntity(Details.class, parentID).getChild().iterator();

		if (Struts2Util.getSession("accountInfo") == null || Struts2Util.getSession("attributeInfo") == null) {// 账号资料是否为空 空的话需要验证
			if (!VerifyPublishAccount.verify(accountInfo, it)) {
				flag = "false";
				return ERROR;
			}
		}

		if (gameID == null && serverID != null) {
			gameID = serverService.getEntity(Server.class, serverID).getArea().getGame().getId();
		}
		professionList = professionService.findProfessionByGame(gameID, 1);
		detailsList = detailsService.findDetailsByAttributeGroup(gameKind.getId(), 2);
		if (Struts2Util.getSession("attributeInfo") != null) {
			attributeInfo = (AttributeInfo) Struts2Util.getSession("attributeInfo");
		}
		return "publishAccountSecondPage";
	}

	/**
	 * 商家—-发布商品--数据--保存（账号方式）
	 */
	public String publishAccountDataSave() throws Exception {
		if (gameKindID == null || typeID == null || gameID == null && serverID == null) {
			flag = "false";
			return ERROR;
		}
		gameKind = gameKindService.getEntity(GameKind.class, gameKindID);
		if (!VerifyPublishAttribute.verify(attributeInfo)) {
			flag = "false";
			return ERROR;
		}
		BizInfo bizInfo = new BizInfo();
		User user = Struts2Util.getUserSession();
		Random rd = new java.util.Random(System.currentTimeMillis());
		long num = rd.nextLong();
		num = Math.abs(num);
		bizInfo.setSerial("SPBH" + num);// 商品编号
		bizInfo.setBizKind(gameKind.getBizKind());// 类别外键（游戏币、装备）
		if (serverID != null) {// '游戏/服务表表外键'
			bizInfo.setGame(null);
			bizInfo.setServer(serverService.getEntity(Server.class, serverID));
		} else if (gameID != null) {
			bizInfo.setGame(gameService.getEntity(Game.class, gameID));
			bizInfo.setServer(null);
		}
		if(ConfigUtil.getInstance(Constant.SYS_CONFIG_PATH).getKeyValue("trade_Type").indexOf(String.valueOf(typeID)) != -1){
			bizInfo.setBuyType(typeID);// '交易类型（1.寄售、2.担保）'
		}else{
			throw new Exception("不存在此交易类型");
		}
		bizInfo.setStock("1");// '库存量'
		bizInfo.setPrice(attributeInfo.getPrice());// '单价'
		bizInfo.setOwner(user);// '发布者（用户）'
		bizInfo.setIsBuy(1);// '是否上架(1、上架 0、下架)'
		bizInfo.setBizCreTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));// '添加时间'
		bizInfo.setAmount("1");// 每件宝贝数量
		bizInfo.setProportion(bizInfo.getPrice());// '比例'
		bizInfo.setUnit(gameKind.getUnit());// '单位（金、万金）'
		bizInfo.setSellModel(3);// 交易方式（1.游戏中当面 2.邮寄 3,账号交易 4,自定义属性交易）
		bizInfo.setSite("账号交易");// 交易地点
		bizInfo.setStartSellTime(bizInfo.getBizCreTime());// '开始销售时间'
		bizInfo.setEndSellTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM, DateUtil.Ds(7)));// '结束销售时间'
		bizInfo.setTradeStart("00:00");// 方便交易时间
		bizInfo.setTradeEnd("23:59");
		bizInfo.setQq(attributeInfo.getQq());// 联系QQ
		bizInfo.setPhoneNum(attributeInfo.getPhoneNum());// 联系电话
		String title = "【" + attributeInfo.getProfessionName() + " " + attributeInfo.getSex() + " " + attributeInfo.getGrade() + "】";
		title += attributeInfo.getTitle2();
		bizInfo.setTitle(title);// '标题'
		accountInfo = (AccountInfo) Struts2Util.getSession("accountInfo");
		bizInfo.setAccount(accountInfo.getAccount());// 账户
		bizInfo.setAccountInfo(accountInfo.getContent());// 其他账号信息
		bizInfo.setPassword(CryptTool.base64Encode(accountInfo.getPassword()));// 密码
		detailsList = detailsService.findDetailsByAttributeGroup(gameKind.getId(), 2);// '上架描述信息'
		Map<String, String> map = attributeInfo.getMap();
		String info = "<dl class=\"zhmm_spxx\">";
		for (Details dts : detailsList) {
			if (dts.getIsUser() == 1 && dts.getParent().getIsUser() == 1) {
				info += "<dt>" + dts.getAttributeName() + ":</dt>";
				for (Details dt : dts.getChild()) {
					if (dt.getIsUser() == 1) {
						if (map.get(dt.getAttributeName()) == null) {
							info += "<dd><em class=\"mintxt\">" + dt.getAttributeName() + ":</em>&nbsp;</dd>";
						} else {
							info += "<dd><em class=\"mintxt\">" + dt.getAttributeName() + ":</em>" + map.get(dt.getAttributeName()) + "</dd>";
						}
					}
				}
			}
		}
		info += "</dl>";
		bizInfo.setInfo(info);
		// 图片信息集 文件上传
		Set<Picture> pictureSet = null;
		Help.chkImage(attributeInfo.getFile(), attributeInfo.getFileFileName(), Constant.IMAGE_SIZE);//图片验证
		List<String> fileNameList = Help.uploadImageToUserPath(attributeInfo.getFile(), attributeInfo.getFileFileName(), user.getUsername() + "/bizInfo");
		Picture picture = null;
		if (!Validator.isEmpty(fileNameList)) {
			pictureSet = new HashSet<Picture>();
			for (String str : fileNameList) {
				picture = new Picture();
				picture.setBizInfo(bizInfo);
				picture.setRoute(str);
				pictureSet.add(picture);
			}
		}
		bizInfo.setPictures(pictureSet);

		// 身份证上传--将已经上传的文件从临时目录中移出来
		Set<Identity> identitySet = null;
		Identity identity = null;
		fileNameList = Help.moveFile(accountInfo.getFileFileName(), "/" + user.getUsername() + "/bizInfo/identity/temp/", "/" + user.getUsername() + "/bizInfo/identity/");
		if (!Validator.isEmpty(fileNameList)) {
			identitySet = new HashSet<Identity>();
			for (String str : fileNameList) {
				identity = new Identity();
				identity.setBizInfo(bizInfo);
				identity.setRoute(str);
				identitySet.add(identity);
			}
		}
		bizInfo.setIdentities(identitySet);
		bizInfoService.createEntity(bizInfo);
		clearBizInfoSession();// 清空Session
		flag = bizInfo.getSerial();
		return "publishAccountDataSave";
	}

	/**
	 * 商家—-发布商品--数据--保存（默认方式）
	 */
	public String publishDefaultDataSave() throws Exception {
		if (gameKindID == null || typeID == null || gameID == null && serverID == null) {
			flag = "false";
			return ERROR;
		}
		gameKind = gameKindService.getEntity(GameKind.class, gameKindID);
		bizKind = gameKind.getBizKind();
		bizInfo.setBizKind(bizKind);
		if (serverID != null) {
			bizInfo.setServer(serverService.getEntity(Server.class, serverID));
		} else if (gameID != null) {
			bizInfo.setGame(gameService.getEntity(Game.class, gameID));
		}
		if (!VerifyPublish.verify(bizInfo)) {
			flag = "false";
			bizKindID = bizKind.getId();
			return ERROR;
		}
		clearBizInfoSession();// 清空Session
		// 文件上传
		User user = Struts2Util.getUserSession();
		Help.chkImage(upload, uploadFileName, Constant.IMAGE_SIZE);//图片验证
		List<String> fileNameList = Help.uploadImageToUserPath(upload, uploadFileName, user.getUsername() + "/bizInfo");
		// 密保卡上传
		Help.chkImage(file, fileFileName, Constant.IMAGE_SIZE);//图片验证
		List<String> pwdFileNameList = Help.uploadImageToUserPath(file, fileFileName, user.getUsername() + "/bizInfo/secure");
		Random rd = new java.util.Random(System.currentTimeMillis());
		long num = rd.nextLong();
		num = Math.abs(num);
		bizInfo.setSerial("SPBH" + num);
		// 将编辑器中的临时图片移到正式文件目录下
		bizInfo.setInfo(Help.moveKindEditorImage(bizInfo.getInfo(), "bizInfo"));
		bizInfo.setBizCreTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
		if(ConfigUtil.getInstance(Constant.SYS_CONFIG_PATH).getKeyValue("trade_Type").indexOf(String.valueOf(typeID)) != -1){
			bizInfo.setBuyType(typeID);// '交易类型（1.寄售、2.担保）'
		}else{
			throw new Exception("不存在此交易类型");
		}
		bizInfo.setStartSellTime(bizInfo.getBizCreTime());
		bizInfo.setEndSellTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM, DateUtil.Ds(bizInfo.getTerm_of_validity())));
		bizInfo.setIsBuy(1);
		bizInfo.setOwner(user);
		bizInfo.setPrice(new BigDecimal(bizInfo.getPrice()).setScale(2, BigDecimal.ROUND_DOWN).toString());
		double amount = Double.parseDouble(bizInfo.getAmount());
		double unit_price = Double.parseDouble(bizInfo.getPrice());
		double proportion = Arith.div(unit_price, amount);
		bizInfo.setProportion(Arith.intercept(proportion, 3) + "");
		bizInfo.setUnit(gameKind.getUnit());
		Set<Picture> pictureSet = null;
		Picture picture = null;
		if (!Validator.isEmpty(fileNameList)) {
			pictureSet = new HashSet<Picture>();
			for (String str : fileNameList) {
				picture = new Picture();
				picture.setBizInfo(bizInfo);
				picture.setRoute(str);
				pictureSet.add(picture);
			}
		}
		bizInfo.setPictures(pictureSet);
		// base64加密
		bizInfo.setPassword(CryptTool.base64Encode(bizInfo.getPassword()));
		bizInfo.setCoded_lock(CryptTool.base64Encode(bizInfo.getCoded_lock()));
		if (!Validator.isEmpty(pwdFileNameList)) {
			bizInfo.setPwdSrc(pwdFileNameList.get(0));
		} else {
			bizInfo.setPwdSrc(null);
		}
		bizInfoService.createEntity(bizInfo);
		flag = bizInfo.getSerial();
		return "publishDefaultDataSave";
	}

	/**
	 * 商家—-发布商品--成功
	 */
	public String publishSuccess() throws Exception {
		User user = Struts2Util.getUserSession();
		if (user != null && flag != null && flag.length() > 0) {
			bizInfo = bizInfoService.getBizInfo(user.getId(), flag);
		}
		return SUCCESS;
	}

	/**
	 * 清楚挂卖信息时候的临时session
	 */
	private void clearBizInfoSession() {
		Struts2Util.getSession().remove("publishErrorInfo");
		Struts2Util.getSession().remove("bizInfo");
		Struts2Util.getSession().remove("accountInfo");
		Struts2Util.getSession().remove("attributeInfo");
		Struts2Util.getSession().remove("customInfo");
	}
	
	// *************上传图片**************
	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	// file
	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}
	// end
	// ****************************

	public BizInfoService getBizInfoService() {
		return bizInfoService;
	}

	public void setBizInfoService(BizInfoService bizInfoService) {
		this.bizInfoService = bizInfoService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public ServerService getServerService() {
		return serverService;
	}

	public void setServerService(ServerService serverService) {
		this.serverService = serverService;
	}

	public GameKindService getGameKindService() {
		return gameKindService;
	}

	public void setGameKindService(GameKindService gameKindService) {
		this.gameKindService = gameKindService;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public BizKind getBizKind() {
		return bizKind;
	}

	public void setBizKind(BizKind bizKind) {
		this.bizKind = bizKind;
	}

	public GameKind getGameKind() {
		return gameKind;
	}

	public void setGameKind(GameKind gameKind) {
		this.gameKind = gameKind;
	}

	public BizInfo getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(BizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Long getGameID() {
		return gameID;
	}

	public void setGameID(Long gameID) {
		this.gameID = gameID;
	}

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	public Long getServerID() {
		return serverID;
	}

	public void setServerID(Long serverID) {
		this.serverID = serverID;
	}

	public Long getBizKindID() {
		return bizKindID;
	}

	public void setBizKindID(Long bizKindID) {
		this.bizKindID = bizKindID;
	}

	public Long getGameKindID() {
		return gameKindID;
	}

	public void setGameKindID(Long gameKindID) {
		this.gameKindID = gameKindID;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public DetailsService getDetailsService() {
		return detailsService;
	}

	public void setDetailsService(DetailsService detailsService) {
		this.detailsService = detailsService;
	}

	public ProfessionService getProfessionService() {
		return professionService;
	}

	public void setProfessionService(ProfessionService professionService) {
		this.professionService = professionService;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public Long getDetailsID() {
		return detailsID;
	}

	public void setDetailsID(Long detailsID) {
		this.detailsID = detailsID;
	}

	public Long getParentID() {
		return parentID;
	}

	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public AttributeInfo getAttributeInfo() {
		return attributeInfo;
	}

	public void setAttributeInfo(AttributeInfo attributeInfo) {
		this.attributeInfo = attributeInfo;
	}

	public CustomInfo getCustomInfo() {
		return customInfo;
	}

	public void setCustomInfo(CustomInfo customInfo) {
		this.customInfo = customInfo;
	}

	public List<Profession> getProfessionList() {
		return professionList;
	}

	public void setProfessionList(List<Profession> professionList) {
		this.professionList = professionList;
	}

	public List<Details> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<Details> detailsList) {
		this.detailsList = detailsList;
	}

	public List<String> getTradeType() {
		return tradeType;
	}

	public void setTradeType(List<String> tradeType) {
		this.tradeType = tradeType;
	}
	
	
}
