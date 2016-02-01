package com.game.util.admin.system.action;

import com.game.util.base.action.BaseAction;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Struts2Util;

/**
 * 环境探针
 */
public class EnvAction extends BaseAction {
	private static final long serialVersionUID = -6784905444750736522L;
	private String serverName;
	private String remoteAddr;
	private String serverTime;
	private String serverInfo;
	private Integer serverPort;
	private String realPath;
	
	public String env() throws Exception{
		serverName = Struts2Util.getRequest().getServerName();
		remoteAddr = Struts2Util.getRequest().getRemoteAddr();
		serverTime = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS);
		serverInfo = Struts2Util.getServletContext().getServerInfo();
		serverPort = Struts2Util.getRequest().getServerPort();
		realPath = Struts2Util.getRealPath(Struts2Util.getRequest().getServletPath());
		return "env";
	}

	public String getServerName() {
		return serverName;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public String getServerTime() {
		return serverTime;
	}

	public String getServerInfo() {
		return serverInfo;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public String getRealPath() {
		return realPath;
	}
	
	
	
	
}

