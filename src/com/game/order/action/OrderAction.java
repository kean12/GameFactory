package com.game.order.action;

import java.util.List;

import com.game.assess.services.AssessService;
import com.game.bizinfo.services.BizInfoService;
import com.game.order.services.OrderService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Assess;
import com.game.util.domain.BizInfo;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.domain.UserRole;
import com.game.util.union.pay.YeePay;
import com.game.util.user.services.UserRoleService;
import com.game.util.user.services.UserService;
import com.game.util.web.Arith;
import com.game.util.web.AssignUtil;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Help;
import com.game.util.web.MD5;
import com.game.util.web.MessageUtil;
import com.game.util.web.Page;
import com.game.util.web.Record;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class OrderAction extends BaseAction {
	private static final long serialVersionUID = 7203350337102042398L;
	private BizInfoService bizInfoService;
	private OrderService orderService;
	private UserService userService;
	private UserRoleService userRoleService;
	private List<Order> orderList;
	private Long bizInfoID;
	private BizInfo bizInfo;
	private Order order;
	private Long orderID;
	private String flag;
	private Integer mode;
	private String applyPwd;
	private String bank;
	private Integer type;
	private Page<Order> page;

	private Integer state;
	private Integer kind;

	private User user;
	private String runningNum;// 交易流水号
	private AssessService assessService;
	private List<UserRole> userRoleList;
	private String content;

	private String orderNum;

	/**
	 * @name 订单状态更改验证
	 * @param user 登录用户
	 * @param kind 1.商家 0.客户
	 */
	private void updateState() {
		if (orderID == null || state == null || user == null)
			return;
		try {
			order = orderService.getEntity(Order.class, orderID);
			if (order.getOwner().getId().equals(user.getId())) {
				kind = 1;
			} else if (order.getConsumer().getId().equals(user.getId())) {
				kind = 0;
			} else {
				return;
			}

			int buyType = order.getBuyType();// 交易类型（1.寄售、2.担保）

			switch (state) {
			case 1:/** 1 已付款————不需要在此处理 */
				break;
			case 2:/** 2 已发货 */
				if (order.getState() == 1 && kind == 1 && buyType != 1) {
					flag = "shipments";
					// order.setState(2);//发货需要跳转到发货页面 让商家上传凭证
					// orderService.updateOrder(order);
				}
				break;
			case 3:/** 3 交易关闭————只能由商家关闭 */
				if (kind == 1 && buyType != 1) {
					// 需要将金额处理
					order.setState(3);
					orderService.updateEntity(order);
				}
				break;
			case 4:/** 4 退款处理————只能由客户发起申请 */
				if (kind == 0) {
					flag = "refundment";
					// //发货需要跳转到发货页面 让商家上传凭证
					// orderService.updateOrder(order);
					// order.setState(4);
					// orderService.updateOrder(order);
				}
				break;
			case 5:/** 5 退款完成————只能由商家处理 */
				if (order.getState() == 4 && kind == 1 && buyType != 1) {
					String assureMoney = order.getAssureMoney();// 中间金额
					User consumer = order.getConsumer();
					String consumerMoney = consumer.getUserInfo().getMoney();
					consumer.getUserInfo().setMoney("" + Arith.intercept(Arith.add(consumerMoney, assureMoney), 2));
					order.setState(5);
					order.setAssureMoney("0.00");
					orderService.updateEntity(order);
					userService.updateUser(consumer);
					MessageUtil.toMessage(4, order, DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM), order.getConsumer());// 发送站内信

					String tol = "" + Arith.intercept(Arith.add(consumer.getUserInfo().getMoney(), consumer.getUserInfo().getFreemoney()), 2);
					String synopsis = "订单编号：" + order.getOrderNum() + " " + order.getTitle() + " 退款";
					Record.set(consumer, null, null, null, 4, assureMoney, null, tol, synopsis);
				}
				break;
			case 6:/** 6 交易成功————必须是已发货的订单 且由买家执行 */
				if ((order.getState() == 2 || order.getState() == 4 || order.getState() == 7) && kind == 0) {
					User owner = order.getOwner();
					String assureMoney = order.getAssureMoney();// 中间金额
					owner.getUserInfo().setMoney(Arith.intercept(Arith.add(assureMoney, owner.getUserInfo().getMoney()), 2) + "");
					order.setState(6);
					order.setAssureMoney("0.00");
					order.setSuccTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));
					userService.updateUser(owner);
					orderService.updateEntity(order);

					String tol = "" + Arith.intercept(Arith.add(owner.getUserInfo().getMoney(), owner.getUserInfo().getFreemoney()), 2);
					String synopsis = "订单编号：" + order.getOrderNum() + " " + order.getTitle() + " 交易收款";
					Record.set(owner, null, null, null, 2, assureMoney, null, tol, synopsis);
				}
				break;
			case 7:/** 7拒绝退款————必须是申请退款的订单 且由卖家执行 */
				if (order.getState() == 4 && kind == 1 && buyType != 1) {
					order.setState(7);
					orderService.updateEntity(order);
					MessageUtil.toMessage(5, order, DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM), order.getConsumer());// 发送站内信
				}
				break;
			default:/** 0 待付款————不需要在此处理 */
				break;
			}
		} catch (Exception e) {
			return;
		}
	}

	public void assessState(Order order) throws Exception {
		List<Assess> assessList = assessService.findAssessByOrder(order.getId());
		if (assessList.size() == 0) {
			order.setIsAssess(1);// 双方都没有评价。
		} else if (assessList.size() == 2) {
			order.setIsAssess(4);// 双方都已评价。
		} else {
			for (Assess as : assessList) {
				user = Struts2Util.getUserSession();
				User consumer = orderService.getEntity(Order.class, order.getId()).getConsumer(); // 取得买家信息
				User owner = orderService.getEntity(Order.class, order.getId()).getOwner(); // 取得买家信息
				if (user.getUsername().equals(consumer.getUsername())) {
					if (as.getType() == 1) {
						order.setIsAssess(3);// 我（买家）评了，对方（卖家）没有评。
					} else {
						order.setIsAssess(2);// 对方（卖家）已评，我（买家）没有评。
					}
				}
				if (user.getUsername().equals(owner.getUsername())) {
					if (as.getType() == 0) {
						order.setIsAssess(3);// 我（卖家）评了，对方（买家）没有评。
					} else {
						order.setIsAssess(2);// 对方（买家）已评，我（卖家）没有评。
					}
				}
			}
		}
	}

	/**
	 * 商家—-卖出的宝贝
	 */
	public String sold() throws Exception {
		user = Struts2Util.getUserSession();
		updateState();
		// 进入发货页面
		if (flag != null && flag.equals("shipments")) {
			return "shipments";
		}
		user = userService.getUserById(user.getId());
		Struts2Util.setUserSession(user);
		page = orderService.searchOrder(user.getId(), type, 1, 15, super.getGoPage());
		orderList = page.getResultlist();
		for (Order o : orderList) {
			assessState(o);
		}
		return "sold";
	}

	/** 0 待付款|1 已付款|2 已发货3 交易关闭|4 退款处理|5 退款完成|6 交易成功--%> */
	/**
	 * 客户—-买到的宝贝
	 */
	public String bought() throws Exception {
		user = Struts2Util.getUserSession();
		updateState();
		// 进入发货页面
		if (flag != null && flag.equals("refundment")) {
			assessState(order);
			return "refundment";
		}
		page = orderService.searchOrder(user.getId(), type, 0, 15, super.getGoPage());
		orderList = page.getResultlist();
		for (Order o : orderList) {
			assessState(o);
		}
		return "bought";
	}

	/**
	 * 客户—-退款理由提交
	 */
	public String refundmentSubmit() throws Exception {
		if (orderID == null || content == null) {
			throw new Exception("非法操作");
		}
		user = Struts2Util.getUserSession();
		order = orderService.getOrder(null, user.getId(), orderID);
		if (order == null || order.getReason() != null && !order.getReason().equals("")) {
			throw new Exception("退款申请正在处理中，请耐心等待。");
		}
		if (order.getState() != 2) {
			throw new Exception("退款申请正在处理中，请耐心等待。");
		}
		// 将编辑器中的临时图片移到正式文件目录下
		order.setReason(Help.moveKindEditorImage(content, "refundment"));
		order.setState(4);
		orderService.updateEntity(order);
		return "refundmentSubmit";
	}

	/**
	 * 交易详情
	 * @throws Exception
	 */
	public String transactionDetail() throws Exception {
		user = Struts2Util.getUserSession();
		if (state == null) {
			state = 100;// 不执行订单状态更改
		}
		updateState();
		assessState(order);
		return "transactionDetail";
	}

	/**
	 * 客户—-购买——填写订单
	 */
	public String buyNow() throws Exception {
		OrderErrorInfo orderErrorInfo = (OrderErrorInfo) Struts2Util.getSession("errorMessage");
		if (orderErrorInfo != null) {
			Struts2Util.getRequest().setAttribute("errorMessage", orderErrorInfo);
			Struts2Util.removeSession("errorMessage");
		}
		bizInfo = bizInfoService.getEntity(BizInfo.class, bizInfoID);
		if (bizInfo == null || bizInfo.getIsBuy() == 0) {
			throw new Exception("该商品不存在或已经下架！");
		}
		user = Struts2Util.getUserSession();
		userRoleList = userRoleService.findUserRoleByUser(user.getId());
		return "buyNow";
	}

	public String buyNowSubmit() throws Exception {
		bizInfo = bizInfoService.getEntity(BizInfo.class, bizInfoID);
		if (bizInfo == null || bizInfo.getStock().equals("") || bizInfo.getStock().equals("0") || bizInfo.getIsBuy() == 0) {
			throw new Exception("此宝贝已下架货不存在！");
		}

		String errorMess = VerifyOrder.verify(order, bizInfo);
		if (errorMess != null) {
			Struts2Util.setSession("errorMessage", new OrderErrorInfo(errorMess, order));
			return INPUT;
		}
		User consumer = Struts2Util.getUserSession();
		if (bizInfo.getOwner().getId().equals(consumer.getId())) {
			throw new Exception("不能购买自己的商品！");
		}
		order.setBizInfo(bizInfo);
		order.setGame(bizInfo.getGame());
		order.setServer(bizInfo.getServer());
		order.setTitle(bizInfo.getTitle());
		order.setConsumer(consumer); // 购买者
		order.setOwner(bizInfo.getOwner()); // 设置商家User 以免挂卖信息删除
		order.setState(0); // 初始化订单状态 0.表示等待买家付款
		order.setSellQQ(bizInfo.getQq()); // 商家QQ
		order.setSellPhoneNum(bizInfo.getPhoneNum()); // 商家电话
		order.setAssureMoney("0"); // 初始化中间金额
		order.setOrderTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM));// 下单时间
		order.setPrice(bizInfo.getPrice()); // 物品单价
		double sumPrice = Arith.mul(order.getPrice(), order.getBuyBum());
		sumPrice = Arith.intercept(sumPrice, 2);
		order.setSumPrice(String.valueOf(sumPrice)); // 总价格
		order.setIsAssess(0); // 初始化评论状态
		order.setBuyType(bizInfo.getBuyType());// 寄售还是担保
		if (bizInfo.getSellModel() == 1) {// 交易地点
			order.setSite(bizInfo.getSite());
		} else if (bizInfo.getSellModel() == 3) {
			order.setSite("账号交易");
		} else {
			order.setSite("邮寄");
		}
		int stock = Integer.parseInt(bizInfo.getStock());
		int buyBum = Integer.parseInt(order.getBuyBum());
		if (stock - buyBum < 0) {
			throw new Exception("当前剩余库存：" + stock + "件");
		} else if (stock - buyBum == 0) {
			bizInfo.setStock("0");
			bizInfo.setIsBuy(0);
		} else {
			bizInfo.setStock((stock - buyBum) + "");
		}
		order.setOvertime(0);// 是否超时 默认否
		order.setCostTime(null);
		order.setBizKind(order.getBizInfo().getBizKind());
		orderService.createOrder(order);
		bizInfoService.updateEntity(bizInfo);
		MessageUtil.toMessage(1, order, order.getOrderTime(), consumer);// 发送站内信

		// 存储用户收获角色名
		UserRole userRole = userRoleService.getUserRole(consumer.getId(), order.getPlayerRole());
		if (userRole == null) {
			userRole = new UserRole();
			userRole.setUser(consumer);
			userRole.setRoleName(order.getPlayerRole());
			userRole.setRemark(null);
			userRoleService.createUserRole(userRole);
		}
		return "buyNowSubmit";
	}

	/**
	 * 客户—-购买——支付页面
	 */
	public String tradePayment() throws Exception {
		if (orderID == null) {
			throw new Exception("你访问的页面不存在！");
		}
		User user = Struts2Util.getUserSession();
		user = userService.getUserById(user.getId());
		Struts2Util.setUserSession(user);
		order = orderService.getEntity(Order.class, orderID);
		if (order == null) {
			throw new Exception("你访问的页面不存在！");
		}
		if (Double.parseDouble(order.getSumPrice()) > Double.parseDouble(user.getUserInfo().getMoney())) {
			flag = "false";
		} else {
			flag = "true";
		}
		mode = 1;
		return "tradePayment";
	}

	/**
	 * 客户—-购买——支付——进行付款
	 */
	public String tradePaymentSubmit() throws Exception {
		User user = Struts2Util.getUserSession();
		user = userService.getUserById(user.getId());
		if (applyPwd == null || user.getUserInfo().getApplyPwd() == null || !user.getUserInfo().getApplyPwd().equals(MD5.toMD5(applyPwd))) {
			throw new Exception("支付密码错误"); // ***********************更改为页面alert**************************
		}
		order = orderService.getOrder(null, user.getId(), orderID);
		if (order == null) {
			throw new Exception("非法操作");
		}
		if (order.getState() != 0) {// 订单状态为已支付时不能重复支付
			throw new Exception("该订单已支付");
		}
		double sumPrice = Float.parseFloat(order.getSumPrice());
		double money = Float.parseFloat(user.getUserInfo().getMoney());
		if (mode == 1) {
			if (sumPrice > money) {
				Struts2Util.setUserSession(user);
				YeePay.setYeePayRequest(order, sumPrice - money, bank, 6);
				return "yeepay";
			} else {
				user.getUserInfo().setMoney(Arith.intercept(Arith.sub(money, sumPrice), 2) + ""); // 更新账户可用金额
				order.setAssureMoney(order.getSumPrice()); // 付款金额保存到中间金额
				order.setState(1); // 这只订单状态为 已付款
				userService.updateUser(user);
				orderService.updateEntity(order);
				MessageUtil.toMessage(2, order, DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM), order.getConsumer());// 发送站内信
				String total = "" + Arith.intercept(Arith.add(user.getUserInfo().getMoney(), user.getUserInfo().getFreemoney()), 2);
				String synopsis = "订单编号：" + order.getOrderNum() + " " + order.getTitle();
				runningNum = Record.set(user, null, null, null, 1, null, "-" + sumPrice, total, synopsis);
				if (order.getBuyType() == 1) {// 寄售交易分配给交易员
					AssignUtil.to_assign(order);
				}
				this.orderNum = order.getOrderNum();
			}
		} else {
			Struts2Util.setUserSession(user);
			YeePay.setYeePayRequest(order, sumPrice, bank, 6);
			return "yeepay";
		}
		Struts2Util.setUserSession(user);
		return "tradePaymentSubmit";
	}

	/**
	 * 客户—-购买——支付——支付成功
	 */
	public String tradePaymentScuuess() {
		return "tradePaymentScuuess";
	}

	// ****************************
	public BizInfoService getBizInfoService() {
		return bizInfoService;
	}

	public void setBizInfoService(BizInfoService bizInfoService) {
		this.bizInfoService = bizInfoService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Long getBizInfoID() {
		return bizInfoID;
	}

	public void setBizInfoID(Long bizInfoID) {
		this.bizInfoID = bizInfoID;
	}

	public BizInfo getBizInfo() {
		return bizInfo;
	}

	public void setBizInfo(BizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getApplyPwd() {
		return applyPwd;
	}

	public void setApplyPwd(String applyPwd) {
		if (!Validator.isBlank(applyPwd)) {
			this.applyPwd = applyPwd;
		} else {
			this.applyPwd = null;
		}

	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		if (!Validator.isBlank(bank)) {
			this.bank = bank.trim();
		} else {
			this.bank = bank;
		}
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Page<Order> getPage() {
		return page;
	}

	public void setPage(Page<Order> page) {
		this.page = page;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public AssessService getAssessService() {
		return assessService;
	}

	public void setAssessService(AssessService assessService) {
		this.assessService = assessService;
	}

	public String getRunningNum() {
		return runningNum;
	}

	public void setRunningNum(String runningNum) {
		this.runningNum = runningNum;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (!Validator.isBlank(content)) {
			this.content = content;
		} else {
			this.content = null;
		}
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

}
