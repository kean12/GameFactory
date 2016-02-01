package com.game.util.admin.area.services.impl;

import java.util.List;

import com.game.util.admin.area.services.AreaService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Area;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class AreaServiceImpl extends GenericServiceImpl<Area, Long> implements AreaService {
	public Page<Area> searchAreaByGame(String areaName, Long gameID, int size, int goPage) throws Exception {
		String hql = "from Area a where a.game.id=" + gameID;
		if (areaName != null && !areaName.equals("")) {
			hql += " and a.areaName like '%" + areaName + "%'";
		}
		hql += " order by a.id";
		return baseDAO.search(hql, size, goPage);
	}

	public Long getAreaMaxID(Long gameID) throws Exception {
		long val = 0;
		List<Object> list = baseDAO.findList("select max(id) from Area a where a.game.id=?", gameID);
		Object obj = list.get(0);
		if (obj != null) {
			val = Long.valueOf(obj.toString()) + 1;
		} else {
			val = gameID * 1000 + 1;
		}
		return val;
	}

	public Area findAreaByName(String areaName, Long gameID) throws Exception {
		String hql = "from Area a where a.areaName=? and a.game.id=" + gameID;
		List<Area> list = baseDAO.findEntity(hql, areaName);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public List<Area> findAreaByState(Integer state) throws Exception {
		return baseDAO.findEntity("from Area a where a.state=? order by a.id", state);
	}

	public List<Area> finfAreaByGame(Long gameID, Integer state) throws Exception {
		String hql = "from Area a where a.game.id=? and a.state=" + state + " order by a.id";
		return baseDAO.findEntity(hql, gameID);
	}

}
