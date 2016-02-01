package com.game.util.system.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.game.order.services.OrderService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Order;
import com.game.util.domain.User;
import com.game.util.union.pay.YeePay;
import com.game.util.user.services.UserService;
import com.game.util.web.Arith;
import com.game.util.web.AssignUtil;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.MessageUtil;
import com.game.util.web.Record;
import com.game.util.web.Struts2Util;
import com.game.util.web.YeePayConfig;
import com.yeepay.PaymentForOnlineService;

public class YeePayAction extends BaseAction {
	private static final long serialVersionUID = 7188929707538962213L;
	private String p4_Cur = "CNY";// 交易币种-人民币
	private String keyValue = YeePayConfig.get("keyValue"); // 商家密钥
	private String nodeAuthorizationURL = YeePayConfig.get("yeepayCommonReqURL"); // 交易请求地址
	private String p1_MerId = YeePayConfig.get("p1_MerId");// 商户编号

	private String p0_Cmd = "Buy";// 在线支付请求，固定值 ”Buy”
	private String p2_Order = null;// 商户订单号
	private String p3_Amt = null;// 支付金额
	private String p5_Pid = null;// 商品名称
	private String p6_Pcat = null;// 商品种类
	private String p7_Pdesc = null;// 商品描述
	private String p8_Url = null;// 商户接收支付成功数据的地址
	private String p9_SAF = "0";// 需要填写送货信息 0：不需要 1:需要
	private String pa_MP = null;// 商户扩展信息
	private String pd_FrpId = null;// 支付通道编码
	private String pr_NeedResponse = "0";// 是否需要应答机制
	private String hmac = null;

	private String r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order,r7_Uid, r8_MP, r9_BType;

	private String flag = null;

	private OrderService orderService;
	private UserService userService;
	private User user;

	private String orderNum;

	/**
	 * 易宝支付
	 */
	public String pay() throws Exception {
		// 1.交易付款 2.交易收款 3.在线充值 4.退款 5.提现 6.差额入账
		HttpServletRequest request = Struts2Util.getRequest();

		// 获得服务器地址
		StringBuffer url = request.getRequestURL();
		String localAddr = url.substring(0, url.indexOf("/", 8));
		String path = "/pay/yeepay.shtml";
		// 商户接收支付成功数据的地址

		p8_Url = localAddr + path;

		p2_Order = (String) request.getAttribute("p2_Order");// 订单号
		p3_Amt = request.getAttribute("p3_Amt").toString();// 金额
		p5_Pid = (String) request.getAttribute("p5_Pid");// 商品名称
		p6_Pcat = (String) request.getAttribute("p6_Pcat");// 种类
		p7_Pdesc = (String) request.getAttribute("p7_Pdesc");// 描述
		pa_MP = (String) request.getAttribute("pa_MP");// 扩展信息
		pd_FrpId = (String) request.getAttribute("pd_FrpId");// 支付通道

		hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(p0_Cmd,
				p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);// 交易签名串

		return "execute";
	}

	public String respond() throws Exception {
		boolean isOK = false;
		isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);

		if (isOK) {
			if (r1_Code.equals("1")) {
				if (!Record.chk_Recoed(r6_Order, r2_TrxId)) {
					user = Struts2Util.getUserSession();
					user = userService.getUserById(user.getId());
					String[] str_arr = r8_MP.split("[|]");
					if (str_arr[0].equals("CE")) {
						deposit(str_arr[1]);// 充值记录
						updateOrder(str_arr[2]);
					} else if (str_arr[0].equals("CZ")) {
						deposit(str_arr[1]);// 充值记录
					}
					Struts2Util.setUserSession(user);
				}
			}
		} else {
			throw new Exception("非法操作！");
		}

