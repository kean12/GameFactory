package com.game.util.domain;

/**
 * 商家发货
 */
public class Shipments implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1470436173601992806L;
	private Long id;
	/**
	 * @name 所属订单
	 */
	private Order order;
	/**
	 * @name 图片路径
	 */
	private String src;
	/**
	 * @name 添加时间
	 */
	private String time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
