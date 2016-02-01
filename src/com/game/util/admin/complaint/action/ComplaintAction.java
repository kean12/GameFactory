package com.game.util.admin.complaint.action;

import com.game.complaint.services.ComplaintService;
import com.game.util.admin.log.services.LogService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Complaint;
import com.game.util.domain.Log;
import com.game.util.domain.Manage;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;

public class ComplaintAction extends BaseAction {

	private static final long serialVersionUID = -4901823690019784545L;
	private ComplaintService complaintService;
	private LogService logService;
	private Complaint complaint;
	private Page<Complaint> page;

	public String allComplaints() throws Exception {
		if (null == complaint) {
			page = complaintService.searchComplaint(null, null, null, null, 15, super.getGoPage());
		} else {
			page = complaintService.searchComplaint(complaint.getUser().getId(), complaint.getDefendant().getId(), complaint.getState(), complaint.getType(), 15, super.getGoPage());
		}
		return "allComplaints";
	}

	public String checkComplaint() throws Exception {
		if (complaint != null && complaint.getId() != null && complaint.getId() > 0) {
			complaint = complaintService.getEntity(Complaint.class, complaint.getId());
		}
		return "checkComplaint";
	}

	public String saveComplaint() throws Exception {
		Manage manage = Struts2Util.getManageSession();
		complaint.setManageName(manage.getUsername());
		complaint.setProcessTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
		complaintService.updateEntity(complaint);
		logService.saveLog(complaint, manage + " 修改了投诉建议！", Log.INFO);
		setErrorMessage("被成功修改！");
		return "successUpdate";
	}

	// ~=====================================
	public ComplaintService getComplaintService() {
		return complaintService;
	}

	public void setComplaintService(ComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public Page<Complaint> getPage() {
		return page;
	}

	public void setPage(Page<Complaint> page) {
		this.page = page;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
