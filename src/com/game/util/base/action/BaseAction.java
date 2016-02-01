package com.game.util.base.action;

import com.game.util.domain.Manage;
import com.game.util.domain.User;
import com.game.util.web.Constant;
import com.game.util.web.Struts2Util;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	private static final long serialVersionUID = -8414438978156820664L;
	private String errorMessage = null;
	private Boolean isSuccess = false;
	private String injunction;// 指令ָ��
	private int goPage = 1; // ָ默认首页

	public User getUserSession() {
		return Struts2Util.getUserSession();
	}
	public Manage getManageSession() {
		return Struts2Util.getManageSession();
	}
	public String getCtx() {
		return Constant.CONTEXT_PATH;
	}
	public int getGoPage() {
		return goPage;
	}
	public void setGoPage(int goPage) {
		this.goPage = goPage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getInjunction() {
		return injunction;
	}
	public void setInjunction(String injunction) {
		this.injunction = injunction;
	}
}
