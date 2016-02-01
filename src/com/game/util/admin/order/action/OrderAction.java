package com.game.util.admin.order.action;

import com.game.order.services.OrderService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Order;
import com.game.util.web.Page;

public class OrderAction extends BaseAction {

	private static final long serialVersionUID = 3271802840623305192L;
	private Page<Order> page;
	private Order order;
	private OrderService orderService;
	// //////////////搜索条件范围///////////////////
	private String orderTimeStart; // 下单时间开始
	private String orderTimeEnd; // 下单时间结束
	private String succTimeStart; // 成功时间开始
	private String succTimeEnd; // 成功时间结束
	private double sumPriceMin; // 总价格最少
	private double sumPriceMax; // 总价格最多

	public String allOrders() throws Exception {
		page = orderService.getOrderPage(15, super.getGoPage(), orderTimeStart, orderTimeEnd, succTimeStart, succTimeEnd, sumPriceMin, sumPriceMax, order);
		return "allOrders";
	}

	public String checkOrder() throws Exception {
		order = orderService.getEntity(Order.class, order.getId());
		return "checkOrder";
	}

	public String saveOrder() throws Exception {
		if (order.getId() != null && order.getId() > 0) {
			if (order.getGame().getId() == null) {
				order.setGame(null);
			}

			if (order.getServer().getId() == null) {
				order.setServer(null);
			}
			orderService.updateEntity(order);
			setErrorMessage("订单保存成功！");
		}
		return "successupdate";
	}

	// ~================getting setting====================
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public String getOrderTimeStart() {
		return orderTimeStart;
	}

	public void setOrderTimeStart(String orderTimeStart) {
		this.orderTimeStart = orderTimeStart;
	}

	public String getOrderTimeEnd() {
		return orderTimeEnd;
	}

	public void setOrderTimeEnd(String orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
	}

	public String getSuccTimeStart() {
		return succTimeStart;
	}

	public void setSuccTimeStart(String succTimeStart) {
		this.succTimeStart = succTimeStart;
	}

	public String getSuccTimeEnd() {
		return succTimeEnd;
	}

	public void setSuccTimeEnd(String succTimeEnd) {
		this.succTimeEnd = succTimeEnd;
	}

	public double getSumPriceMin() {
		return sumPriceMin;
	}

	public void setSumPriceMin(double sumPriceMin) {
		this.sumPriceMin = sumPriceMin;
	}

	public double getSumPriceMax() {
		return sumPriceMax;
	}

	public void setSumPriceMax(double sumPriceMax) {
		this.sumPriceMax = sumPriceMax;
	}

	public Page<Order> getPage() {
		return page;
	}

	public void setPage(Page<Order> page) {
		this.page = page;
	}

}
