package com.game.util.admin.user.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.game.util.admin.log.services.LogService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Manage;
import com.game.util.domain.User;
import com.game.util.user.services.UserInfoService;
import com.game.util.user.services.UserService;
import com.game.util.web.MD5;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;
import com.game.util.web.TradeMD5;
import com.game.util.web.Validator;

/**
 * @author rplees
 * @see 前台用户后台管理Action 对前台用户进行查看，启用、禁用等操作
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = -5073541967004057454L;
	public static Log log = LogFactory.getLog(UserAction.class);
	private UserService userService;
	private UserInfoService userInfoService;
	private LogService logService;
	private User user;

	private Page<User> page;
	private long id;
	private String updatePwd;
	private String updateApplyPwd;

	/**
	 * 分页获得数据
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String allUsers() throws Exception {
		page = userService.getAllUsers(15, super.getGoPage(), user);
		return "listUsers";
	}

	/**
	 * 查找用户
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String checkUser() throws Exception {
		if (id > 0) {
			user = userService.getUserById(id);
		}
		return "checkUser";
	}

	/**
	 * 保存用户资料
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveUser() throws Exception {
		if (user.getId() != null && user.getId() > 0) {
			if (!Validator.isBlank(updatePwd) && !user.getPassword().equals(TradeMD5.toMD5(updatePwd))) {
				user.setPassword(TradeMD5.toMD5(updatePwd));
			}
			if (!Validator.isBlank(updateApplyPwd) && !user.getUserInfo().getApplyPwd().equals(MD5.toMD5(updateApplyPwd))) {
				user.getUserInfo().setApplyPwd(MD5.toMD5(updateApplyPwd));
			}
			user.getUserInfo().setId(user.getId());
			userService.updateUser(user);
			userInfoService.updateUserInfo(user.getUserInfo());
			Manage m = Struts2Util.getManageSession();
			log.info(m.getUsername() + " 重新设定客户信息:" + user.getUsername());
			// log.info(ToStringBuilder.reflectionToString( m
			// ,ToStringStyle.MULTI_LINE_STYLE)
			// + " 重新设定客户信息:"
			// +
			// ToStringBuilder.reflectionToString(user,ToStringStyle.MULTI_LINE_STYLE))
			// ;
			logService.saveLog(user, m.getUsername() + "重新设定客户信息" + user.getUsername(), com.game.util.domain.Log.INFO);
		}
		return SUCCESS;
	}

	public String searchForEditUser() throws Exception {
		page = userService.getAllUsers(2, super.getGoPage(), user);
		user = null;
		List<User> l = (List<User>) page.getResultlist();
		if (Validator.isEmpty(l) || l.size() > 1) { // 要么没数据，要么不只一条
			setErrorMessage("检索数据时不能精确到某一条!请输入ID或用户名进行检索!");
			return "search4EditUser";
		}
		user = l.get(0);
		return "editUser";
	}

	public String orderByBuyer() throws Exception {
		page = userService.getAllUsers(15, super.getGoPage(), user, "buyer");
		return "buyerGradeUsers";
	}

	public String orderBySeller() throws Exception {
		page = userService.getAllUsers(15, super.getGoPage(), user, "seller");
		return "sellerGradeUsers";
	}

	// ~========================================
	public UserService getUserService() {
		return userService;
	}

	public String getUpdatePwd() {
		return updatePwd;
	}

	public void setUpdatePwd(String updatePwd) {
		this.updatePwd = updatePwd;
	}

	public String getUpdateApplyPwd() {
		return updateApplyPwd;
	}

	public void setUpdateApplyPwd(String updateApplyPwd) {
		this.updateApplyPwd = updateApplyPwd;
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

	public Page<User> getPage() {
		return page;
	}

	public void setPage(Page<User> page) {
		this.page = page;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
