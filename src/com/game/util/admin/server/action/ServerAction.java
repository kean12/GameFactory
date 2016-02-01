package com.game.util.admin.server.action;

import java.util.List;

import com.game.util.admin.area.services.AreaService;
import com.game.util.admin.server.services.ServerService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Area;
import com.game.util.domain.Server;
import com.game.util.web.Page;

public class ServerAction extends BaseAction {
	private static final long serialVersionUID = -7736071193564247405L;
	private AreaService areaService;
	private ServerService serverService;
	private Server server;
	private Long areaID;
	private Long serverID;
	private List<Server> serverList;
	private Page<Server> page;
	private String serverName;

	/**
	 * @name 获得大类
	 */
	public String listServer() throws Exception {
		if (areaID == null) {
			return "err";
		}

		page = serverService.searchServerByArea(serverName, areaID, 20, super.getGoPage());
		serverList = page.getResultlist();
		return "listserver";
	}

	/**
	 * @name 进入添加更改页面
	 */
	public String add() throws Exception {
		if (serverID != null) {
			server = serverService.getEntity(Server.class, serverID);
			areaID = server.getArea().getId();
		}
		return "add";
	}

	/**
	 * @name 保存菜单
	 */
	public String save() throws Exception {
		if (areaID == null) {
			return "err";
		}
		Server tmpserver = serverService.findServerByName(server.getServerName(), areaID);
		if (server.getId() != null) {
			Server tmp = serverService.getEntity(Server.class, server.getId());
			if (tmpserver == null || tmp.getServerName().equals(server.getServerName())) {
				tmp.setServerName(server.getServerName());
				tmp.setState(server.getState());
				serverService.updateEntity(tmp);
			} else {
				throw new Exception("您所添加的服务器已存在！");
			}
		} else {
			if (tmpserver != null) {
				throw new Exception("您所添加的服务器已存在！");
			}
			server.setId(serverService.getServerMaxID(areaID));
			server.setArea(areaService.getEntity(Area.class, areaID));
			serverService.createEntity(server);
		}
		return "saveserver";
	}

	// *******get/set方法*********
	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public Long getAreaID() {
		return areaID;
	}

	public void setAreaID(Long areaID) {
		this.areaID = areaID;
	}

	public Page<Server> getPage() {
		return page;
	}

	public void setPage(Page<Server> page) {
		this.page = page;
	}

	public ServerService getServerService() {
		return serverService;
	}

	public void setServerService(ServerService serverService) {
		this.serverService = serverService;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Long getServerID() {
		return serverID;
	}

	public void setServerID(Long serverID) {
		this.serverID = serverID;
	}

	public List<Server> getServerList() {
		return serverList;
	}

	public void setServerList(List<Server> serverList) {
		this.serverList = serverList;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
}
