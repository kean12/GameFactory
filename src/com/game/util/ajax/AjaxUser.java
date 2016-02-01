package com.game.util.ajax;

import com.game.util.base.action.BaseAction;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.DWRUtil;

public class AjaxUser extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String usermessage;

	public AjaxUser() {
	}

	/**
	 * @param usernamen 需要检验的用户名
	 * @return 存在返回true 否则返回false
	 */
	public String checkname(String username) throws Exception {
		UserService userService = (UserService) DWRUtil.getBean("userService");
		User user = userService.findUserByName(username, null);
		if (user == null) {
			usermessage = "true";
		} else {
			usermessage = "用户名已被注册";
		}
		return usermessage;
	}

	/**
	 * 验证验证码
	 * @param vercode 用户填写的 验证码
	 * @return true 正确，false 错误
	 */
	public boolean checkvercode(String vercode) {
		String randomCode = DWRUtil.getSession("randomCode").toString();
		return vercode.equals(randomCode);
	}
}
