package com.game.util.domain;

import java.util.List;

/**
 * 寄售订单分配
 */
public class Assign implements Domain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 568783250872928339L;
	private Long id; // 标识列
	private Order order; // 订单表外键
	private Manage manage; // 分配管理员
	private Manage operate; // 实际操作员
	private Integer state; // 状态 是否处理 1.已处理 0.未处理
	private String startTime; // 分配时间
	private String endTime; // 结束时间
	private String costTime; // 花费时间
	private Integer overtime; // 是否超时 0,否 1,是
	private String remark; // 备注
	private String reason; // 申诉内容
	private String outcome; // 处理结果

	private List<Shipments> shipmentList;// 发货截图

	public Assign() {
	};

	public Assign(Order order, Manage manage, String startTime, Integer state,
			String remark) {
		this.order = order;
		this.manage = manage;
		this.startTime = startTime;
		this.state = state;
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Manage getManage() {
		return manage;
	}

	public void setManage(Manage manage) {
		this.manage = manage;
	}

	public Manage getOperate() {
		return operate;
	}

	public void setOperate(Manage operate) {
		this.operate = operate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	public Integer getOvertime() {
		return overtime;
	}

	public void setOvertime(Integer overtime) {
		this.overtime = overtime;
	}

	public List<Shipments> getShipmentList() {
		return shipmentList;
	}

	public void setShipmentList(List<Shipments> shipmentList) {
		this.shipmentList = shipmentList;
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

}
