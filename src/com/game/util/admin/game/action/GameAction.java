package com.game.util.admin.game.action;

import java.util.List;

import com.game.util.admin.game.services.GameService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Game;
import com.game.util.web.Help;
import com.game.util.web.Page;
import com.game.util.web.PinYin;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class GameAction extends BaseAction {
	private static final long serialVersionUID = 8254214126793780353L;
	private GameService gameService;
	private Game game;
	private Long gameID;
	private List<Game> gameList;
	private Page<Game> page;
	private String gameName;

	/**
	 * @name 获得大类
	 */
	public String listGame() throws Exception {
		if (!Validator.isBlank(gameName) && gameName.equals("请输入游戏名称"))
			gameName = "";
		page = gameService.searchGame(gameName, 20, super.getGoPage());
		gameList = page.getResultlist();
		return "listgame";
	}

	/**
	 * @name 进入添加更改页面
	 */
	public String add() throws Exception {
		if (gameID != null) {
			game = gameService.getEntity(Game.class, gameID);
		}
		return "add";
	}

	/**
	 * @name 保存菜单
	 */
	public String save() throws Exception {
		Game tmpgame = gameService.findGameByName(game.getGameName());
		if (game.getId() != null) {
			Game tmp = gameService.getEntity(Game.class, game.getId());
			if (tmpgame == null || tmp.getGameName().equals(game.getGameName())) {
				tmp.setGameName(game.getGameName());
				tmp.setCompany(game.getCompany());
				tmp.setGameHot(game.getGameHot());
				tmp.setState(game.getState());
				tmp.setGameIndex(PinYin.getPYString(game.getGameName()));
				tmp.setPinyin(PinYin.getFullSpell(game.getGameName()));
				tmp.setGameNum(String.format("%05d", game.getId()));
				gameService.updateEntity(tmp);
			} else {
				throw new Exception("您所添加的游戏已存在！");
			}
		} else {
			if (tmpgame != null) {
				throw new Exception("您所添加的游戏已存在！");
			}
			game.setId(gameService.getGameMaxID());
			game.setGameIndex(PinYin.getPYString(game.getGameName()));
			game.setPinyin(PinYin.getFullSpell(game.getGameName()));
			game.setGameNum(String.format("%05d", game.getId()));
			gameService.createEntity(game);
		}
		return "savegame";
	}

	/**
	 * @name 删除菜单
	 */
	public String delete() throws Exception {
		if (gameID != null) {
			game = gameService.getEntity(Game.class, gameID);
			gameService.removeEntity(game);
		} else {
			return listGame();
		}
		return "savegame";
	}

	// 将游戏数据更新到js文件
	public String updateList() throws Exception {
		return "updateList";
	}

	public String saveGameList() throws Exception {
		String path = Struts2Util.getRealPath("");
		Help.updateGamePage(path, gameService);
		return "saveGameList";
	}

	// *******get/set方法*********

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Long getGameID() {
		return gameID;
	}

	public void setGameID(Long gameID) {
		this.gameID = gameID;
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

}
