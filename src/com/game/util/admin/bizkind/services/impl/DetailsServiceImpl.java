package com.game.util.admin.bizkind.services.impl;

import java.util.List;

import com.game.util.admin.bizkind.services.DetailsService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Details;
import com.game.util.web.Validator;

public class DetailsServiceImpl extends GenericServiceImpl<Details, Long> implements DetailsService {
	public void removeDetails(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Details.class, id));
	}

	public List<Details> findDetailsByGameKind(Long gameKindID) throws Exception {
		String hql = "from Details a where a.gameKind.id=? order by a.orderIndex";
		return baseDAO.findEntity(hql, gameKindID);
	}

	public Details findDetailsByGameKind(Long gameKindID, Integer type) throws Exception {
		String hql = "from Details a where a.gameKind.id=? ";
		if (type != null) {
			hql += " and a.type=" + type + " order by a.orderIndex";
			List<Details> list = baseDAO.findEntity(hql, gameKindID);
			if (!Validator.isEmpty(list)) {
				return list.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Details findDetailsByFormName(Long gameKindID, String formName, Long notID) throws Exception {
		String hql = "from Details a where a.parent.parent.gameKind.id=" + gameKindID + " and a.formName=?";
		if (notID != null) {
			hql += " and a.id!=" + notID;
		}
		List<Details> list = baseDAO.findEntity(hql, formName);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public Details findDetailsByAccountGroup(Long gameKindID, Integer groupType) throws Exception {
		String hql = "from Details a where a.parent.gameKind.id=? ";
		hql += " and a.type=" + groupType + " order by a.orderIndex";
		List<Details> list = baseDAO.findEntity(hql, gameKindID);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<Details> findDetailsByAttributeGroup(Long gameKindID, Integer groupType) throws Exception {
		String hql = "from Details a where a.parent.gameKind.id=? and a.type=" + groupType + " order by a.orderIndex";
		return baseDAO.findEntity(hql, gameKindID);
	}

}
