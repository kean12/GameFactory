package com.game.util.admin.manage.services.impl;

import java.util.List;

import com.game.util.admin.manage.services.ManageService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Manage;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class ManageServiceImpl extends GenericServiceImpl<Manage, Long> implements ManageService {

	public Manage findManageByName(String username) throws Exception {
		List<Manage> list = baseDAO.findEntity("from Manage a where a.username=?", username);
		if (!Validator.isEmpty(list)) {
			return (Manage) list.get(0);
		}
		return null;
	}

	public Page<Manage> getAllManage(int size, int goPage) throws Exception {
		return baseDAO.search("from Manage a order by a.name", size, goPage);
	}

	public void removeManage(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Manage.class, id));
	}

	public List<Manage> findManageByRole(String name, Integer state) throws Exception {
		String hql = "from Manage a where a.isuse=1 and a.role.state=1";
		if (name != null) {
			hql += " and a.role.name='" + name + "'";
		}
		if (state != null) {
			hql += " and a.state=" + state;
		}
		return baseDAO.findEntity(hql);
	}

	public Manage findManageByName(String name, String roleName) throws Exception {
		String hql = "from Manage a where a.isuse=1 and a.role.state=1";
		if (roleName != null) {
			hql += " and a.role.name='" + roleName + "'";
		}
		if (name != null) {
			hql += " and a.name='" + name + "'";
		}
		List<Manage> list = baseDAO.findEntity(hql);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	// *********Client********
	public Manage findManageByClientLogin(String username, String password) throws Exception {
		String[] param = { username, password };
		String hql = "from Manage a where a.username=? and a.password=? and a.isuse=1 and a.role.state=1";
		List<Manage> list = baseDAO.findEntity(hql, param);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}

		return null;
	}

}
