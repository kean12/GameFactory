package com.game.util.admin.order.action;

import java.util.List;

import com.game.order.services.OrderService;
import com.game.order.services.ShipmentsService;
import com.game.util.admin.manage.services.AssignService;
import com.game.util.admin.manage.services.ManageService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Assign;
import com.game.util.domain.Manage;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.Arith;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.MessageUtil;
import com.game.util.web.Page;
import com.game.util.web.Record;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class RefundmentAction extends BaseAction {
	private static final long serialVersionUID = -1975968398070027075L;
	private ManageService manageService;
	private OrderService orderService;
	private ShipmentsService shipmentsService;
	private UserService userService;
	private AssignService assignService;
	private Assign assign;
	private Long assignID;
	private String orderNum;
	private Page<Assign> page;
	private List<Assign> assignList;
	private Integer orderState = 4;
	private String outcome;// 退款拒绝内容

	/**
	 * 用户申请的寄售订单退款
	 */
	public String refundmentList() throws Exception {
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		if (!name.equals("admin") && !name.equals("trade") && !name.equals("temp")) {
			throw new Exception("您没有此权限");
		}
		Long manageID = null;
		if (!name.equals("admin")) {
			manageID = manage.getId();
		}
		page = assignService.findAssignByRefundment(manageID, orderState, 20, super.getGoPage());
		assignList = page.getResultlist();
		return "refundmentList";
	}

	/**
	 * 退款__订单详情
	 */
	public String refundmentDetail() throws Exception {
		if (assignID == null) {
			throw new Exception("非法操作");
		}
		assign = assignService.getEntity(Assign.class, assignID);
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		if (!name.equals("admin") && (!assign.getManage().getId().equals(manage.getId()) && !assign.getOperate().getId().equals(manage.getId()))) {
			throw new Exception("您没有此权限");
		}
		if (assign != null) {
			assign.setShipmentList(shipmentsService.getShipments(assign.getOrder().getId()));
		}
		return "refundmentDetail";
	}

	/**
	 * 退款处理
	 */
	public String refundmentProcess() throws Exception {
		if (orderState == 5) {
			refundment("同意客户退款。");
		} else if (orderState == 7 && outcome != null) {
			if (assignID != null) {
				assign = assignService.getEntity(Assign.class, assignID);
				Order order = assign.getOrder();
				order.setOutcome(outcome);
				order.setState(7);
				orderService.updateEntity(order);
			}
		}
		return "refundmentProcess";
	}

	/**
	 * 缺货退款
	 */
	public String refundment() throws Exception {
		String info = "由于商户账号缺少库存，退款给买家。";
		// 退款操作
		refundment(info);
		return "refundment";
	}

	/**
	 * 同意退款处理
	 * @param info 备注
	 */
	private void refundment(String info) throws Exception {
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		if (!name.equals("admin") && !name.equals("trade") && !name.equals("temp")) {
			throw new Exception("您没有此权限");
		}
		Long manageID = null;
		if (!name.equals("admin")) {
			manageID = manage.getId();
		}
		if (orderNum != null) {
			assign = assignService.getAssignByOrderNum(orderNum, manageID, 0);
		} else if (assignID != null) {
			assign = assignService.getEntity(Assign.class, assignID);
		} else {
			throw new Exception("非法操作");
		}
		if (!name.equals("admin") && (!assign.getManage().getId().equals(manage.getId()) && !assign.getOperate().getId().equals(manage.getId()))) {
			throw new Exception("您没有此权限");
		}
		if (assign != null) {
			Order order = assign.getOrder();
			if (order.getState() != 0) {
				User consumer = order.getConsumer();
				String assureMoney = order.getAssureMoney();
				String money = consumer.getUserInfo().getMoney();
				String freemoney = consumer.getUserInfo().getFreemoney();

				String tolmoney = "" + Arith.intercept(Arith.add(money, assureMoney), 2);// 交易订单中间金额+用户原余额
				consumer.getUserInfo().setMoney(tolmoney);

				order.setAssureMoney("0.00");
				order.setState(5);// 设置订单状态为 退款成功
				orderService.updateEntity(order);
				userService.updateUser(consumer);

				// 发送站内信
				MessageUtil.toMessage(4, order, DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM), order.getConsumer());

				String tol = "" + Arith.intercept(Arith.add(tolmoney, freemoney), 2);
				String synopsis = "订单编号：" + order.getOrderNum() + " " + order.getTitle() + " 退款";
				Record.set(consumer, null, null, "游戏买卖网", 4, assureMoney, null, tol, synopsis);

				assign.setOperate(manage);
				assign.setState(1);// 设置状态为已处理
				long time1 = System.currentTimeMillis();
				long time2 = DateUtil.convertTimeMillis(assign.getStartTime());
				assign.setCostTime(DateUtil.costTime(time1, time2));
				assign.setEndTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM,time1));
				String remark = assign.getRemark();
				if (remark != null) {
					remark += "|" + info + "操作员：" + manage.getName();
				} else {
					remark = info + "操作员：" + manage.getName();
				}
				assign.setRemark(remark);
				assignService.updateEntity(assign);
			}
		}
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

	public ManageService getManageService() {
		return manageService;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Assign getAssign() {
		return assign;
	}

	public void setAssign(Assign assign) {
		this.assign = assign;
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

	public Long getAssignID() {
		return assignID;
	}

	public void setAssignID(Long assignID) {
		this.assignID = assignID;
	}

	public ShipmentsService getShipmentsService() {
		return shipmentsService;
	}

	public void setShipmentsService(ShipmentsService shipmentsService) {
		this.shipmentsService = shipmentsService;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		if (!Validator.isBlank(outcome)) {
			this.outcome = outcome.trim();
		} else {
			this.outcome = null;
		}
	}

	public AssignService getAssignService() {
		return assignService;
	}

	public void setAssignService(AssignService assignService) {
		this.assignService = assignService;
	}

}