		return "pay_success";
	}

	// 存钱
	public void deposit(String place) throws Exception {
		user.getUserInfo().setMoney("" + Arith.add(user.getUserInfo().getMoney(), r3_Amt));// 充值后账户可用余额
		double totalString = Arith.add(user.getUserInfo().getMoney(), user.getUserInfo().getFreemoney());
		String total = "" + Arith.intercept(totalString, 2);
		userService.updateUser(user);
		Record.set(user, r6_Order, r2_TrxId, place, 3, r3_Amt, null, total, "在线充值");
	}

	// 交易支付更改订单状态
	public void updateOrder(String orderNum) throws Exception {

		Order order = orderService.getOrder(orderNum, user.getId());

		user.getUserInfo().setMoney(Arith.intercept(Arith.sub(user.getUserInfo().getMoney(), order.getSumPrice()), 2) + ""); // 更新账户可用金额
		order.setAssureMoney(order.getSumPrice()); // 付款金额保存到中间金额
		order.setState(1); // 这只订单状态为 已付款
		orderService.updateEntity(order);
		userService.updateUser(user);

		// 发送站内信
		MessageUtil.toMessage(2, order, DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM), order.getConsumer());

		String total = "" + Arith.intercept(Arith.add(user.getUserInfo().getMoney(), user.getUserInfo().getFreemoney()), 2);
		String synopsis = "订单编号：" + order.getOrderNum() + " " + order.getTitle();
		Record.set(user, null, null, YeePay.bourse("游戏买卖网"), 1, null, "-" + order.getSumPrice(), total, synopsis);

		if (order.getBuyType() == 1) {// 寄售交易分配给交易员
			AssignUtil.to_assign(order);
		}

		this.orderNum = orderNum;// 传送订单编号
	}

	// /****************************************************
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getP0_Cmd() {
		return p0_Cmd;
	}

	public void setP0_Cmd(String p0Cmd) {
		p0_Cmd = p0Cmd;
	}

	public String getP2_Order() {
		return p2_Order;
	}

	public void setP2_Order(String p2Order) {
		p2_Order = p2Order;
	}

	public String getP3_Amt() {
		return p3_Amt;
	}

	public void setP3_Amt(String p3Amt) {
		p3_Amt = p3Amt;
	}

	public String getP5_Pid() {
		return p5_Pid;
	}

	public void setP5_Pid(String p5Pid) {
		p5_Pid = p5Pid;
	}

	public String getP6_Pcat() {
		return p6_Pcat;
	}

	public void setP6_Pcat(String p6Pcat) {
		p6_Pcat = p6Pcat;
	}

	public String getP7_Pdesc() {
		return p7_Pdesc;
	}

	public void setP7_Pdesc(String p7Pdesc) {
		p7_Pdesc = p7Pdesc;
	}

	public String getP8_Url() {
		return p8_Url;
	}

	public void setP8_Url(String p8Url) {
		p8_Url = p8Url;
	}

	public String getP9_SAF() {
		return p9_SAF;
	}

	public void setP9_SAF(String p9SAF) {
		p9_SAF = p9SAF;
	}

	public String getPa_MP() {
		return pa_MP;
	}

	public void setPa_MP(String paMP) {
		pa_MP = paMP;
	}

	public String getPd_FrpId() {
		return pd_FrpId;
	}

	public void setPd_FrpId(String pdFrpId) {
		pd_FrpId = pdFrpId;
	}

	public String getPr_NeedResponse() {
		return pr_NeedResponse;
	}

	public void setPr_NeedResponse(String prNeedResponse) {
		pr_NeedResponse = prNeedResponse;
	}

	public String getHmac() {
		return hmac;
	}

	public void setHmac(String hmac) {
		this.hmac = hmac;
	}

	public String getP4_Cur() {
		return p4_Cur;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public String getNodeAuthorizationURL() {
		return nodeAuthorizationURL;
	}

	public String getP1_MerId() {
		return p1_MerId;
	}

	public String getR0_Cmd() {
		return r0_Cmd;
	}

	public void setR0_Cmd(String r0Cmd) throws UnsupportedEncodingException {
		if (r0Cmd != null) {
			r0_Cmd = URLDecoder.decode(URLEncoder.encode(r0Cmd, "iso-8859-1"),
					"GBK");
		} else {
			r0_Cmd = r0Cmd;
		}
	}

	public String getR1_Code() {
		return r1_Code;
	}

	public void setR1_Code(String r1Code) throws UnsupportedEncodingException {
		if (r1Code != null) {
			r1_Code = URLDecoder.decode(
					URLEncoder.encode(r1Code, "iso-8859-1"), "GBK");
		} else {
			r1_Code = r1Code;
		}
	}

	public String getR2_TrxId() {
		return r2_TrxId;
	}

	public void setR2_TrxId(String r2TrxId) throws UnsupportedEncodingException {
		if (r2TrxId != null) {
			r2_TrxId = URLDecoder.decode(URLEncoder.encode(r2TrxId,
					"iso-8859-1"), "GBK");
		} else {
			r2_TrxId = r2TrxId;
		}
	}

	public String getR3_Amt() {
		return r3_Amt;
	}

	public void setR3_Amt(String r3Amt) throws UnsupportedEncodingException {
		if (r3Amt != null) {
			r3_Amt = URLDecoder.decode(URLEncoder.encode(r3Amt, "iso-8859-1"),
					"GBK");
		} else {
			r3_Amt = r3Amt;
		}
	}

	public String getR4_Cur() {
		return r4_Cur;
	}

	public void setR4_Cur(String r4Cur) throws UnsupportedEncodingException {
		if (r4Cur != null) {
			r4_Cur = URLDecoder.decode(URLEncoder.encode(r4Cur, "iso-8859-1"),
					"GBK");
		} else {
			r4_Cur = r4Cur;
		}
	}

	public String getR5_Pid() {
		return r5_Pid;
	}

	public void setR5_Pid(String r5Pid) throws UnsupportedEncodingException {
		if (r5Pid != null) {
			r5_Pid = URLDecoder.decode(URLEncoder.encode(r5Pid, "iso-8859-1"),
					"GBK");
		} else {
			r5_Pid = r5Pid;
		}
	}

	public String getR6_Order() {
		return r6_Order;
	}

	public void setR6_Order(String r6Order) throws UnsupportedEncodingException {
		if (r6Order != null) {
			r6_Order = URLDecoder.decode(URLEncoder.encode(r6Order,
					"iso-8859-1"), "GBK");
		} else {
			r6_Order = r6Order;
		}
	}

	public String getR7_Uid() {
		return r7_Uid;
	}

	public void setR7_Uid(String r7Uid) throws UnsupportedEncodingException {
		if (r7Uid != null) {
			r7_Uid = URLDecoder.decode(URLEncoder.encode(r7Uid, "iso-8859-1"),
					"GBK");
		} else {
			r7_Uid = r7Uid;
		}
	}

	public String getR8_MP() {
		return r8_MP;
	}

	public void setR8_MP(String r8MP) throws UnsupportedEncodingException {
		if (r8MP != null) {
			r8_MP = URLDecoder.decode(URLEncoder.encode(r8MP, "iso-8859-1"),
					"GBK");
		} else {
			r8_MP = r8MP;
		}
	}

	public String getR9_BType() {
		return r9_BType;
	}

	public void setR9_BType(String r9BType) throws UnsupportedEncodingException {
		if (r9BType != null) {
			r9_BType = URLDecoder.decode(URLEncoder.encode(r9BType,
					"iso-8859-1"), "GBK");
		} else {
			r9_BType = r9BType;
		}
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

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

}