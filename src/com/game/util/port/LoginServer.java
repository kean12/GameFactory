package com.game.util.port;

import com.game.assist.task.ManageModel;
import com.game.util.admin.manage.services.ManageService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Manage;
import com.game.util.web.MD5;
import com.game.util.web.Struts2Util;
import com.game.util.web.TunnelServerUtil;

public class LoginServer extends BaseAction {

	private static final long serialVersionUID = -32059423040850917L;
	private ManageService manageService;

	public void clientLogin() {
		try {
			// 来自客户端的信息
			ManageModel manageModel = (ManageModel) TunnelServerUtil.get(Struts2Util.getRequest());

			Manage manage = manageService.findManageByClientLogin(manageModel.getUsername(), MD5.toMD5(manageModel.getPassword()));
			manageModel = new ManageModel(manage.getName());// 将真实姓名放入对象
			TunnelServerUtil.set(manageModel, Struts2Util.getResponse());// 将处理结果返回到客户端
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ManageService getManageService() {
		return manageService;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

}
