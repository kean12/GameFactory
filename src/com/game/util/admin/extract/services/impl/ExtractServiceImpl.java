package com.game.util.admin.extract.services.impl;

import java.util.Map;

import com.game.util.admin.extract.services.ExtractService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Extract;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;

public class ExtractServiceImpl extends GenericServiceImpl<Extract, Long> implements ExtractService {

	public void removeExtract(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Extract.class, id));
	}

	public Page<Extract> searchExtract(Long userID, String bank, String account, Integer state, String beginTime, String endTime, int size, int goPage) throws Exception {
		String hql = "from Extract a where 1=1";
		if (userID != null) {
			hql += " and a.user.id=" + userID;
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		if (bank != null) {
			hql += " and a.bank like '%" + bank + "%'";
		}
		if (account != null) {
			hql += " and a.account like '%" + account + "%'";
		}
		if (beginTime != null) {
			hql += " and a.time>='" + beginTime + "'";
		}
		if (endTime != null) {
			hql += " and a.time<='" + endTime + " 23:59:59:999'";
		}
		hql += " order by a.time desc";
		return baseDAO.search(hql, size, goPage);
	}

	public Page<Extract> searchExtract(Map<String, Object> map, int size, int goPage) throws Exception {
		String hql = "from Extract a where 1=1";
		if (map.get("username") != null) {
			hql += " and a.user.username='" + map.get("username") + "'";
		}

		if (map.get("beginTime") != null) {
			hql += " and a.time>='" + map.get("beginTime") + "'";
		}

		if (map.get("endTime") != null) {
			hql += " and a.time<='" + map.get("endTime") + " 23:59:59:999'";
		}

		if (map.get("p_beginTime") != null) {
			hql += " and a.processTime>='" + map.get("p_beginTime") + "'";
		}

		if (map.get("p_endTime") != null) {
			hql += " and a.processTime<='" + map.get("p_endTime")
					+ " 23:59:59:999'";
		}

		if (map.get("moneyMin") != null) {
			hql += " and a.money>=" + map.get("moneyMin");
		}

		if (map.get("moneyMax") != null) {
			hql += " and a.money<=" + map.get("moneyMax");
		}

		if (map.get("extractNum") != null) {
			hql += " and a.extractNum like '%" + map.get("extractNum") + "%'";
		}

		if (map.get("bank") != null) {
			hql += " and a.bank like '%" + map.get("bank") + "%'";
		}

		if (map.get("account") != null) {
			hql += " and a.account = '" + map.get("account") + "'";
		}

		if (map.get("state") != null) {
			hql += " and a.state = " + map.get("state");
		}

		hql += " order by a.time desc";
		return baseDAO.search(hql, size, goPage);
	}

	public Integer getExtractCount(Long userID) throws Exception {
		String nowTime = DateUtil.nowDate(Constant.YYYY_MM_DD);
		String hql = "select count(*) from Extract a where a.user.id=? and a.time>='" + nowTime + "' and a.time<='" + nowTime + " 23:59:59:999'";
		int count = Integer.valueOf(baseDAO.findList(hql, userID).get(0).toString());
		return count;
	}
}
