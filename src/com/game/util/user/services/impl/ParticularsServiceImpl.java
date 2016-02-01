package com.game.util.user.services.impl;

import java.util.List;

import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Particulars;
import com.game.util.user.services.ParticularsService;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class ParticularsServiceImpl extends GenericServiceImpl<Particulars, Long> implements ParticularsService {

	public Particulars createParticulars(Particulars particulars) throws Exception {
		return baseDAO.saveEntity(particulars);
	}

	public Page<Particulars> findParticulars(Long userID, String orderNum, String runningNum, String bank, Integer type, String beginTime, String endTime, int size, int goPage) throws Exception {
		String hql = "from Particulars a where 1=1";
		if (userID != null) {
			hql += " and a.user.id=" + userID;
		}
		if (orderNum != null && !orderNum.equals("")) {
			hql += " and a.orderNum like '%" + orderNum + "%'";
		}
		if (runningNum != null && !runningNum.equals("")) {
			hql += " and a.runningNum like '%" + runningNum + "%'";
		}
		if (bank != null && !bank.equals("")) {
			hql += " and a.bank like '%" + bank + "%'";
		}
		if (type != null) {
			hql += " and a.type=" + type;
		}

		if (beginTime != null && !beginTime.equals("")) {
			hql += " and a.time>='" + beginTime + "'";
		}
		if (endTime != null && !endTime.equals("")) {
			hql += " and a.time<='" + endTime + " 23:59:59:999'";
		}
		hql += " order by a.time,id";
		return baseDAO.search(hql, size, goPage);
	}

	public Particulars getParticularsById(Long id) throws Exception {
		return baseDAO.getEntity(Particulars.class, id);
	}

	public void updateParticulars(Particulars particulars) throws Exception {
		baseDAO.updateEntity(particulars);
	}

	public Particulars getParticularsNum(String orderNum, String runningNum) throws Exception {
		String[] param = { orderNum, runningNum };
		String hql = "from Particulars a where a.orderNum=? and runningNum=?";
		List<Particulars> list = baseDAO.findEntity(hql, param);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
