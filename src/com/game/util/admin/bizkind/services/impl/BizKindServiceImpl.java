package com.game.util.admin.bizkind.services.impl;

import java.util.List;

import com.game.util.admin.bizkind.services.BizKindService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.BizKind;

public class BizKindServiceImpl extends GenericServiceImpl<BizKind, Long> implements BizKindService {

	public List<BizKind> findAllBizKind() throws Exception {
		return baseDAO.findEntity("from BizKind a order by a.orderIndex");
	}

	public void removeBizKind(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(BizKind.class, id));
	}

	public List<BizKind> findBizKindByIsUse(Integer isUse) throws Exception {
		String hql = "from BizKind a where ";
		if (isUse == null || isUse != 1) {
			hql += " a.isUse<>1 or a.isuse is null";
		} else {
			hql += " a.isUse=1";
		}
		hql += " order by a.orderIndex";
		return baseDAO.findEntity(hql);
	}

	public List<BizKind> findBizKindByToleration(Integer isUse) throws Exception {
		String hql = "from BizKind a where a.toleration=1";
		if (isUse == null || isUse != 1) {
			hql += " and a.isUse<>1 or a.isuse is null";
		} else {
			hql += " and a.isUse=1";
		}
		hql += " order by a.orderIndex";
		return baseDAO.findEntity(hql);
	}

	public List<BizKind> findGameKindByGame(Long gameID, Integer isUse) throws Exception {
		String hql = "select a.bizKind from GameKind a where a.game.id=? ";
		if (isUse == null || isUse != 1) {
			hql += " and a.bizKind.isUse<>1 or a.isuse is null";
		} else {
			hql += " and a.bizKind.isUse=1";
		}
		hql += " order by a.bizKind.orderIndex";
		return baseDAO.findEntity(hql, gameID);
	}
}
