package com.game.order.action;

import java.util.List;

import com.game.order.services.OrderService;
import com.game.util.admin.manage.services.AssignService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Assign;
import com.game.util.domain.BizInfo;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.domain.UserRole;
import com.game.util.user.services.UserRoleService;
import com.game.util.user.services.UserService;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

/**
 * 检查订单Action
 */
public class ExamineAction extends BaseAction {
	private static final long serialVersionUID = 3256094340940395704L;
	private OrderService orderService;
	private UserService userService;
	private AssignService assignService;
	private UserRoleService userRoleService;
	private Order order;
	private Assign assign;
	List<UserRole> userRoleList;
	private String orderNum;

	/**
	 * 查看订单信息
	 */
	public String examineOrderInfo() throws Exception {
		if (Validator.isBlank(orderNum)) {
			return "index";
		}
		User user = Struts2Util.getUserSession();
		order = orderService.getOrder(orderNum, user.getId());
		if (order == null) {
			return "index";
		}
		return "examineOrderInfo";
	}

	/**
	 * 查看订单信息--下一步
	 */
	public String examineOrderNext() throws Exception {
		if (Validator.isBlank(orderNum)) {
			return "index";
		}
		assign = assignService.getAssignByOrderNum(orderNum, null, null);
		return "examineOrderNext";
	}

	/**
	 * 查看订单信息--修改
	 */
	public String examineOrderAmend() throws Exception {
		if (Validator.isBlank(orderNum)) {
			return "index";
		}
		User user = Struts2Util.getUserSession();
		order = orderService.getOrder(orderNum, user.getId());
		if (order == null) {
			return "index";
		}
		userRoleList = userRoleService.findUserRoleByUser(user.getId());
		return "examineOrderAmend";
	}

	/**
	 * 查看订单信息--修改--提交
	 */
	public String examineOrderAmendSubmit() throws Exception {
		Struts2Util.removeSession("errorMessage");
		if (Validator.isBlank(orderNum)) {
			return "index";
		}
		User user = Struts2Util.getUserSession();
		Order tmpOrder = orderService.getOrder(orderNum, user.getId());
		if (tmpOrder == null) {
			return "index";
		}
		if (tmpOrder.getState() != 1) {
			throw new Exception("此订单交易状态已改变不能进行修改!");
		}
		BizInfo bizInfo = tmpOrder.getBizInfo();
		if (bizInfo.getSellModel() != 3) {
			tmpOrder.setPlayerRole(order.getPlayerRole());
			tmpOrder.setPlayerGrade(order.getPlayerGrade());
		}
		tmpOrder.setPlayQQ(order.getPlayQQ());
		tmpOrder.setPlayPhoneNum(order.getPlayPhoneNum());
		String errorMess = VerifyOrder.verify(tmpOrder, bizInfo);
		if (errorMess != null) {
			Struts2Util.setSession("errorMessage", new OrderErrorInfo(errorMess, tmpOrder));
			return INPUT;
		}
		orderService.updateEntity(tmpOrder);
		return "examineOrderAmendSubmit";
	}

	// ***********get/set*****************
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public AssignService getAssignService() {
		return assignService;
	}

	public void setAssignService(AssignService assignService) {
		this.assignService = assignService;
	}

	public Assign getAssign() {
		return assign;
	}

	public void setAssign(Assign assign) {
		this.assign = assign;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

}
