package com.game.util.admin.charge.action;

import com.game.util.admin.log.services.LogService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Log;
import com.game.util.domain.Particulars;
import com.game.util.user.services.ParticularsService;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;

/**
 * 对充值明细进行基本操作
 * 
 * @author rplees
 */
public class ChargeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ParticularsService particularsService;
	private LogService logService;
	private Particulars particulars;
	private Page<Particulars> page;
	// /////////搜索条件////////////////
	private String beginTime;
	private String endTime;

	/**
	 * 分页获得帐户明细
	 * @throws Exception
	 */
	public String allParticularses() throws Exception {
		if (particulars == null) {
			page = particularsService.findParticulars(null, null, null, null, null, null, null, 15, super.getGoPage());
		} else if (particulars.getUser() == null) {
			page = particularsService.findParticulars(null, particulars.getOrderNum(), particulars.getRunningNum(), particulars.getBank(), particulars.getType(), beginTime, endTime, 15, super.getGoPage());
		} else {
			page = particularsService.findParticulars(particulars.getUser().getId(), particulars.getOrderNum(), particulars.getRunningNum(), particulars.getBank(), particulars.getType(), beginTime, endTime, 15, super.getGoPage());
		}
		return "allParticularses";
	}

	/**
	 * 根据ID获得帐户明细
	 * @throws Exception
	 */
	public String checkParticulars() throws Exception {
		if (null != particulars && particulars.getId() != null && particulars.getId() > 0) {
			particulars = particularsService.getParticularsById(particulars.getId());
		}
		return "checkParticulars";
	}

	/**
	 * 保存帐户明细
	 * @throws Exception
	 */
	public String saveParticulars() throws Exception {
		particularsService.updateParticulars(particulars);
		logService.saveLog(particulars, Struts2Util.getManageSession() + " 修改了帐户明细！", Log.INFO);
		return "successUpdate";
	}

	// ~============getting setting======================
	public ParticularsService getParticularsService() {
		return particularsService;
	}

	public void setParticularsService(ParticularsService particularsService) {
		this.particularsService = particularsService;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Particulars getParticulars() {
		return particulars;
	}

	public void setParticulars(Particulars particulars) {
		this.particulars = particulars;
	}

	public Page<Particulars> getPage() {
		return page;
	}

	public void setPage(Page<Particulars> page) {
		this.page = page;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
