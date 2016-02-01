package com.game.util.admin.menu.services.impl;

import java.util.List;
import java.util.Map;

import com.game.util.admin.menu.services.MenuService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Menu;
import com.game.util.web.BinaryUtil;
import com.game.util.web.Validator;

public class MenuServiceImpl extends GenericServiceImpl<Menu, Long> implements MenuService {
	public void updatePowers(Map<Long, List<Long>> powersMap) throws Exception {
		String hql = "";
		int power;
		for (Long menuId : powersMap.keySet()) {
			power = BinaryUtil.logic(powersMap.get(menuId)).intValue();
			hql = "update Menu a set a.power=" + power + " where a.id=" + menuId;
			baseDAO.updateEntity(hql);
		}
	}

	public List<Menu> getAllMenu() throws Exception {
		return baseDAO.findEntity("from Menu a order by a.orderIndex");
	}

	public void removeMenu(Long id) throws Exception {
		baseDAO.removeEntity(super.getEntity(Menu.class, id));
	}

	public List<Menu> findMenuByHql(String hql) throws Exception {
		return baseDAO.findEntity(hql);
	}

	public List<Menu> findMenuByView(Integer isView) throws Exception {
		return baseDAO.findEntity("from Menu a where a.isView=? order by a.orderIndex", isView);
	}

	public Menu findMenuByName(String menuName, Long parentID) throws Exception {
		String hql = "from Menu a where a.menuName=? and ";
		if (parentID == null) {
			hql += "a.parent.id is null";
		} else {
			hql += "a.parent.id = " + parentID;
		}
		List<Menu> list = baseDAO.findEntity(hql, menuName);
		if (!Validator.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public List<Menu> findMenuByParent(Long parentID) throws Exception {
		String hql = "from Menu a where a.parent.id";
		if (parentID == null) {
			hql += " is null";
		} else {
			hql += "=" + parentID;
		}
		hql += " order by a.orderIndex";
		return baseDAO.findEntity(hql);
	}
}
