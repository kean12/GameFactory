package com.game.order.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.game.order.services.OrderService;
import com.game.order.services.ShipmentsService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Order;
import com.game.util.domain.Shipments;
import com.game.util.domain.User;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Help;
import com.game.util.web.MessageUtil;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class ShipmentsAction extends BaseAction {
	private static final long serialVersionUID = 3754483091947715919L;
	private OrderService orderService;
	private ShipmentsService shipmentsService;
	private Long orderID;

	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	private String allowTypes;

	/**
	 * @name 商家发货——上传图片
	 */
	public String shipments() throws Exception {
		if (orderID == null) {
			return INPUT;
		}
		User user = Struts2Util.getUserSession();
		Order order = orderService.getEntity(Order.class, orderID);
		if (order.getState() != 1 || !order.getOwner().getId().equals(user.getId())) {
			return INPUT;
		}
		if (upload == null || upload.size() == 0) {
			throw new Exception("请上传交易截图！");
		}

		// 文件上传
		String subdirectory = user.getUsername() + "\\order";
		Help.chkImage(upload, uploadFileName, Constant.IMAGE_SIZE);//图片验证
		List<String> fileNameList = Help.uploadImageToUserPath(upload, uploadFileName, subdirectory);

		List<Shipments> shipmentsList = null;
		Shipments shipments = null;
		if (!Validator.isEmpty(fileNameList)) {
			String time = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM);
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
			orderService.updateEntity(order);
			MessageUtil.toMessage(3, order, time, order.getConsumer());
		}
		return SUCCESS;
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

	public ShipmentsService getShipmentsService() {
		return shipmentsService;
	}

	public void setShipmentsService(ShipmentsService shipmentsService) {
		this.shipmentsService = shipmentsService;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

}
