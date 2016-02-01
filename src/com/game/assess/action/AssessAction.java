package com.game.assess.action;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.game.assess.services.AssessService;
import com.game.order.services.OrderService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Assess;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.user.services.UserInfoService;
import com.game.util.user.services.UserService;
import com.game.util.web.Arith;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Struts2Util;

public class AssessAction extends BaseAction {
	private static final long serialVersionUID = 4065366131255054720L;
	private Assess assess;
	private Long orderID;
	private OrderService orderService;
	private AssessService assessService;
	private UserService userService;
	private UserInfoService userInfoService;
	private String sellerPositiveRatio; // 好评率
	private String buyerPositiveRatio; // 好评率

	public String execute() throws Exception {
		User user = Struts2Util.getUserSession();
		User owner = orderService.getEntity(Order.class, orderID).getOwner(); // 取得卖家信息
		User consumer = orderService.getEntity(Order.class, orderID).getConsumer(); // 取得买家信息

		assess.setInitiative(user);
		assess.setOrder(orderService.getEntity(Order.class, orderID));
		assess.setTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS));
		Order order = orderService.getEntity(Order.class, orderID);
		if (user.getUsername().equals(consumer.getUsername())) {
			assess.setType(1);
			assess.setPassive(owner);
			if (order.getIsAssess() == 0 || order.getIsAssess() == 1) {
				order.setIsAssess(2);// 买家评价了
			} else if (order.getIsAssess() == 3) {
				order.setIsAssess(4);
			}
		} else {
			if (order.getIsAssess() == 0 || order.getIsAssess() == 1) {
				order.setIsAssess(3);// 卖家评价了
			} else if (order.getIsAssess() == 2) {
				order.setIsAssess(4);
			}
			assess.setType(0);
			assess.setPassive(consumer);
		}
		if (assess.getGrade() == null) {
			assess.setGrade(1);
		}
		if (assess.getContent().length() > 2000) {
			throw new Exception("你评论的字数过多");
		}
		assessService.createEntity(assess); // 添加评论
		orderService.updateEntity(order);
		List<Assess> assessList = assessService.findAssessByOrder(orderID);
		if (assessList.size() == 2) {
			for (Assess as : assessList) {
				if (as.getType() == 1) {// 买家给卖家打分
					int grade = owner.getUserInfo().getSellerCredit() + as.getGrade();
					owner.getUserInfo().setSellerCredit(grade);
					userInfoService.updateUserInfo(owner.getUserInfo());
				} else {// 卖家给买家打分
					int grade = consumer.getUserInfo().getBuyerCredit() + as.getGrade();
					consumer.getUserInfo().setBuyerCredit(grade);
					userInfoService.updateUserInfo(consumer.getUserInfo());
				}
				NumberFormat formatter = new DecimalFormat("0.00");
				List<Object[]> seller = assessService.getAssessByPassive(owner.getId(), 1, null);
				List<Object[]> buyer = assessService.getAssessByPassive(consumer.getId(), 0, null);
				int sellerHp = 0; // 作为卖家好评数
				int sellerZp = 0; // 作为卖家中评数
				int sellerCp = 0; // 作为卖家差评数

				int buyerHp = 0; // 作为买家好评数
				int buyerZp = 0; // 作为买家中评数
				int buyerCp = 0; // 作为买家差评数
				for (int i = 0; i < seller.size(); i++) {
					Object[] obj = (Object[]) seller.get(i);
					if (obj[1].equals(1)) {
						sellerHp = (Integer.parseInt(obj[0].toString()));
					} else if (obj[1].equals(0)) {
						sellerZp = (Integer.parseInt(obj[0].toString()));
					} else {
						sellerCp = (Integer.parseInt(obj[0].toString()));
					}
				}
				for (int i = 0; i < buyer.size(); i++) {
					Object[] obj = (Object[]) buyer.get(i);
					if (obj[1].equals(1)) {
						buyerHp = (Integer.parseInt(obj[0].toString()));
					} else if (obj[1].equals(0)) {
						buyerZp = (Integer.parseInt(obj[0].toString()));
					} else {
						buyerCp = (Integer.parseInt(obj[0].toString()));
					}
				}
				if (sellerHp + sellerZp + sellerCp != 0) {
					Double x = new Double(Arith.intercept((double) sellerHp / (sellerHp + sellerZp + sellerCp) * 100, 2));
					sellerPositiveRatio = formatter.format(x);
				}
				owner.getUserInfo().setSellerPositiveRatio(sellerPositiveRatio + "%");
				userInfoService.updateUserInfo(owner.getUserInfo());
				if (buyerHp + buyerZp + buyerCp != 0) {
					Double x = new Double(Arith.intercept((double) buyerHp / (buyerHp + buyerZp + buyerCp) * 100, 2));
					buyerPositiveRatio = formatter.format(x);
				}
				consumer.getUserInfo().setBuyerPositiveRatio(buyerPositiveRatio + "%");
				userInfoService.updateUserInfo(consumer.getUserInfo());
			}
		}
		return SUCCESS;
	}

	public Assess getAssess() {
		return assess;
	}

	public void setAssess(Assess assess) {
		this.assess = assess;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public AssessService getAssessService() {
		return assessService;
	}

	public void setAssessService(AssessService assessService) {
		this.assessService = assessService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getSellerPositiveRatio() {
		return sellerPositiveRatio;
	}

	public void setSellerPositiveRatio(String sellerPositiveRatio) {
		this.sellerPositiveRatio = sellerPositiveRatio;
	}

	public String getBuyerPositiveRatio() {
		return buyerPositiveRatio;
	}

	public void setBuyerPositiveRatio(String buyerPositiveRatio) {
		this.buyerPositiveRatio = buyerPositiveRatio;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
