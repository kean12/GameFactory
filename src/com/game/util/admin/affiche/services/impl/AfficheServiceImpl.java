package com.game.util.admin.affiche.services.impl;

import java.util.List;

import com.game.util.admin.affiche.services.AfficheService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Affiche;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;

public class AfficheServiceImpl extends GenericServiceImpl<Affiche, Long> implements AfficheService {

	public void removeAffiche(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Affiche.class, id));
	}

	public Page<Affiche> searchAffiche(Integer state, int size, int goPage) throws Exception {
		String hql = "from Affiche a ";
		if (state != null) {
			hql += " where a.state=" + state;
		}
		hql += " order by a.time desc";
		return baseDAO.search(hql, size, goPage);
	}

	public void updateAffiche(Affiche affiche) throws Exception {
		affiche.setTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
		baseDAO.updateEntity(affiche);
	}

	public List<Affiche> findAfficheByCount(Integer type, Integer state, int count) throws Exception {
		String hql = "from Affiche a where" + " a.type=" + type + " and" + " a.state=" + state + " order by a.time desc";
		return baseDAO.findEntity(hql, 0, count);
	}

	public Page<Affiche> searchAffiche(Integer type, Integer state, int size, int goPage) throws Exception {
		String hql = "from Affiche a where 1=1";
		if (type != null) {
			hql += " and a.type=" + type;
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		hql += " order by a.time desc";
		return baseDAO.search(hql, size, goPage);
	}

}
