package com.game.util.ajax;

import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.Constant;
import com.game.util.web.DWRUtil;
import com.game.util.web.MD5;
import com.game.util.web.TradeMD5;
import com.game.util.web.Validator;

public class Account {
	private UserService userService;
	private String usermessage = null;
	User user = null;

	public Account() {
		if (userService == null) {
			userService = (UserService) DWRUtil.getBean("userService");
		}
	}

	public boolean chkUser() {
		user = DWRUtil.getUserSession();
		return (user == null);
	}

	/**
	 * @name 更改电话号码
	 */
	public String doPhoneNum(String phoneNum) throws Exception {
		if (chkUser()) {
			usermessage = "请先登录！";
		} else {
			if (!Validator.isMobilePhone(phoneNum.trim())) {
				usermessage = "请输入正确的手机号码";
			} else {
				user.setPhoneNum(phoneNum.trim());
				userService.updateUser(user);
				DWRUtil.setUserSession(user);
				usermessage = "true";
			}
		}
		return usermessage;
	}

	/**
	 * @name 更改QQ号码
	 */
	public String doQq(String qq) throws Exception {
		if (chkUser()) {
			usermessage = "请先登录！";
		} else {
			if (!Validator.isQq(qq)) {
				usermessage = "请正确输入纯数字的QQ号码";
			} else {
				user.setQq(qq.trim());
				userService.updateUser(user);
				DWRUtil.setUserSession(user);
				usermessage = "true";
			}
		}
		return usermessage;
	}

	/**
	 * @name 更改银行信息
	 */
	public String doBank(String bank) throws Exception {
		return usermessage;
	}

	/**
	 * @name 身份证
	 */
	public String doIdentity(String identity, String realName) throws Exception {
		if (chkUser()) {
			usermessage = "请先登录！";
		} else {
			if (Validator.isName(realName.trim())) {
				usermessage = "请输入身份证号码和真实姓名";
			} else {
				if (Validator.isIDcard(identity)) {
					user.setIdentity(identity.trim());
					user.setRealName(realName.trim());
					userService.updateUser(user);
					DWRUtil.setUserSession(user);
					usermessage = "true";
				} else {
					usermessage = "身份证号码不合法";
				}
			}
		}
		return usermessage;
	}

	/**
	 * @name 更改点子邮箱
	 */
	public String doEmail(String email) throws Exception {
		if (chkUser()) {
			usermessage = "请先登录！";
		} else {
			if (!Validator.isEmail(email.trim())) {
				usermessage = "电子邮件格式不正确";
			} else {
				user.setEmail(email.trim());
				userService.updateUser(user);
				DWRUtil.setUserSession(user);
				usermessage = "true";
			}
		}
		return usermessage;
	}

	/**
	 * @name 更改支付密码
	 */
	public String doApplyPwd(String o_applyPwd, String applyPwd1,
			String applyPwd2) throws Exception {
		if (chkUser()) {
			usermessage = "请先登录！";
		} else {
			if (!Validator.isBlank(user.getUserInfo().getApplyPwd())
					&& Validator.isBlank(o_applyPwd)) {
				usermessage = "请输入原支付密码！";
			} else if (Validator.isBlank(applyPwd1)
					|| Validator.isBlank(applyPwd2)) {
				usermessage = "请输入新支付密码！";
			} else if (!Validator.isPasswLength(applyPwd1)) {
				usermessage = "支付密码长度为6-20！";
			} else if (!applyPwd1.equals(applyPwd2)) {
				usermessage = "确认密码与新密码输入不一致！";
			} else if (!Validator.isBlank(user.getUserInfo().getApplyPwd())
					&& !user.getUserInfo().getApplyPwd()
							.equals(MD5.toMD5(o_applyPwd))) {
				usermessage = "原支付密码输入不正确！";
			} else {
				user.getUserInfo().setApplyPwd(MD5.toMD5(applyPwd1));
				userService.updateUser(user);
				DWRUtil.setUserSession(user);
				usermessage = "true";
			}
		}
		return usermessage;
	}

	/**
	 * @name 设置支付密码
	 */
	public String setApplyPwd(String applyPwd, String applyPwd1)
			throws Exception {
		if (chkUser()) {
			return Constant.NO_LOGIN;
		}
		if (Validator.isBlank(applyPwd)) {
			usermessage = "请输入新支付密码！";
		} else if (!Validator.isPasswLength(applyPwd)) {
			usermessage = "支付密码长度为6-20！";
		} else if (!applyPwd.equals(applyPwd)) {
			usermessage = "确认密码与新密码输入不一致！";
		} else {
			user.getUserInfo().setApplyPwd(MD5.toMD5(applyPwd1));
			userService.updateUser(user);
			DWRUtil.setUserSession(user);
			usermessage = "true";
		}
		return usermessage;
	}

	/**
	 * @name 更改登录密码
	 */
	public String doPassword(String o_password, String password1,
			String password2) throws Exception {
		if (chkUser()) {
			usermessage = "请先登录！";
		} else {
			if (Validator.isBlank(o_password)) {
				usermessage = "请输入原登录密码！";
			} else if (Validator.isBlank(password1)
					|| Validator.isBlank(password2)) {
				usermessage = "请输入新登录密码！";
			} else if (!Validator.isPasswLength(password1)) {
				usermessage = "登录密码长度为6-20！";
			} else if (!password1.equals(password2)) {
				usermessage = "确认密码与新密码输入不一致！";
			} else if (!user.getPassword().equals(TradeMD5.toMD5(o_password))) {
				usermessage = "原登录密码输入不正确！";
			} else {
				user.setPassword(TradeMD5.toMD5(password1));
				userService.updateUser(user);
				DWRUtil.setUserSession(user);
				usermessage = "true";
			}
		}
		return usermessage;
	}

	/**
	 * @name 更改密码提示问题
	 */
	public String doHint(String o_hint, String o_answer, String hint,
			String answer) throws Exception {
		if (chkUser()) {
			usermessage = "请先登录！";
		} else if (Validator.isLtLength(answer, 4)) {
			usermessage = "密码提示答案至少需要2个汉字或4个字符";
		} else if (!Validator.isBlank(user.getHint())
				&& (Validator.isBlank(o_hint) || Validator.isBlank(o_answer))) {
			usermessage = "请输入原密码提示问题和答案";
		} else if (Validator.isBlank(hint) || Validator.isBlank(answer)) {
			usermessage = "请输入新密码提示问题和答案";
		} else if (!Validator.isBlank(user.getHint())
				&& (!user.getHint().equals(o_hint) || !user.getAnswer().equals(
						o_answer))) {
			usermessage = "原密码提示问题或答案输入不正确";
		} else if (Validator.isBlank(user.getHint())
				&& (!Validator.isBlank(o_hint) || !Validator.isBlank(o_answer))) {
			usermessage = "原密码提示问题或答案输入不正确";
		} else {
			user.setHint(hint);
			user.setAnswer(answer);
			userService.updateUser(user);
			DWRUtil.setUserSession(user);
			usermessage = "true";
		}

		return usermessage;
	}

	/**
	 * @name 更改收获地址
	 */
	public String doPostAddr(String postAddr) throws Exception {
		if (chkUser()) {
			usermessage = "请先登录！";
		} else {
			if (Validator.isBlank(postAddr)
					|| Validator.isLtLength(postAddr, 3)) {
				usermessage = "请输入收获地址";
			} else {
				user.setPostAddr(postAddr.trim());
				userService.updateUser(user);
				DWRUtil.setUserSession(user);
				usermessage = "true";
			}
		}
		return usermessage;
	}

}
