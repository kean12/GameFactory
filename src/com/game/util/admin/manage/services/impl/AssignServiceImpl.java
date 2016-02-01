package com.game.util.admin.manage.services.impl;

import java.util.List;

import com.game.util.admin.manage.services.AssignService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Assign;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class AssignServiceImpl extends GenericServiceImpl<Assign, Long> implements AssignService {
	public Assign getAssignByOrderNum(String orderNum, Long manageID, Integer state) throws Exception {
		String hql = "from Assign a where a.order.orderNum=?";
		if (manageID != null) {
			hql += " and a.manage.id=" + manageID;
		}

		if (state != null) {
			hql += " and a.state=" + state;
		}

		List<Assign> list = baseDAO.findEntity(hql, orderNum);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public Page<Assign> findAssignByManage(Long manageID, Integer state, int size, int goPage) throws Exception {
		String hql = "from Assign a where 1=1";
		if (manageID != null) {
			hql += " and a.manage.id=" + manageID;
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		hql += " order by a.startTime desc";
		return baseDAO.search(hql, size, goPage);
	}

	public Page<Assign> findAssignByRefundment(Long manageID, Integer orderState, int size, int goPage) throws Exception {
		String hql = "from Assign a where 1=1";
		if (manageID != null) {
			hql += " and a.manage.id=" + manageID;
		}
		if (orderState != null) {
			hql += " and a.order.state=" + orderState;
		} else {
			hql += " and a.order.state in (4,5,7)";
		}
		hql += " order by a.startTime desc";
		return baseDAO.search(hql, size, goPage);
	}

	public List<Assign> findAssignByHql(String hql) throws Exception {
		return baseDAO.findEntity(hql);
	}

	public Page<Assign> findAssignByOvertime(Long manageID, String orderNum, int size, int goPage) throws Exception {
		String hql = "from Assign a where a.overtime=1";
		if (manageID != null) {
			hql += " and a.manage.id=" + manageID;
		}
		if (!Validator.isBlank(orderNum)) {
			hql += " and a.order.orderNum like '%" + orderNum + "%'";
		}
		hql += " order by a.startTime desc";
		return baseDAO.search(hql, size, goPage);
	}

	public Assign getAssignByOvertime(String orderNum, Long manageID, Integer overtime) throws Exception {
		String hql = "from Assign a where a.order.orderNum=?";
		if (manageID != null) {
			hql += " and a.manage.id=" + manageID;
		}
		if (overtime != null) {
			hql += " and a.overtime=" + overtime;
		}
		List<Assign> list = baseDAO.findEntity(hql, orderNum);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
