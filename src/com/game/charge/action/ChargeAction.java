package com.game.charge.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.game.util.base.action.BaseAction;
import com.game.util.domain.Order;
import com.game.util.domain.Particulars;
import com.game.util.domain.User;
import com.game.util.union.pay.YeePay;
import com.game.util.user.services.ParticularsService;
import com.game.util.web.Page;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class ChargeAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7374845195975784459L;
	private String bank, money;
	private Page<Particulars> page;
	private ParticularsService particularsService;
	private List<Particulars> particularsList;
	private String orderNum, runningNum, beginTime, endTime;

	/**
	 * @name 进入充值页面
	 */
	public String deposit() throws Exception {
		return "deposit";
	}

	/**
	 * @name 确认充值
	 */
	public String depositVerify() throws Exception {
		if (!Validator.isDeposit(money)) {
			throw new Exception("请您正确填写充值金额！");
		}
		return "depositVerify";
	}

	// 进行充值
	public String charge() throws Exception {
		Order order = new Order();
		order.setTitle("在线充值");
		order.setOrderNum("在线充值");
		YeePay.setYeePayRequest(order, Double.parseDouble(money), bank, 3);
		return "yeepay";
	}

	/**
	 * @name 充值记录
	 */
	public String record() throws Exception {
		if (!Validator.isBlank(bank)) {
			bank = URLDecoder.decode(bank, "utf-8");
		} else {
			bank = null;
		}
		User user = Struts2Util.getUserSession();
		page = particularsService.findParticulars(user.getId(), orderNum, runningNum, bank, 3, beginTime, endTime, 20, super.getGoPage());
		particularsList = page.getResultlist();
		return "record";
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) throws UnsupportedEncodingException {
		if (!Validator.isBlank(orderNum)) {
			this.orderNum = URLDecoder.decode(orderNum, "utf-8");
		} else {
			this.orderNum = null;
		}

	}

	public String getRunningNum() {
		return runningNum;
	}

	public void setRunningNum(String runningNum) throws UnsupportedEncodingException {
		if (!Validator.isBlank(runningNum)) {
			this.runningNum = URLDecoder.decode(runningNum, "utf-8");
		} else {
			this.runningNum = null;
		}
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Page<Particulars> getPage() {
		return page;
	}

	public void setPage(Page<Particulars> page) {
		this.page = page;
	}

	public ParticularsService getParticularsService() {
		return particularsService;
	}

	public void setParticularsService(ParticularsService particularsService) {
		this.particularsService = particularsService;
	}

	public List<Particulars> getParticularsList() {
		return particularsList;
	}

	public void setParticularsList(List<Particulars> particularsList) {
		this.particularsList = particularsList;
	}

}
