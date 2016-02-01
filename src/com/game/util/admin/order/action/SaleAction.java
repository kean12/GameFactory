package com.game.util.admin.order.action;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.game.order.services.OrderService;
import com.game.order.services.ShipmentsService;
import com.game.util.admin.manage.services.AssignService;
import com.game.util.admin.manage.services.ManageService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Assign;
import com.game.util.domain.Manage;
import com.game.util.domain.Order;
import com.game.util.domain.Shipments;
import com.game.util.web.Constant;
import com.game.util.web.CryptTool;
import com.game.util.web.DateUtil;
import com.game.util.web.Help;
import com.game.util.web.MessageUtil;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class SaleAction extends BaseAction {
	private static final long serialVersionUID = 8335821249265805385L;
	private ManageService manageService;
	private AssignService assignService;
	private OrderService orderService;
	private ShipmentsService shipmentsService;
	private Order order;

	private String serial;// bizInfo中的商品编号
	private String orderNum;// 订单号
	private String ownerName;// 商家名称
	private String consumerName;// 客户名称
	private Integer state;// 订单状态
	private String startTime;
	private String endTime;

	private List<Order> orderList;
	private Page<Order> page;

	private Long assignID;
	private Assign assign;

	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	private String allowTypes;

	private String message;

	/**
	 * 寄售订单管理
	 */
	public String saleList() throws Exception {
		page = orderService.findOrderBySale(ownerName, consumerName, orderNum, state, startTime, endTime, 15, super.getGoPage());
		return "saleList";
	}

	/**
	 * 发货管理--首页
	 */
	public String saleIndex() throws Exception {
		return "saleIndex";
	}

	/**
	 * 发货管理--订单详情
	 */
	public String saleDetail() throws Exception {
		if (orderNum == null) {
			throw new Exception("请输入订单编号");
		}

		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();

		Assign assign = null;
		if (name.equals("admin")) {
			assign = assignService.getAssignByOrderNum(orderNum, null, 0);
		} else if (name.equals("trade") || name.equals("temp")) {
			assign = assignService.getAssignByOrderNum(orderNum, manage.getId(), 0);
		}

		if (assign != null) {
			if (!name.equals("admin") && !assign.getManage().getId().equals(manage.getId())) {
				throw new Exception("您没有此权限");
			}
			assignID = assign.getId();
			order = assign.getOrder();
			if (order != null) {
				if (order.getBizInfo().getPassword() != null) {
					order.getBizInfo().setPassword(CryptTool.base64Decode(order.getBizInfo().getPassword()));
				}
				if (order.getBizInfo().getCoded_lock() != null) {
					order.getBizInfo().setCoded_lock(CryptTool.base64Decode(order.getBizInfo().getCoded_lock()));
				}
			}
		}
		return "saleDetail";
	}

	/**
	 * 发货管理--查看宝贝图片
	 */
	public String saleLookPic() throws Exception {
		if (assignID != null) {
			assign = assignService.getEntity(Assign.class, assignID);
			order = assign.getOrder();
		}
		return "saleLookPic";
	}

	/**
	 * 发货管理--查看宝贝介绍
	 */
	public String saleLookInfo() throws Exception {
		if (assignID != null) {
			assign = assignService.getEntity(Assign.class, assignID);
			order = assign.getOrder();
		}
		return "saleLookInfo";
	}

	/**
	 * 发货管理--发货页面
	 */
	public String saleShipments() throws Exception {
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		if (assignID == null || (!name.equals("admin") && !name.equals("trade") && !name.equals("temp"))) {
			throw new Exception("您没有此权限");
		}
		assign = assignService.getEntity(Assign.class, assignID);
		if (!name.equals("admin") && !assign.getManage().getId().equals(manage.getId())) {
			throw new Exception("您没有此权限");
		}
		if (assign.getOrder().getState() == 2) {
			throw new Exception("该订单已发货");
		}
		return "saleShipments";
	}

	/**
	 * 发货管理--上传发货图片
	 */
	public String saleShipmentsSubmit() throws Exception {
		Manage manage = Struts2Util.getManageSession();
		String name = manage.getRole().getName();
		if (assignID == null || (!name.equals("admin") && !name.equals("trade") && !name.equals("temp"))) {
			throw new Exception("您没有此权限");
		}
		assign = assignService.getEntity(Assign.class, assignID);
		if (!name.equals("admin") && !assign.getManage().getId().equals(manage.getId())) {
			throw new Exception("您没有此权限");
		}
		if (assign.getOrder().getState() == 2) {
			throw new Exception("该订单已发货");
		}

		if (upload == null || upload.size() == 0) {
			throw new Exception("请上传发货截图");
		}

		Order order = assign.getOrder();

		String subdirectory = order.getOwner().getUsername() + "\\order";
		Help.chkImage(upload, uploadFileName, Constant.IMAGE_SIZE);//图片验证
		List<String> fileNameList = Help.uploadImageToUserPath(upload, uploadFileName, subdirectory);

		List<Shipments> shipmentsList = null;
		Shipments shipments = null;
		if (!Validator.isEmpty(fileNameList)) {
			long time1 = System.currentTimeMillis();
			String time = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM,time1);
			shipmentsList = new ArrayList<Shipments>();
			for (String str : fileNameList) {
				shipments = new Shipments();
				shipments.setSrc(str);
				shipments.setTime(time);
				shipments.setOrder(order);
				shipmentsList.add(shipments);
			}
			shipmentsService.updateShipments(shipmentsList);
			order.setState(2);// 设置订单状态为 已发货

			// 发送站内信
			MessageUtil.toMessage(3, order, time, order.getConsumer());

			assign.setOperate(manage);
			assign.setState(1);

			long time2 = DateUtil.convertTimeMillis(assign.getStartTime());
			assign.setCostTime(DateUtil.costTime(time1, time2));
			assign.setEndTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS));

			order.setCostTime(assign.getCostTime());

			String tradeTime = assign.getOrder().getBizKind().getTradeTime();
			if (DateUtil.costTime(assign.getStartTime(), assign.getEndTime(), tradeTime) < 0) {
				order.setOvertime(1);
				assign.setOvertime(1);
			} else {
				order.setOvertime(0);
				assign.setOvertime(0);
			}

			orderService.updateEntity(order);
			assignService.updateEntity(assign);

			message = "订单号：" + order.getOrderNum() + " 发货成功！";
		}
		return "saleShipmentsSubmit";
	}

	/**
	 * 发货管理--发货成功
	 */
	public String saleShipmentsSuccess() throws Exception {
		message = URLDecoder.decode(URLEncoder.encode(message, "iso-8859-1"), "utf-8");
		return "saleShipmentsSuccess";
	}

	// *************上传图片**************
	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	// ****************************
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		if (serial != null && !serial.trim().equals("")) {
			this.serial = serial.trim();
		} else {
			this.serial = null;
		}

	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		if (orderNum != null && !orderNum.trim().equals("")) {
			this.orderNum = orderNum.trim();
		} else {
			this.orderNum = null;
		}
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Page<Order> getPage() {
		return page;
	}

	public void setPage(Page<Order> page) {
		this.page = page;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		if (ownerName != null && !ownerName.trim().equals("")) {
			this.ownerName = ownerName.trim();
		} else {
			this.ownerName = null;
		}
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		if (consumerName != null && !consumerName.trim().equals("")) {
			this.consumerName = consumerName.trim();
		} else {
			this.consumerName = null;
		}
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
		if (startTime != null && !startTime.trim().equals("")) {
			this.startTime = startTime.trim();
		} else {
			this.startTime = null;
		}
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		if (endTime != null && !endTime.trim().equals("")) {
			this.endTime = endTime.trim();
		} else {
			this.endTime = null;
		}
	}

	public ManageService getManageService() {
		return manageService;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public Long getAssignID() {
		return assignID;
	}

	public void setAssignID(Long assignID) {
		this.assignID = assignID;
	}

	public Assign getAssign() {
		return assign;
	}

	public void setAssign(Assign assign) {
		this.assign = assign;
	}

	public ShipmentsService getShipmentsService() {
		return shipmentsService;
	}

	public void setShipmentsService(ShipmentsService shipmentsService) {
		this.shipmentsService = shipmentsService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AssignService getAssignService() {
		return assignService;
	}

	public void setAssignService(AssignService assignService) {
		this.assignService = assignService;
	}

}
