package com.game.util.admin.log.action;

import com.game.util.admin.log.services.LogService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Log;
import com.game.util.web.Page;

public class LogAction extends BaseAction {
	private static final long serialVersionUID = 6653262505204329953L;
	private LogService logService ;
	private Page<Log> page ;
	private Log log ;
	private long id ;
	
	public String allLogs() throws Exception {
		
		page = logService.getAllManage(15, super.getGoPage(), log) ;
		return "logList";
	}
	
	public String checkLog() throws Exception {
		if(id > 0){
			log = logService.getLog(id);
		}
		return "checkLog";
	}
	public String removeLog() throws Exception {
		if(id > 0 ){
			logService.removeLog(id);
		}
		return SUCCESS ;
	}
	
	//~==================================

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Page<Log> getPage() {
		return page;
	}

	public void setPage(Page<Log> page) {
		this.page = page;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
