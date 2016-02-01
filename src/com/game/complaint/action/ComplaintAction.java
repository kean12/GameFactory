package com.game.complaint.action;

import java.util.List;

import com.game.complaint.services.ComplaintService;
import com.game.order.services.OrderService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Complaint;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Help;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;

public class ComplaintAction extends BaseAction {
	private static final long serialVersionUID = -1519191228265262018L;
	private OrderService orderService;
	private UserService userService;
	private ComplaintService complaintService;
	private Complaint complaint;
	private User defendant;// 被投诉用户
	private Long orderID;
	private Order order;
	private Integer state;
	private Integer type;
	private String content;
	private List<Complaint> complaintList;
	private Page<Complaint> page;

	/**
	 * 投诉
	 */
	public String complaint() throws Exception {
		try {
			order = orderService.getEntity(Order.class, orderID);
			User user = Struts2Util.getUserSession();
			if (order.getOwner().getId().equals(user.getId())) {
				type = 1;
				defendant = order.getConsumer();
			} else if (order.getConsumer().getId().equals(user.getId())) {
				type = 2;
				defendant = order.getOwner();
			} else {
				throw new Exception("非法操作");
			}
			complaint = complaintService.getComplaint(user.getId(), order.getId());
			if (complaint != null) {
				return "circumstance";// 已投诉 转到详情页面
			}
			return "complaint";
		} catch (Exception e) {
			throw new Exception("您访问的页面不存在！");
		}
	}

	/**
	 * 投诉-提交
	 */
	public String complaintSubmit() throws Exception {
		try {
			order = orderService.getEntity(Order.class, orderID);
			User user = Struts2Util.getUserSession();
			complaint = complaintService.getComplaint(user.getId(), order.getId());
			if (complaint == null) {
				complaint = new Complaint();
				if (order.getOwner().getId().equals(user.getId())) {
					complaint.setType(1);
					complaint.setDefendant(order.getConsumer());
				} else if (order.getConsumer().getId().equals(user.getId())) {
					complaint.setType(2);
					complaint.setDefendant(order.getOwner());
				} else {
					throw new Exception("非法操作");
				}

				complaint.setUser(user); // 投诉用户
				complaint.setOrder(order); // 投诉订单号

				// 将编辑器中的临时图片移到正式文件目录下
				complaint.setContent(Help.moveKindEditorImage(content, "complaint"));// 响应内容
				complaint.setAnswer(null); // 响应内容
				complaint.setState(0); // 状态 0.未处理 1.已处理 2.处理中
				complaint.setApplyTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM)); // 投诉时间
				complaint.setProcessTime(null); // 处理时间
				complaint.setManageName(null); // 处理客服名称

				complaintService.createEntity(complaint);
			}
			return "complaintSubmit";
		} catch (Exception e) {
			throw new Exception("您访问的页面不存在！");
		}
	}

	/**
	 * 发出的投诉
	 */
	public String listComplaint() throws Exception {
		User user = Struts2Util.getUserSession();
		page = complaintService.searchComplaint(user.getId(), null, null, null, 20, super.getGoPage());
		complaintList = page.getResultlist();
		return "listComplaint";
	}

	/**
	 * 接收的投诉
	 */
	public String listDefendant() throws Exception {
		User user = Struts2Util.getUserSession();
		page = complaintService.searchComplaint(null, user.getId(), null, null, 20, super.getGoPage());
		complaintList = page.getResultlist();
		return "listDefendant";
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

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public User getDefendant() {
		return defendant;
	}

	public void setDefendant(User defendant) {
		this.defendant = defendant;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Complaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(List<Complaint> complaintList) {
		this.complaintList = complaintList;
	}

	public Page<Complaint> getPage() {
		return page;
	}

	public void setPage(Page<Complaint> page) {
		this.page = page;
	}

}
