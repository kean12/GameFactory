package com.game.util.user.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.game.util.base.action.BaseAction;
import com.game.util.domain.User;
import com.game.util.domain.UserInfo;
import com.game.util.user.services.UserService;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Struts2Util;
import com.game.util.web.TradeMD5;
import com.game.util.web.Validator;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 6363611588407346209L;
	private static Log log = LogFactory.getLog(LoginAction.class);
	private UserService userService;
	private User user;
	private String vercode;
	private boolean flag = true;

	private String registerType;

	/**
	 * 进入登陆页面
	 */
	public String login() throws Exception {
		if (flag) {
			removeErrorSession();
		}
		return LOGIN;
	}

	/**
	 * 用户登陆
	 */
	public String carry() throws Exception {
		if (Validator.isBlank(vercode) || user == null || Validator.isBlank(user.getUsername()) || Validator.isBlank(user.getPassword())) {
			return INPUT;
		}

		flag = false;

		String ver2 = (String) Struts2Util.getSession("randomCode");
		if (!ver2.equals(vercode)) {
			Struts2Util.setSession("errorMessage", "验证码不正确!");
			return INPUT;
		}

		User tmp_user = userService.findUserByName(user.getUsername(), null);
		if (null == tmp_user) { // 当用户不存在时，转到注册页面并携带 用户名跟密码参数
			registerType = "completeInformation";
			return "input2register";
		}

		if (tmp_user.getIsUse() == null || tmp_user.getLock() == null) {
			tmp_user.setIsUse(1);
			tmp_user.setLock("N");
		}

		if (tmp_user.getIsUse() != 1) {
			Struts2Util.setSession("errorMessage", "您的账号因违反用户规则，已被停用!");
			return INPUT;
		}

		if (!TradeMD5.toMD5(user.getPassword()).equals(tmp_user.getPassword())) {
			Struts2Util.setSession("errorMessage", "您输入的用户名或密码错误!");
			return INPUT;
		}

		removeErrorSession();

		UserInfo userInfo = null;
		try {
			userInfo = tmp_user.getUserInfo();
			userInfo.setTmpTime(userInfo.getLoginTime());
			userInfo.setLoginTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
			userInfo.setTmpIp(userInfo.getIp());
			userInfo.setIp(Struts2Util.getIp());
		} catch (Exception e) {
			userInfo = new UserInfo();
			userInfo.setIp(Struts2Util.getIp());
			userInfo.setLoginTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
			userInfo.setTmpIp(userInfo.getIp());
			userInfo.setTmpTime(userInfo.getLoginTime());

			userInfo.setMoney("0.00");
			userInfo.setBuyerCredit(0);
			userInfo.setBuyerGrade(0);
			userInfo.setSellerCredit(0);
			userInfo.setSellerGrade(0);
			userInfo.setFreemoney("0.00");
			userInfo.setSellerPositiveRatio("-");
			userInfo.setBuyerPositiveRatio("-");
		} finally {
			userInfo.setUser(tmp_user);
			tmp_user.setUserInfo(userInfo);
			userService.updateUser(tmp_user);

			Struts2Util.setUserSession(tmp_user);
			log.info("用户： " + tmp_user.getUsername() + " 登录系统");
		}

		return "success";
	}

	/**
	 * 退出登录
	 */
	public String logout() throws Exception {
		User tmp_user = Struts2Util.getUserSession();
			if (tmp_user != null) {
				Struts2Util.removeUserSession();
				log.info("用户： " + tmp_user.getUsername() + " 退出系统");
			}
			removeErrorSession();
		return "logout";
	}

	/**
	 * 删除登录错误信息
	 */
	public void removeErrorSession() {
		Object errotMess = Struts2Util.getSession("errorMessage");
		if (errotMess != null) {
			Struts2Util.removeSession("errorMessage");
		}
	}

	// ~==============getting setting========================

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getRegisterType() {
		return registerType;
	}
}
