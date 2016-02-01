package com.game.util.admin.order.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.game.order.services.OrderService;
import com.game.util.admin.manage.services.AssignService;
import com.game.util.admin.manage.services.ManageService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Assign;
import com.game.util.domain.Manage;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.web.Arith;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;
import com.game.util.web.Record;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class AssignAction extends BaseAction {
	private static final long serialVersionUID = 4665975481687261768L;
	private static Log log = LogFactory.getLog(AssignAction.class);
	private ManageService manageService;
	private AssignService assignService;
	private OrderService orderService;
	private Page<Assign> page;
	private List<Assign> assignList;
	private List<Manage> manageList;
	private String content;
	private Long manageID;
	private String orderNum;
	private Assign assign;
	private Long assignID;
	private String reason;// 申诉内容
	private String outcome;// 处理结果
	private Integer compensate;// 是否赔付
	private boolean reload = false;// 是否刷新页面

	/**
	 * 寄售订单管理
	 */
	public String list_assign() throws Exception {
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		Long manageID = null;
		if (!name.equals("admin")) {
			manageID = manage.getId();
		}
		page = assignService.findAssignByManage(manageID, 0, 20, super.getGoPage());
		manageList = manageService.findManageByRole("trade", null);
		assignList = page.getResultlist();
		return "list_assign";
	}

	public String reset() throws Exception {
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		if (!name.equals("admin") && !name.equals("temp")) {
			throw new Exception("您没有此权限");
		}
		@SuppressWarnings("unused")
		Long manageID = null;
		if (!name.equals("admin")) {
			manageID = manage.getId();
		}

		if (content != null) {
			String[] arr = content.split(";");
			if (arr.length > 0) {
				String time = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS_MMM);
				Manage admin = null;
				@SuppressWarnings("unused")
				Order order = null;
				Assign assign = null;
				List<Assign> assignList = new ArrayList<Assign>();
				String[] parm = null;
				for (String str : arr) {
					parm = str.split("[|]");
					admin = manageService.getEntity(Manage.class, Long.valueOf(parm[0]));
					order = orderService.getEntity(Order.class, Long.valueOf(parm[1]));
					assign = assignService.getEntity(Assign.class, Long.valueOf(parm[2]));
					assign.setRemark("订单重分配，操作员:" + assign.getManage().getName() + ";原始订单时间：" + assign.getStartTime());
					assign.setStartTime(time);
					assign.setManage(admin);
					assignList.add(assign);
				}
				assignService.saveorupdatecoll(assignList);
			}
		}
		return "reset";
	}

	/**
	 * 超时订单列表
	 */
	public String list_overtime_assign() throws Exception {
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		if (!name.equals("admin")) {
			manageID = manage.getId();
		}
		page = assignService.findAssignByOvertime(manageID, orderNum, 20, super.getGoPage());
		assignList = page.getResultlist();
		manageList = manageService.findManageByRole("trade", null);
		return "list_overtime_assign";
	}

	/**
	 * 超时订单--详细信息
	 */
	public String overtime_detail() throws Exception {
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		if (!name.equals("admin")) {
			manageID = manage.getId();
		}
		if (Validator.isBlank(orderNum)) {
			throw new Exception("非法操作!");
		}
		assign = assignService.getAssignByOvertime(orderNum, manageID, 1);
		compensate = assign.getOrder().getCompensate();
		return "overtime_detail";
	}

	/**
	 * 超时订单--保存(普通权限)
	 */
	public String overtime_detail_save() throws Exception {
		reload = false;// 初始化 默认不刷新
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		assign = assignService.getEntity(Assign.class, assignID);
		orderNum = assign.getOrder().getOrderNum();
		if (name.equals("admin")) {
			if (reason != null && !Validator.isBlank(reason.trim())) {
				assign.setReason(reason);
			}
			if (outcome != null && !Validator.isBlank(outcome.trim())) {
				assign.setOutcome(outcome);
			}
			if (assign.getOrder().getCompensate() == null) {
				if (compensate != null) {
					if (compensate == 1) {// 进行赔付
						assign.getOrder().setCompensate(compensate);
						assign.getOrder().setQuantity("10.00");
						User consumer = assign.getOrder().getConsumer();
						String money = consumer.getUserInfo().getMoney();
						consumer.getUserInfo().setMoney(Arith.intercept(Arith.add(money, "10.00"), 2) + "");
						assignService.updateEntity(assign);
						String total = "" + Arith.intercept(Arith.add(consumer.getUserInfo().getMoney(), consumer.getUserInfo().getFreemoney()), 2);
						Record.set(consumer, null, null, null, 7, "10.0", null, total, "超时赔付");
						String lg = "订单号:" + orderNum 
								+ "\t用户:" + consumer.getUsername() 
								+ "\t原始可用金额:" + money
								+ "\t赔付金额:10.00" 
								+ "\t赔付后可用金额:" + consumer.getUserInfo().getMoney()
								+ "\t操作管理员登录账号:" + manage.getUsername()
								+ "\t操作管理员名称:" + manage.getName();
						log.info(lg);
					} else {// 不赔付
						assign.getOrder().setCompensate(0);
						assignService.updateEntity(assign);

					}
				}
			}
		} else {
			manageID = manage.getId();
			if (!assign.getManage().getId().equals(manageID)) {
				throw new Exception("你没有此权限!");
			}
			if (reason != null && !Validator.isBlank(reason.trim())) {
				assign.setReason(reason);
			}
			assignService.updateEntity(assign);
		}
		reload = true;
		return "overtime_detail_save";
	}

	public ManageService getManageService() {
		return manageService;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public Page<Assign> getPage() {
		return page;
	}

	public void setPage(Page<Assign> page) {
		this.page = page;
	}

	public List<Assign> getAssignList() {
		return assignList;
	}

	public void setAssignList(List<Assign> assignList) {
		this.assignList = assignList;
	}

	public List<Manage> getManageList() {
		return manageList;
	}

	public void setManageList(List<Manage> manageList) {
		this.manageList = manageList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content != null && !content.equals("")) {
			this.content = content.trim();
		} else {
			this.content = null;
		}
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public AssignService getAssignService() {
		return assignService;
	}

	public void setAssignService(AssignService assignService) {
		this.assignService = assignService;
	}

	public Long getManageID() {
		return manageID;
	}

	public void setManageID(Long manageID) {
		this.manageID = manageID;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		if (!Validator.isBlank(orderNum)) {
			this.orderNum = orderNum.trim();
		} else {
			this.orderNum = null;
		}

	}

	public Assign getAssign() {
		return assign;
	}

	public void setAssign(Assign assign) {
		this.assign = assign;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public Long getAssignID() {
		return assignID;
	}

	public void setAssignID(Long assignID) {
		this.assignID = assignID;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}

	public boolean isReload() {
		return reload;
	}

	public Integer getCompensate() {
		return compensate;
	}

	public void setCompensate(Integer compensate) {
		this.compensate = compensate;
	}

}
