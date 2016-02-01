package com.game.util.admin.manage.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.game.util.admin.log.services.LogService;
import com.game.util.admin.manage.services.ManageService;
import com.game.util.admin.role.services.RoleService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Manage;
import com.game.util.domain.Role;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.MD5;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class ManageAction extends BaseAction {

	private static final long serialVersionUID = 3030000864562250130L;
	private ManageService manageService;
	private LogService logService;
	private RoleService roleService;

	private List<Role> roleList;

	private Manage manageInfo;
	private Page<Manage> page;
	private long id;

	private String rawPwd;
	private String updatePwd;
	private String confirmPwd;

	private static Log log = LogFactory.getLog(ManageAction.class);

	/**
	 * 获得后台用户列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String listManage() throws Exception {
		page = manageService.getAllManage(15, super.getGoPage());
		return "listManage";
	}

	/**
	 * 编辑或添加用户
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addManage() throws Exception {
		if (id > 0) {
			manageInfo = manageService.getEntity(Manage.class, id);
		}
		roleList = roleService.getAllRole(1);
		return "addManage";
	}

	/**
	 * 保存后台用户资料
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String saveManage() throws Exception {

		if (!Validator.isBlank(manageInfo.getPassword2())) { // 如果修改密码不为空
			manageInfo.setPassword(MD5.toMD5(manageInfo.getPassword2()));
		} else if (manageInfo.getId() != null && manageInfo.getId() > 0) {
			// manageInfo.setPassword(manageService.getManageById(manageInfo.getId()).getPassword());
		} else {
			super.setErrorMessage("新增后台用户的新密码必须设定!");
			return addManage();
		}

		if (manageInfo.getId() != null && manageInfo.getId() > 0) {
			manageService.updateEntity(manageInfo);
			logService.saveLog(manageInfo, Struts2Util.getManageSession() + " 修改后台用户信息 ", com.game.util.domain.Log.INFO);
		} else {
			// 新增
			manageInfo.setRegip(Struts2Util.getIp());
			manageInfo.setRegisterTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
			manageService.createEntity(manageInfo);
			log.info("新增后台用户信息：" + manageInfo);
			logService.saveLog(manageInfo, Struts2Util.getManageSession() + " 新增后台用户信息 ", com.game.util.domain.Log.INFO);
		}
		return SUCCESS;
	}

	/**
	 * 删除后台用户
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String removeManage() throws Exception {
		if (id > 0) {
			manageService.removeManage(id);
			log.info(Struts2Util.getManageSession() + "删除操作：删除ID为: " + id + " 的后台用户信息!");
		}
		return SUCCESS;
	}

	/**
	 * @see 编辑自己的密码
	 */
	public String updatePassword() throws Exception {
		return "updatePassword";
	}

	/**
	 * @see 保存自己的密码
	 */
	public String savePassword() throws Exception {
		if (!confirmPwd.equals(updatePwd)) {
			setErrorMessage("俩次密码不匹配");
			return "updatePassword";
		}

		Manage m = Struts2Util.getManageSession();
		if (!m.getPassword().equals(MD5.toMD5(rawPwd))) {
			setErrorMessage("原始密码不匹配");
			return "updatePassword";
		}

		m.setPassword(MD5.toMD5(updatePwd));
		manageService.updateEntity(m);
		Struts2Util.removeManageSession();
		log.info("后台用户名为:" + m.getName() + " 修改密码操作!");

		return LOGIN;
	}

	// ~=========================================

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public Page<Manage> getPage() {
		return page;
	}

	public void setPage(Page<Manage> page) {
		this.page = page;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ManageService getManageService() {
		return manageService;
	}

	public String getRawPwd() {
		return rawPwd;
	}

	public String getUpdatePwd() {
		return updatePwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public void setRawPwd(String rawPwd) {
		this.rawPwd = rawPwd;
	}

	public void setUpdatePwd(String updatePwd) {
		this.updatePwd = updatePwd;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Manage getManageInfo() {
		return manageInfo;
	}

	public void setManageInfo(Manage manageInfo) {
		this.manageInfo = manageInfo;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
