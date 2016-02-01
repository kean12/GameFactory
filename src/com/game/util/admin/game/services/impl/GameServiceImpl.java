package com.game.util.admin.game.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.game.util.admin.game.services.GameService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Game;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class GameServiceImpl extends GenericServiceImpl<Game, Long> implements GameService {

	public void removeGame(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Game.class, id));
	}

	public Page<Game> searchGame(String gameName, int size, int goPage) throws Exception {
		String hql = "from Game a";
		if (gameName != null && !gameName.equals("")) {
			hql += " where a.gameName like '%" + gameName + "%'";
		}
		hql += " order by a.state desc,a.pinyin";
		return baseDAO.search(hql, size, goPage);
	}

	public Long getGameMaxID() throws Exception {
		long val = 0;
		List<Object> list = baseDAO.findList("select max(id) from Game a ");
		Object obj = list.get(0);
		if (obj != null) {
			val = Long.valueOf(obj.toString());
		}
		val += 1;
		return val;
	}

	public Game findGameByName(String gameName) throws Exception {
		List<Game> list = baseDAO.findEntity("from Game a where a.gameName=?", gameName);
		if (list.size() != 0) {
			return (Game) list.get(0);
		}
		return null;
	}

	public List<Game> findGameByName(String gameName, Integer state) throws Exception {
		String hql = "from Game a where 1=1";
		if (gameName != null && !gameName.equals("")) {
			hql += " and a.gameName like '%" + gameName + "%'";
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		hql += " order by substr(a.gameIndex,0,1),a.gameHot desc,a.gameIndex";
		return baseDAO.findEntity(hql);
	}

	public List<Game> findGameByState(Integer state) throws Exception {
		return baseDAO.findEntity("from Game a where a.state=? order by substr(a.gameIndex,0,1),a.gameHot desc,a.gameIndex", state);
	}

	public List<String> findGameCompany(Integer state) throws Exception {
		List<Object> objectList = baseDAO.findList("select distinct(a.company) from Game a where a.state=? and a.company is not null", state);
		if (!Validator.isEmpty(objectList)) {
			List<String> strList = new ArrayList<String>();
			for (Object obj : objectList) {
				strList.add(obj.toString());
			}
			return strList;
		}
		return null;
	}

	public List<Game> findGameByIndex(String gameIndex, Integer state) throws Exception {
		String hql = "from Game a where 1=1";
		if (gameIndex != null) {
			hql += " and a.gameIndex like '" + gameIndex + "%'";
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		hql += " order by a.gameHot desc,a.gameIndex";
		return baseDAO.findEntity(hql);
	}

	public List<Game> findGameNotSetBizKind(Long bizKindID) throws Exception {
		return baseDAO.findEntity("from Game a where a.id not in (select b.game.id from GameKind b where b.bizKind.id=?)", bizKindID);
	}
}
