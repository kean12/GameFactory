package com.game.bizinfo.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.game.assess.services.AssessService;
import com.game.bizinfo.services.BizInfoService;
import com.game.bizinfo.services.PictureService;
import com.game.util.admin.area.services.AreaService;
import com.game.util.admin.bizkind.services.BizKindService;
import com.game.util.admin.game.services.GameService;
import com.game.util.admin.server.services.ServerService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Area;
import com.game.util.domain.BizInfo;
import com.game.util.domain.BizKind;
import com.game.util.domain.Game;
import com.game.util.domain.Picture;
import com.game.util.domain.Server;
import com.game.util.web.Arith;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class SearchAction extends BaseAction {
	private static final long serialVersionUID = -9011212672413256775L;
	private BizInfoService bizInfoService;
	private PictureService pictureService;
	private GameService gameService;
	private AreaService areaService;
	private ServerService serverService;
	private AssessService assessService;
	private BizKindService bizKindService;
	private List<BizInfo> bizInfoList;
	private List<Picture> pictureList;
	private Page<BizInfo> page;
	private Game game;
	private Area area;
	private Server server;
	private BizKind bizKind;
	private String searchContent;
	private Long gameID;
	private Long areaID;
	private Long serverID;
	private Long bizKindID;
	private BizInfo bizInfo;
	private Long bizInfoID;
	private Long userID;
	private Long pictureID;
	private Long ownerID;

	private String order_by; // 排列字段
	private String sort; // 逆序
	private int size = 20; // 每页数量

	private String sellerPositiveRatio; // 好评率
	private int isSellerPositiveRatio;
	private String buyerPositiveRatio; // 好评率
	private int isBuyerPositiveRatio;

	/**
	 * 商品搜索页面
	 */
	public String search() throws Exception {
		return "search";
	}

	/**
	 * 商品浏览页面
	 */
	public String browse() throws Exception {
		if (gameID != null) {
			game = gameService.getEntity(Game.class, gameID);
		}
		if (areaID != null) {
			area = areaService.getEntity(Area.class, areaID);
		}
		if (serverID != null) {
			server = serverService.getEntity(Server.class, serverID);
		}
		if (bizKindID != null) {
			bizKind = bizKindService.getEntity(BizKind.class, bizKindID);
		}
		page = bizInfoService.findBizInfoByState(ownerID, gameID, areaID, serverID, bizKindID, searchContent, 1, DateUtil.nowDate(Constant.HH_MM), order_by, sort, size, super.getGoPage());
		bizInfoList = page.getResultlist();
		return "browse";
	}

	/**
	 * 商品详细介绍页面
	 */
	public String detail() throws Exception {
		bizInfo = bizInfoService.getBizInfo(userID, bizInfoID);
		if (bizInfo != null) {
			pictureList = pictureService.findPictureByBizInfo(bizInfo.getId());
			List<Object[]> seller = assessService.getAssessByPassive(bizInfo.getOwner().getId(), 1, null);
			List<Object[]> buyer = assessService.getAssessByPassive(bizInfo.getOwner().getId(), 0, null);
			int sellerHp = 0; // 作为卖家好评数
			int sellerZp = 0; // 作为卖家中评数
			int sellerCp = 0; // 作为卖家差评数
			int buyerHp = 0; // 作为买家好评数
			int buyerZp = 0; // 作为买家中评数
			int buyerCp = 0; // 作为买家差评数
			for (int i = 0; i < seller.size(); i++) {
				Object[] obj = (Object[]) seller.get(i);
				if (obj[1].equals(1)) {
					sellerHp = (Integer.parseInt(obj[0].toString()));
				} else if (obj[1].equals(0)) {
					sellerZp = (Integer.parseInt(obj[0].toString()));
				} else {
					sellerCp = (Integer.parseInt(obj[0].toString()));
				}
			}
			for (int i = 0; i < buyer.size(); i++) {
				Object[] obj = (Object[]) buyer.get(i);
				if (obj[1].equals(1)) {
					buyerHp = (Integer.parseInt(obj[0].toString()));
				} else if (obj[1].equals(0)) {
					buyerZp = (Integer.parseInt(obj[0].toString()));
				} else {
					buyerCp = (Integer.parseInt(obj[0].toString()));
				}
			}
			NumberFormat formatter = new DecimalFormat("0.00");
			if (sellerHp + sellerZp + sellerCp != 0) {
				Double x = new Double(Arith.intercept((double) sellerHp / (sellerHp + sellerZp + sellerCp) * 100, 2));
				sellerPositiveRatio = formatter.format(x);
				isSellerPositiveRatio = 0;
			} else {
				isSellerPositiveRatio = -1;
			}
			if (buyerHp + buyerZp + buyerCp != 0) {
				Double x = new Double(Arith.intercept((double) buyerHp / (buyerHp + buyerZp + buyerCp) * 100, 2));
				buyerPositiveRatio = formatter.format(x);
				isBuyerPositiveRatio = 0;
			} else {
				isBuyerPositiveRatio = -1;
			}
		}
		return "detail";
	}

	/**
	 * 商品详细介绍--图片
	 */
	public String picture() throws Exception {
		pictureList = pictureService.findPictureByBizInfo(bizInfoID);
		return "picture";
	}

	// *************************************************************************
	// *************************************************************************
	public BizInfoService getBizInfoService() {
		return bizInfoService;
	}

	public void setBizInfoService(BizInfoService bizInfoService) {
		this.bizInfoService = bizInfoService;
	}

	public BizKindService getBizKindService() {
		return bizKindService;
	}

	public void setBizKindService(BizKindService bizKindService) {
		this.bizKindService = bizKindService;
	}

	public List<BizInfo> getBizInfoList() {
		return bizInfoList;
	}

	public void setBizInfoList(List<BizInfo> bizInfoList) {
		this.bizInfoList = bizInfoList;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) throws UnsupportedEncodingException {
		if (!Validator.isBlank(searchContent)) {
			this.searchContent = URLDecoder.decode(searchContent.trim(),
					"utf-8");
		} else {
			this.searchContent = null;
		}
	}

	public Long getGameID() {
		return gameID;
	}

	public void setGameID(Long gameID) {
		this.gameID = gameID;
	}

	public Long getAreaID() {
		return areaID;
	}

	public void setAreaID(Long areaID) {
		this.areaID = areaID;
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

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public ServerService getServerService() {
		return serverService;
	}

	public void setServerService(ServerService serverService) {
		this.serverService = serverService;
	}

	public Page<BizInfo> getPage() {
		return page;
	}

	public void setPage(Page<BizInfo> page) {
		this.page = page;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public BizKind getBizKind() {
		return bizKind;
	}

	public void setBizKind(BizKind bizKind) {
		this.bizKind = bizKind;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String orderBy) {
		if (!Validator.isBlank(orderBy)) {
			this.order_by = orderBy.trim();
		} else {
			this.order_by = null;
		}
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		if (!Validator.isBlank(sort)) {
			this.sort = sort.trim();
		} else {
			this.sort = null;
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		if (size != 20 && size != 40 && size != 80) {
			this.size = 20;
		} else {
			this.size = size;
		}
	}

	public BizInfo getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(BizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}

	public Long getBizInfoID() {
		return bizInfoID;
	}

	public void setBizInfoID(Long bizInfoID) {
		this.bizInfoID = bizInfoID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public AssessService getAssessService() {
		return assessService;
	}

	public void setAssessService(AssessService assessService) {
		this.assessService = assessService;
	}

	public String getSellerPositiveRatio() {
		return sellerPositiveRatio;
	}

	public void setSellerPositiveRatio(String sellerPositiveRatio) {
		this.sellerPositiveRatio = sellerPositiveRatio;
	}

	public int getIsSellerPositiveRatio() {
		return isSellerPositiveRatio;
	}

	public void setIsSellerPositiveRatio(int isSellerPositiveRatio) {
		this.isSellerPositiveRatio = isSellerPositiveRatio;
	}

	public String getBuyerPositiveRatio() {
		return buyerPositiveRatio;
	}

	public void setBuyerPositiveRatio(String buyerPositiveRatio) {
		this.buyerPositiveRatio = buyerPositiveRatio;
	}

	public int getIsBuyerPositiveRatio() {
		return isBuyerPositiveRatio;
	}

	public void setIsBuyerPositiveRatio(int isBuyerPositiveRatio) {
		this.isBuyerPositiveRatio = isBuyerPositiveRatio;
	}

	public List<Picture> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<Picture> pictureList) {
		this.pictureList = pictureList;
	}

	public Long getPictureID() {
		return pictureID;
	}

	public void setPictureID(Long pictureID) {
		this.pictureID = pictureID;
	}

	public Long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(Long ownerID) {
		this.ownerID = ownerID;
	}

	public PictureService getPictureService() {
		return pictureService;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

}
