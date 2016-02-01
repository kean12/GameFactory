package com.game.util.port;

import com.game.assist.server.OnlineUser;
import com.game.util.base.action.BaseAction;
import com.game.util.web.Struts2Util;
import com.game.util.web.TunnelServerUtil;

public class OnlineUserServer extends BaseAction {

	private static final long serialVersionUID = -2180918440737558497L;

	public void setOnlineUser() {
		try {
			// 来自客户端的信息
			OnlineUser onlineUser = (OnlineUser) TunnelServerUtil.get(Struts2Util.getRequest());
			Struts2Util.getApplication().put("onlineUser", onlineUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
