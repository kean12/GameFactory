package com.game.util.admin.credit.services.impl;

import java.util.List;

import com.game.util.admin.credit.services.CreditService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Credit;
import com.game.util.web.Validator;

public class CreditServiceImpl extends GenericServiceImpl<Credit, Long> implements CreditService {

	public List<Credit> findCredit(Integer type, Integer grade) throws Exception {
		String hql = "from Credit a where 1=1";
		if (type != null) {
			hql += " and a.type=" + type;
		}
		if (grade != null) {
			hql += " and a.grade=" + grade;
		}
		hql += " order by a.grade";
		return baseDAO.findEntity(hql);
	}

	public Credit findCreditByGrade(Integer type, Integer grade) throws Exception {
		List<Credit> list = findCredit(type, grade);
		if (!Validator.isEmpty(list)) {
			return (Credit) list.get(0);
		}
		return null;
	}

	public void removeCredit(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Credit.class, id));
	}

	public List<Credit> findCreditGTGrade(Integer type, Integer grade) throws Exception {
		String hql = "from Credit a where 1=1";
		if (type != null) {
			hql += " and a.type=" + type;
		}
		if (grade != null) {
			hql += " and a.grade>?";
		}
		hql += " order by a.grade";
		return baseDAO.findEntity(hql, grade);
	}

	public void updateCreditByMaxGrade(Integer maxCredit, Integer type) throws Exception {
		int grade = 0;
		List<Object> list = baseDAO.findList("select Max(a.grade) from Credit a where a.type=" + type);
		Object obj = list.get(0);
		if (obj != null) {
			grade = Integer.valueOf(obj.toString());
		}
		String hql = "update Credit a set a.upper=" + maxCredit + " where type=" + type + " and grade=" + grade;
		baseDAO.updateEntity(hql);
	}

}
