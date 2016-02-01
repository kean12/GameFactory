package com.game.util.admin.game.action;

import java.util.List;

import com.game.util.admin.game.services.GameService;
import com.game.util.admin.game.services.ProfessionService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Game;
import com.game.util.domain.Profession;

public class ProfessionAction extends BaseAction {

	private static final long serialVersionUID = -8533601078911877540L;
	private GameService gameService;
	private ProfessionService professionService;
	private Game game;
	private Profession profession;
	private Long gameID;
	private Long professionID;

	private List<Profession> professionList;

	/**
	 * @name 某游戏职业列表
	 */
	public String listProfession() throws Exception {
		professionList = professionService.findProfessionByGame(gameID, null);
		return "listProfession";
	}

	/**
	 * @name 添加某游戏职业
	 */
	public String addProfession() throws Exception {
		game = gameService.getEntity(Game.class, gameID);
		if (professionID != null) {
			profession = professionService.getEntity(Profession.class, professionID);
		}
		return "addProfession";
	}

	/**
	 * @name 保存某游戏职业
	 */
	public String saveProfession() throws Exception {
		if (profession != null) {
			profession.setGame(gameService.getEntity(Game.class, gameID));
			if (profession.getId() != null) {
				professionService.updateEntity(profession);
			} else {
				professionService.createEntity(profession);
			}
		}
		return "saveProfession";
	}

	/**
	 * @name 删除某游戏职业
	 */
	public String deleteProfession() throws Exception {
		if (professionID != null) {
			professionService.removeProfession(professionID);
		}

		return "saveProfession";
	}

	// ***********get/set*************
	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public ProfessionService getProfessionService() {
		return professionService;
	}

	public void setProfessionService(ProfessionService professionService) {
		this.professionService = professionService;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public Long getGameID() {
		return gameID;
	}

	public void setGameID(Long gameID) {
		this.gameID = gameID;
	}

	public Long getProfessionID() {
		return professionID;
	}

	public void setProfessionID(Long professionID) {
		this.professionID = professionID;
	}

	public List<Profession> getProfessionList() {
		return professionList;
	}

	public void setProfessionList(List<Profession> professionList) {
		this.professionList = professionList;
	}

}
