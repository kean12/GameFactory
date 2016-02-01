package com.game.util.port;

import com.game.assist.server.OnlineUser;
import com.game.util.base.action.BaseAction;
import com.game.util.web.Struts2Util;

public class OnlineUserAction extends BaseAction {

	private static final long serialVersionUID = 3126200009065009224L;
	private String[] online;

	public String getOnlineUser() {
		OnlineUser onlineUser = (OnlineUser) Struts2Util.getApplication().get("onlineUser");
		if (onlineUser != null && onlineUser.getName_string() != null) {
			online = onlineUser.getName_string().split(";;");
		}
		return "getOnlineUser";
	}

	public String[] getOnline() {
		return online;
	}

	public void setOnline(String[] online) {
		this.online = online;
	}

}
