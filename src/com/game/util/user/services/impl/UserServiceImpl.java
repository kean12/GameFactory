package com.game.util.user.services.impl;

import java.util.List;

import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

	public User createUser(User user) throws Exception {
		user = baseDAO.saveEntity(user);
		return user;
	}

	public User getUserById(Long id) throws Exception {
		return baseDAO.getEntity(User.class, id);
	}

	public void updateUser(User user) throws Exception {
		baseDAO.saveorupdate(user);
	}

	public User findUserByName(String username, Integer isUse) throws Exception {
		String hql = "from User a where a.username=?";
		if (isUse != null) {
			hql += " and isUse=" + isUse;
		}
		List<User> list = baseDAO.findEntity(hql, username);
		if (!Validator.isEmpty(list)) {
			return (User) list.get(0);
		}
		return null;
	}

	public Long getUserMaxID() throws Exception {
		long val = 0;
		List<Object> list = baseDAO.findList("select max(id) from User a ");
		Object obj = list.get(0);
		if (obj != null) {
			val = Long.valueOf(obj.toString());
		}
		val += 1;
		return val;
	}

	public List<User> findUserByIsUse(Integer isUse) throws Exception {
		String hql = "from User a where 1=1";
		if (isUse != null) {
			hql += " and isUse=" + isUse;
		}
		return baseDAO.findEntity(hql);
	}

	public Page<User> getAllUsers(int size, int goPage, User u) throws Exception {
		return getAllUsers(size, goPage, u, "id");
	}

	public Page<User> getAllUsers(int size, int goPage, User u, String orderBy) throws Exception {
		String hql = "from User u where u.userInfo.money is not null ";
		if (u != null) {
			if (u.getId() != null && u.getId() > 0) {
				hql += " and u.id =" + u.getId();
			}
			if (!Validator.isBlank(u.getUsername())) {
				hql += " and u.username like '%" + u.getUsername() + "%' ";
			}
			if (!Validator.isBlank(u.getRealName())) {
				hql += " and u.realName like '%" + u.getRealName() + "%' ";
			}
			if (!Validator.isBlank(u.getQq())) {
				hql += " and u.qq like '%" + u.getQq() + "%' ";
			}
			if (!Validator.isBlank(u.getEmail())) {
				hql += " and u.email like '%" + u.getEmail() + "%' ";
			}
		}
		// order 的确定
		if (orderBy.equalsIgnoreCase("id")) {
			hql += " order by u.id";
		} else if (orderBy.equalsIgnoreCase("buyer")) {
			hql += " order by u.userInfo.buyerCredit desc ";
		} else if (orderBy.equalsIgnoreCase("seller")) {
			hql += " order by u.userInfo.sellerCredit desc ";
		}
		return baseDAO.search(hql, size, goPage);
	}
}
