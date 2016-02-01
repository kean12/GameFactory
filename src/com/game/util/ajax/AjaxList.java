package com.game.util.ajax;

import java.util.List;

import com.game.util.admin.area.services.AreaService;
import com.game.util.admin.bizkind.services.BizKindService;
import com.game.util.admin.game.services.GameService;
import com.game.util.admin.server.services.ServerService;
import com.game.util.domain.Area;
import com.game.util.domain.BizKind;
import com.game.util.domain.Game;
import com.game.util.domain.Server;
import com.game.util.web.DWRUtil;

public class AjaxList {

	public List<Game> gameList(String gameIndex) throws Exception {
		GameService gameService = (GameService) DWRUtil.getBean("gameService");
		if (gameIndex != null && !gameIndex.equals("")) {
			return gameService.findGameByIndex(gameIndex, 1);
		}
		return null;
	}

	public List<Area> areaList(Long gameID) throws Exception {
		AreaService areaService = (AreaService) DWRUtil.getBean("areaService");
		if (gameID != null) {
			return areaService.finfAreaByGame(gameID, 1);
		}
		return null;
	}

	public List<Server> serverList(Long areaID) throws Exception {
		ServerService serverService = (ServerService) DWRUtil.getBean("serverService");
		if (areaID != null) {
			return serverService.findServerByArea(areaID, 1);
		}
		return null;
	}

	public List<BizKind> bizKindList(Long gameID) throws Exception {
		BizKindService bizKindService = (BizKindService) DWRUtil.getBean("bizKindService");
		if (gameID != null) {
			return bizKindService.findGameKindByGame(gameID, 1);
		}
		return null;
	}

}
