package com.game.complaint.services.impl;

import java.util.List;

import com.game.complaint.services.ComplaintService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Complaint;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class ComplaintServiceImpl extends GenericServiceImpl<Complaint, Long> implements ComplaintService {

	public Complaint getComplaint(Long userId, Long orderId) throws Exception {
		String hql = "from Complaint a where 1=1";
		if (userId != null) {
			hql += " and a.user.id=" + userId;
		}
		if (orderId != null) {
			hql += " and a.order.id=" + orderId;
		}
		List<Complaint> list = baseDAO.findEntity(hql);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public void removeComplaint(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Complaint.class, id));
	}

	public Page<Complaint> searchComplaint(Long userID, Long defendant, Integer state, Integer type, int size, int goPage) throws Exception {
		String hql = "from Complaint a where 1=1";
		if (userID != null) {
			hql += " and a.user.id=" + userID;
		}
		if (defendant != null) {
			hql += " and a.defendant.id=" + defendant;
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		if (type != null) {
			hql += " and a.type=" + type;
		}
		hql += "order by a.applyTime desc";
		return baseDAO.search(hql, size, goPage);
	}

}
