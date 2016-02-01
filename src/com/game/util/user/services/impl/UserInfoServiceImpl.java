package com.game.util.user.services.impl;

import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.UserInfo;
import com.game.util.user.services.UserInfoService;

public class UserInfoServiceImpl extends GenericServiceImpl<UserInfo, Long> implements UserInfoService {
	public UserInfo getUserInfoById(Long id) throws Exception {
		return baseDAO.getEntity(UserInfo.class, id);
	}

	public void updateUserInfo(UserInfo userInfo) throws Exception {
		baseDAO.saveorupdate(userInfo);
	}
}
