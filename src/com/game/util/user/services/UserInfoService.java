package com.game.util.user.services;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.UserInfo;

public interface UserInfoService extends GenericService<UserInfo, Long> {
	/**
	 * @name 根据ID加载用户信息实体
	 */
	public UserInfo getUserInfoById(Long id) throws Exception;

	/**
	 * @name 更新一个用户信息
	 */
	public void updateUserInfo(UserInfo userInfo) throws Exception;

}
