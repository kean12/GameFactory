package com.game.util.admin.manage.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.game.util.admin.log.services.LogService;
import com.game.util.admin.manage.services.ManageService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Manage;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.MD5;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class LoginAdminAction extends BaseAction {
	private static final long serialVersionUID = -2429482896125088207L;
	private static Log log = LogFactory.getLog(LoginAdminAction.class);
	private ManageService manageService;
	private LogService logService;
	private Manage manage;
	private String vercode;
	private String error;

	public String execute() throws Exception {
		if (manage == null) {
			return INPUT;
		}

		String ver2 = (String) Struts2Util.getSession("randomCode");
		if (!vercode.equals(ver2)) {
			error = "您输入的验证码不正确！";
			return INPUT;
		}

		Manage tmp_manage = manageService.findManageByName(manage.getUsername());

		if (tmp_manage == null) {
			error = "您输入的用户名或密码不正确！";
			return INPUT;
		}
		if (tmp_manage.getIsuse() != 1) {
			error = "您的账户已被停用！";
			return INPUT;
		}
		try {
			if (null == tmp_manage.getRole()) {
				error = "您没分配角色，请与后台管理员联系！";
				return INPUT;
			}

		} catch (Exception e) {
			error = "您没分配角色，请与后台管理员联系！";
			return INPUT;
		}
		if (tmp_manage.getRole().getState() != 1L) {
			error = "您所属的 " + tmp_manage.getRole().getName() + " 角色已被禁用！";
			return INPUT;
		}
		/*
		 * 判断帐户的IP限制规则 1，当允许ip [getAccessIp()]为空，锁定ip [getLockIp()]为空 ，通过
		 * 
		 * 2，当允许ip [getAccessIp()]为空，锁定ip [getLockIp()]不为空 且锁定ip包含IP时，不通过
		 * 
		 * 
		 * 3，当允许ip [getAccessIp()]不为空且包含该IP时，通过
		 * 
		 * 4，当允许ip [getAccessIp()]为空，锁定ip [getLockIp()]不为空 且锁定ip包含IP时，不通过
		 */
		List<String> accessIpList = tmp_manage.getAccessIp();
		List<String> lockIpList = tmp_manage.getLockIp();

		if (!Validator.isEmpty(accessIpList) && !accessIpList.contains(Struts2Util.getIp())) {
			error = "该IP不在允许IP列表之内！";
			return INPUT;
		} else if (!Validator.isEmpty(lockIpList) && lockIpList.contains(Struts2Util.getIp())) {
			// /** 允许IP列表
			// * 127.0.0.1
			// * 192.168.1.1
			// * ------------------
			// * 不允许IP列表
			// * 127.0.0.1
			// * 当IP为127.0.0.1，此时IP通过‘允许IP列表’验证，却不通过‘ 不允许IP列表’验证
			// * 按照IP限制规则结果为’通过‘，即在里在多做一个判断
			// * */
			// if( !Validator.isBlank(accessIpList) &&
			// !accessIpList.contains(super.getIp()) ){
			// error=" 该IP已在锁定IP列表之内！";
			// return INPUT ;
			// }
			error = " 该IP已在锁定IP列表之内！";
			return INPUT;
		}

		if (!tmp_manage.getPassword().equals(MD5.toMD5(manage.getPassword()))) {
			error = "您输入的用户名或密码不正确！";
			return INPUT;
		}

		{
			tmp_manage.setTmpTime(tmp_manage.getLoginTime());
			tmp_manage.setIp(Struts2Util.getIp());
			tmp_manage.setLoginTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
			manageService.updateEntity(tmp_manage);
		}
		String message = "管理员：" + tmp_manage.getUsername() + " 登录系统";
		message += " 登录时间：" + tmp_manage.getLoginTime();
		message += " 登录IP：" + tmp_manage.getIp();
		message += " 上一次登录时间：" + tmp_manage.getTmpTime();
		log.info(message);
		logService.saveLog(message, "用户登录后台", com.game.util.domain.Log.INFO);
		Struts2Util.setManageSession(tmp_manage);
		return SUCCESS;
	}

	public String logout() throws Exception {
		Struts2Util.clearSession();
		return SUCCESS;
	}

	// *******get/set方法*******
	public ManageService getManageService() {
		return manageService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public Manage getManage() {
		return manage;
	}

	public void setManage(Manage manage) {
		this.manage = manage;
	}

	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
