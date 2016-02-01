package com.game.util.admin.bizkind.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.game.util.admin.bizkind.services.BizKindService;
import com.game.util.admin.bizkind.services.GameKindService;
import com.game.util.admin.game.services.GameService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.BizKind;
import com.game.util.domain.Game;
import com.game.util.domain.GameKind;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class GameKindAction extends BaseAction {
	private static final long serialVersionUID = 5608002939644848163L;
	private GameService gameService;
	private BizKindService bizKindService;
	private GameKindService gameKindService;
	private BizKind bizKind;
	private GameKind gameKind;
	private Long bizKindID;
	private Long gameKindID;
	private Long gameID;
	private List<BizKind> bizKindList;
	private List<GameKind> gameKindList;
	private List<Game> gameList;
	private Page<Game> page;
	private List<Long> idKey;

	private String gameName;
	private String unit;
	private Integer type;

	private List<BizKind> tempList;

	/**
	 * @name 分类列表
	 */
	public String listGame() throws Exception {
		if (!Validator.isBlank(gameName) && gameName.equals("请输入游戏名称"))
			gameName = null;
		page = gameService.searchGame(gameName, 20, super.getGoPage());
		gameList = page.getResultlist();
		return "listGame";
	}

	/**
	 * @name 分类列表
	 */
	public String listGameKind() throws Exception {
		bizKindList = bizKindService.findBizKindByIsUse(1);
		gameKindList = gameKindService.findGameKindByGame(gameID);
		if (!Validator.isEmpty(gameKindList)) {
			tempList = new ArrayList<BizKind>();
			Iterator<GameKind> it = gameKindList.iterator();
			while (it.hasNext()) {
				tempList.add(it.next().getBizKind());
			}
		}
		return "listGameKind";
	}

	public String addGameKind() throws Exception {
		List<Long> list = new ArrayList<Long>();
		List<GameKind> tmpList = gameKindService.findGameKindByGame(gameID);
		if (idKey == null || idKey.size() == 0) {
			if (!Validator.isEmpty(tmpList)) {
				gameKindService.removeAllEntity(tmpList);
			}
		} else {
			for (GameKind kind : tmpList) {
				bizKindID = kind.getBizKind().getId();
				if (idKey.contains(bizKindID)) {
					list.add(bizKindID);
				} else {
					gameKindService.removeEntity(kind);
				}
			}

			gameKindList = new ArrayList<GameKind>();
			Game game = gameService.getEntity(Game.class, gameID);
			for (Long id : idKey) {
				if (!list.contains(id)) {
					gameKind = new GameKind();
					gameKind.setBizKind(bizKindService.getEntity(BizKind.class, id));
					gameKind.setGame(game);
					gameKind.setUnit(gameKind.getBizKind().getUnit());
					gameKind.setTradeType(gameKind.getBizKind().getTradeType());
					gameKindList.add(gameKind);
				}
			}

			if (!Validator.isEmpty(gameKindList)) {
				gameKindService.saveorupdatecoll(gameKindList);
			}
		}
		return "add";
	}

	/**
	 * @name 修改单位
	 */
	public String editUnit() throws Exception {
		if (!Validator.isBlank(unit) && gameID != null && gameKindID != null) {
			gameKind = gameKindService.getEntity(GameKind.class, gameKindID);
			gameKind.setUnit(unit.trim());
			gameKindService.updateEntity(gameKind);
		}
		return "add";
	}

	/**
	 * @name 修改挂卖方式
	 */
	public String editTradeType() throws Exception {
		if (type != null && gameID != null && gameKindID != null) {
			gameKind = gameKindService.getEntity(GameKind.class, gameKindID);
			gameKind.setTradeType(type);
			gameKindService.updateEntity(gameKind);
		}
		return "add";
	}

	/**
	 * @name 保存分类
	 */
	public String save() throws Exception {
		return "savebizKind";
	}

	/**
	 * @name 删除一个分类
	 */
	public String delete() throws Exception {
		return "savebizKind";
	}

	// *******get/set方法*********
	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public BizKindService getBizKindService() {
		return bizKindService;
	}

	public void setBizKindService(BizKindService bizKindService) {
		this.bizKindService = bizKindService;
	}

	public GameKindService getGameKindService() {
		return gameKindService;
	}

	public void setGameKindService(GameKindService gameKindService) {
		this.gameKindService = gameKindService;
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

	public List<BizKind> getBizKindList() {
		return bizKindList;
	}

	public void setBizKindList(List<BizKind> bizKindList) {
		this.bizKindList = bizKindList;
	}

	public List<GameKind> getGameKindList() {
		return gameKindList;
	}

	public void setGameKindList(List<GameKind> gameKindList) {
		this.gameKindList = gameKindList;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	public Page<Game> getPage() {
		return page;
	}

	public void setPage(Page<Game> page) {
		this.page = page;
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

	public List<BizKind> getTempList() {
		return tempList;
	}

	public void setTempList(List<BizKind> tempList) {
		this.tempList = tempList;
	}

	public List<Long> getIdKey() {
		return idKey;
	}

	public void setIdKey(List<Long> idKey) {
		this.idKey = idKey;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
