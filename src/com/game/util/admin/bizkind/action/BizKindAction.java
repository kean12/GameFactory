package com.game.util.admin.bizkind.action;

import java.util.ArrayList;
import java.util.List;

import com.game.util.admin.bizkind.services.BizKindService;
import com.game.util.admin.bizkind.services.GameKindService;
import com.game.util.admin.game.services.GameService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.BizKind;
import com.game.util.domain.Game;
import com.game.util.domain.GameKind;
import com.game.util.web.Help;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class BizKindAction extends BaseAction {
	private static final long serialVersionUID = -4645019621284415424L;
	private GameService gameService;
	private BizKindService bizKindService;
	private BizKind bizKind;
	private Long bizKindID;
	private List<BizKind> bizKindList;
	private GameKindService gameKindService;

	/**
	 * @name 分类列表
	 */
	public String listBizKind() throws Exception {
		bizKindList = bizKindService.findAllBizKind();
		return "listbizKind";
	}

	/**
	 * @name 进入添加更改页面
	 */
	public String add() throws Exception {
		if (bizKindID != null) {
			bizKind = bizKindService.getEntity(BizKind.class, bizKindID);
		}
		return "add";
	}

	/**
	 * @name 保存分类
	 */
	public String save() throws Exception {
		if (bizKind.getId() != null) {
			bizKindService.updateEntity(bizKind);
		} else {
			bizKindService.createEntity(bizKind);
		}
		if (bizKind.getToleration() == 1) {
			List<Game> gameList = gameService.findGameNotSetBizKind(bizKind.getId());
			if (!Validator.isEmpty(gameList)) {
				List<GameKind> gameKindList = new ArrayList<GameKind>();
				GameKind gameKind = null;
				for (Game game : gameList) {
					gameKind = new GameKind();
					gameKind.setGame(game);
					gameKind.setBizKind(bizKind);
					gameKind.setUnit(bizKind.getUnit());
					gameKind.setTradeType(bizKind.getTradeType());
					gameKindList.add(gameKind);
				}
				gameKindService.saveorupdatecoll(gameKindList);
			}
		}
		saveBizKindList();
		return "savebizKind";
	}

	/**
	 * @name 删除一个分类
	 */
	public String delete() throws Exception {
		if (bizKindID != null) {
			bizKindService.removeBizKind(bizKindID);
			saveBizKindList();
		}
		return "savebizKind";
	}

	// 将分类解析到js
	public void saveBizKindList() throws Exception {
		String path = Struts2Util.getRealPath("");
		Help.updateBizKind(path, bizKindService);
	}

	// *******get/set方法*********
	public BizKindService getBizKindService() {
		return bizKindService;
	}

	public void setBizKindService(BizKindService bizKindService) {
		this.bizKindService = bizKindService;
	}

	public BizKind getBizKind() {
		return bizKind;
	}

	public void setBizKind(BizKind bizKind) {
		this.bizKind = bizKind;
	}

	public Long getBizKindID() {
		return bizKindID;
	}

	public void setBizKindID(Long bizKindID) {
		this.bizKindID = bizKindID;
	}

	public List<BizKind> getBizKindList() {
		return bizKindList;
	}

	public void setBizKindList(List<BizKind> bizKindList) {
		this.bizKindList = bizKindList;
	}

	public GameKindService getGameKindService() {
		return gameKindService;
	}

	public void setGameKindService(GameKindService gameKindService) {
		this.gameKindService = gameKindService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

}
