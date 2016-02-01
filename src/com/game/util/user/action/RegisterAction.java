package com.game.util.user.action;

import com.game.util.base.action.BaseAction;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Struts2Util;
import com.game.util.web.TradeMD5;
import com.game.util.web.Validator;

public class RegisterAction extends BaseAction {
	private static final long serialVersionUID = 1580047163545416154L;
	private UserService userService;
	private User user;
	private User errorUser;
	private String vercode;
	private ErrorInfo errorInfo;

	public String register() throws Exception {
		return "register";
	}

	/**
	 * 用户注册
	 */
	public String execute() throws Exception {
		if (verify().equals("input")) {
			return INPUT;
		} else if (verify().equals("true")) {
			user.setId(userService.getUserMaxID());
			user.setPassword(TradeMD5.toMD5(user.getPassword()));

			user.setRegTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
			user.setLock("N");
			user.setIsUse(1);

			user.getUserInfo().setMoney("0.00");
			user.getUserInfo().setBuyerCredit(0);
			user.getUserInfo().setBuyerGrade(0);
			user.getUserInfo().setSellerCredit(0);
			user.getUserInfo().setSellerGrade(0);
			user.getUserInfo().setFreemoney("0.00");
			user.getUserInfo().setSellerPositiveRatio("-");
			user.getUserInfo().setBuyerPositiveRatio("-");

			// user.getUserInfo().setApplyPwd(MD5.toMD5(user.getUserInfo().getApplyPwd()));
			user.getUserInfo().setIp(Struts2Util.getIp());
			user.getUserInfo().setLoginTime(user.getRegTime());
			user.getUserInfo().setTmpIp(user.getUserInfo().getIp());
			user.getUserInfo().setTmpTime(user.getRegTime());

			user.getUserInfo().setUser(user);

			userService.createUser(user);
			Struts2Util.setUserSession(user);
			return "success";
		}
		errorUser = user;
		return "execute";

	}

	public String verify() throws Exception {
		if (user == null) {
			return "input";
		}
		boolean flag = true;
		errorInfo = new ErrorInfo();
		String ver2 = (String) Struts2Util.getSession().get("randomCode");
		if (ver2 == null || vercode == null || !vercode.equals(ver2)) {
			flag = false;
			errorInfo.setVercodeMess("<span style='color:red;'>验证码输入不正确</span>");
		}

		// 用户名
		if (Validator.isUserName(user.getUsername())) {
			User tmp_user = userService.findUserByName(user.getUsername(), null);
			if (tmp_user == null) {
				errorInfo.setUsernameMess("<span style='color:green;'>通过</span>");
			} else {
				flag = false;
				errorInfo.setUsernameMess("<span style='color:red;'>用户名已被注册</span>");
			}
		} else {
			errorInfo.setUsernameMess("<span style='color:red;'>6-20个字符，仅限字母、数字、下划线</span>");
		}

		// 密码
		if (!Validator.isPasswLength(user.getPassword())) {
			flag = false;
			errorInfo.setPasswordMess("<span style='color:red;'>密码长度为6-20</span>");
		} else {
			errorInfo.setPasswordMess("<span style='color:green;'>通过</span>");
		}

		// 密码提示问题
		if (Validator.isBlank(user.getHint())) {
			flag = false;
			errorInfo.setHintMess("<span style='color:red;'>请选择提示问题</span>");
		} else {
			errorInfo.setHintMess("<span style='color:green;'>通过</span>");
		}

		// 提示问题答案

		if (Validator.isLtLength(user.getAnswer().trim(), 4)) {
			flag = false;
			errorInfo.setAnswerMess("<span style='color:red;'>至少2个汉字或4个字符</span>");
		} else {
			errorInfo.setAnswerMess("<span style='color:green;'>通过</span>");
		}

		// 验证电子邮件
		if (Validator.isEmail(user.getEmail())) {
			errorInfo.setEmailMess("<span style='color:green;'>通过</span>");
		} else {
			flag = false;
			errorInfo.setEmailMess("<span style='color:red;'>电子邮件格式不正确</span>");
		}

		// 验证qq
		if (Validator.isQq(user.getQq())) {
			errorInfo.setQqMess("<span style='color:green;'>通过</span>");
		} else {
			flag = false;
			errorInfo.setQqMess("<span style='color:red;'>请正确输入纯数字的QQ号码</span>");
		}

		// 验证手机号码
		if (Validator.isMobilePhone(user.getPhoneNum())) {
			errorInfo.setPhoneNumMess("<span style='color:green;'>通过</span>");
		} else {
			flag = false;
			errorInfo.setPhoneNumMess("<span style='color:red;'>请输入正确的手机号码</span>");
		}
		return String.valueOf(flag);
	}

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

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	public User getErrorUser() {
		return errorUser;
	}

	public void setErrorUser(User errorUser) {
		this.errorUser = errorUser;
	}

}
