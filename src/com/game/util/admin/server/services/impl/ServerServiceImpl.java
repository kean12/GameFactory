package com.game.util.admin.server.services.impl;

import java.util.List;

import com.game.util.admin.server.services.ServerService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Server;
import com.game.util.web.Page;

public class ServerServiceImpl extends GenericServiceImpl<Server, Long> implements ServerService {

	public Page<Server> searchServerByArea(String serverName, Long areaID, int size, int goPage) throws Exception {
		String hql = "from Server a where a.area.id=" + areaID;
		if (serverName != null && !serverName.equals("")) {
			hql += " and a.serverName like '%" + serverName + "%'";
		}
		hql += " order by a.id";
		return baseDAO.search(hql, size, goPage);
	}

	public Long getServerMaxID(Long areaID) throws Exception {
		long val = 0;
		List<Object> list = baseDAO.findList("select max(id) from Server a where a.area.id=?", areaID);
		Object obj = list.get(0);
		if (obj != null) {
			val = Long.valueOf(obj.toString()) + 1;
		} else {
			val = areaID * 1000 + 1;
		}
		return val;
	}

	public Server findServerByName(String serverName, Long areaID) throws Exception {
		String hql = "from Server a where a.serverName=? and a.area.id=" + areaID;
		List<Server> list = baseDAO.findEntity(hql, serverName);
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	public List<Server> findServerByState(Integer state) throws Exception {
		return baseDAO.findEntity("from Server a where a.state=? order by a.id", state);
	}

	public List<Server> findServerByArea(Long areaID, Integer state) throws Exception {
		String hql = "from Server a where a.area.id=? and a.state=" + state + " order by a.id";
		return baseDAO.findEntity(hql, areaID);
	}

}
