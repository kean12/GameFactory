package com.game.bizinfo.services.impl;

import java.util.List;

import com.game.bizinfo.services.BizInfoService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.BizInfo;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class BizInfoServiceImpl extends GenericServiceImpl<BizInfo, Long>
		implements BizInfoService {

	public Page<BizInfo> findBizInfoByState(Long userID, Long gameID,
			Long areaID, Long serverID, Long bizKindID, String content,
			Integer state, String nowTime, String order_by, String sort,
			int size, int goPage) throws Exception {
		String hql = "from BizInfo a where 1=1";
		if (userID != null) {
			hql += " and a.owner.id=" + userID;
		}

		if (gameID != null) {
			hql += " and (a.game.id=" + gameID;
			hql += " or a.server.area.game.id=" + gameID + ")";
		}

		if (serverID != null) {
			hql += " and a.server.id=" + serverID;
		}
		if (areaID != null) {
			hql += " and a.server.area.id=" + areaID;
		}
		if (gameID != null) {
			hql += " and (a.game.id=" + gameID;
			hql += " or a.server.area.game.id=" + gameID + ")";
		}
		if (bizKindID != null) {
			hql += " and a.bizKind.id=" + bizKindID;
		}
		if (content != null && !content.equals("") && !content.equals("请输入关键词")) {
			hql += " and (";
			hql += " a.title like '%" + content + "%'";
			hql += ")";
		}

		if (state != null) {
			hql += " and a.isBuy=" + state;
		}
		if (nowTime != null && !nowTime.equals("")) {
			hql += " and a.tradeStart<'" + nowTime + "' and a.tradeEnd>'" + nowTime + "'";
		}
		if (order_by != null && !order_by.equals("")) {
			if (order_by.equals("proportion")) {
				hql += " order by to_number(a.proportion)";
			} else if (order_by.equals("proportion desc")) {
				hql += " order by to_number(a.proportion) desc";
			} else {
				hql += " order by a." + order_by;
				if (sort != null && sort.equals("desc")) {
					hql += " desc";
				}
			}
		} else {
			hql += " order by a.bizCreTime desc";
		}
		return baseDAO.search(hql, size, goPage);
	}

	public void updateBizInfoByHql(String hql) throws Exception {
		baseDAO.updateEntity(hql);
	}

	public BizInfo getBizInfo(Long userID, Long bizInfoID) throws Exception {
		String hql = null;
		if (userID != null) {
			hql = "from BizInfo a where a.owner.id=" + userID + " and a.id=?";
		} else {
			hql = "from BizInfo a where a.id=?";
		}
		List<BizInfo> list = baseDAO.findEntity(hql, bizInfoID);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public BizInfo getBizInfo(Long userID, String serial) throws Exception {
		String hql = null;
		if (userID != null) {
			hql = "from BizInfo a where a.owner.id=" + userID + " and a.serial=?";
		} else {
			hql = "from BizInfo a where a.serial=?";
		}

		List<BizInfo> list = baseDAO.findEntity(hql, serial);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public void removeBizInfo(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(BizInfo.class, id));
	}

}
