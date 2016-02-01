package com.game.order.action;

import com.game.util.domain.Order;

public class OrderErrorInfo {
	private String errorMessage;
	private Order order;

	public OrderErrorInfo(String errorMessage, Order order) {
		this.errorMessage = errorMessage;
		this.order = order;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
