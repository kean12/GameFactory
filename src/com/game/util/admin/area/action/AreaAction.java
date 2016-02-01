package com.game.util.admin.area.action;

import java.util.List;

import com.game.util.admin.area.services.AreaService;
import com.game.util.admin.game.services.GameService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Area;
import com.game.util.domain.Game;
import com.game.util.web.Page;

public class AreaAction extends BaseAction {
	private static final long serialVersionUID = 6063825174485774310L;
	private AreaService areaService;
	private GameService gameService;
	private Area area;
	private Long areaID;
	private Long gameID;
	private List<Area> areaList;
	private Page<Area> page;
	private String areaName;

	/**
	 * @name 获得大类
	 */
	public String listArea() throws Exception {
		if (gameID == null) {
			return "err";
		}
		page = areaService.searchAreaByGame(areaName, gameID, 20, super.getGoPage());
		areaList = page.getResultlist();
		return "listarea";
	}

	/**
	 * @name 进入添加更改页面
	 */
	public String add() throws Exception {
		if (areaID != null) {
			area = areaService.getEntity(Area.class, areaID);
			gameID = area.getGame().getId();
		}
		return "add";
	}

	/**
	 * @name 保存菜单
	 */
	public String save() throws Exception {
		if (gameID == null) {
			return "err";
		}
		Area tmparea = areaService.findAreaByName(area.getAreaName(), gameID);
		if (area.getId() != null) {
			Area tmp = areaService.getEntity(Area.class, area.getId());
			if (tmparea == null || tmp.getAreaName().equals(area.getAreaName())) {
				tmp.setAreaName(area.getAreaName());
				tmp.setState(area.getState());
				areaService.updateEntity(tmp);
			} else {
				throw new Exception("您所添加的分区已存在！");
			}
		} else {
			if (tmparea != null) {
				throw new Exception("您所添加的分区已存在！");
			}
			area.setId(areaService.getAreaMaxID(gameID));
			area.setGame(gameService.getEntity(Game.class, gameID));
			areaService.createEntity(area);
		}
		return "savearea";
	}

	// *******get/set方法*********
	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Long getAreaID() {
		return areaID;
	}

	public void setAreaID(Long areaID) {
		this.areaID = areaID;
	}

	public Long getGameID() {
		return gameID;
	}

	public void setGameID(Long gameID) {
		this.gameID = gameID;
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public Page<Area> getPage() {
		return page;
	}

	public void setPage(Page<Area> page) {
		this.page = page;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
