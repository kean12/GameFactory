package com.game.util.admin.game.services.impl;

import java.util.List;

import com.game.util.admin.game.services.ProfessionService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Profession;
import com.game.util.web.Validator;

public class ProfessionServiceImpl extends GenericServiceImpl<Profession, Long> implements ProfessionService {
	public void removeProfession(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Profession.class, id));
	}

	public List<Profession> findProfessionByGame(Long gameID, Integer isUse) throws Exception {
		String hql = "from Profession a where a.game.id=?";
		if (isUse != null) {
			hql += " and a.isUse=" + isUse;
		}
		hql += " order by a.orderIndex";
		return baseDAO.findEntity(hql, gameID);
	}

	public Profession findProfessionByName(Long gameID, String professionName) throws Exception {
		String hql = "from Profession a where a.game.id=" + gameID + " and a.professionName=?";
		List<Profession> list = baseDAO.findEntity(hql, professionName);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}
