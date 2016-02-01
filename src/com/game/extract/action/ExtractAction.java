package com.game.extract.action;

import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.game.util.admin.extract.services.ExtractService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Extract;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.Arith;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;
import com.game.util.web.Record;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class ExtractAction extends BaseAction {
	private static final long serialVersionUID = 2774386513504997808L;
	private static Log log = LogFactory.getLog(ExtractAction.class);
	private Double serverMoney = 5000.0;// 单笔最高提现金额
	private UserService userService;
	private ExtractService extractService;
	private Extract extract;
	private List<Extract> extractList;
	private Page<Extract> page;
	private String bank, account, beginTime, endTime;
	private Integer state;
	private Double money;
	private Integer count;// 计算可提现次数

	/**
	 * 提现记录
	 */
	public String listExtract() throws Exception {
		User user = Struts2Util.getUserSession();
		if (!Validator.isBlank(bank)) {
			this.bank = URLDecoder.decode(bank.trim(), "utf-8");
		} else {
			this.bank = null;
		}
		page = extractService.searchExtract(user.getId(), bank, account, state, beginTime, endTime, 15, super.getGoPage());
		extractList = page.getResultlist();
		return "listExtract";
	}

	/**
	 * 提现申请
	 */
	public String extractApply() throws Exception {
		User user = Struts2Util.getUserSession();
		user = userService.getUserById(user.getId());
		Struts2Util.setUserSession(user);
		count = Constant.SEVERAL - extractService.getExtractCount(user.getId());
		return "extractApply";
	}

	/**
	 * 提现申请————提交
	 */
	public String extractApplySubmit() throws Exception {
		User user = Struts2Util.getUserSession();
		user = userService.getUserById(user.getId());
		count = Constant.SEVERAL - extractService.getExtractCount(user.getId());
		if (count > Constant.SEVERAL) {
			throw new Exception("您今天的提现申请次数已满");
		}
		if (money > serverMoney) {
			throw new Exception("单次提现金额不能大于" + serverMoney);
		}
		double avail = Double.parseDouble(user.getUserInfo().getMoney());// 可用余额
		double charge = Arith.intercept(Arith.mul(money, 0.01), 1);// 手续费(只保留一位小数)
		if (charge < 1) {
			charge = 1.0;
		}
		double total = Arith.add(charge, money);// 提现金额+手续费
		double leavings = Arith.sub(avail, total);// 剩余金额（包括扣除手续费）
		if (leavings < 0) {// 判断余额是否充足
			throw new Exception("可用余额不足");
		}
		Extract extract = new Extract();
		extract.setAccount(user.getUserInfo().getBankNum()); // 银行账号
		extract.setBank(user.getUserInfo().getBankName()); // 银行
		extract.setCharge("" + charge); // 手续费
		extract.setExtractNum(Record.getOrderNum(5)); // 交易编号
		extract.setMoney("" + money); // 提现金额
		extract.setManagerName(null); // 操作管理员
		extract.setProcessTime(null); // 处理时间
		extract.setState(0); // 状态 0.未处理，-1.拒绝，1.成功，2.处理中
		extract.setTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS));// 申请时间
		extract.setUser(user); // 提现用户
		extractService.createEntity(extract);
		user.getUserInfo().setMoney("" + leavings);
		user.getUserInfo().setFreemoney("" + Arith.add("" + total, user.getUserInfo().getFreemoney()));
		userService.updateUser(user);
		Struts2Util.setUserSession(user);
		String info = "用户：" + user.getUsername() + " 于" + extract.getTime() + " 提现申请 申请金额：" + money + " 手续费：" + charge;
		log.info(info);
		return "extractApplySubmit";
	}

	/**
	 * 提现申请————成功
	 */
	public String extractSuccess() throws Exception {
		return "extractSuccess";
	}

	// *****************get/set******************
	public ExtractService getExtractService() {
		return extractService;
	}

	public void setExtractService(ExtractService extractService) {
		this.extractService = extractService;
	}

	public Extract getExtract() {
		return extract;
	}

	public void setExtract(Extract extract) {
		this.extract = extract;
	}

	public List<Extract> getExtractList() {
		return extractList;
	}

	public void setExtractList(List<Extract> extractList) {
		this.extractList = extractList;
	}

	public Page<Extract> getPage() {
		return page;
	}

	public void setPage(Page<Extract> page) {
		this.page = page;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		if (!Validator.isBlank(bank)) {
			this.bank = bank.trim();
		} else {
			this.bank = null;
		}
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		if (!Validator.isBlank(beginTime)) {
			this.beginTime = beginTime.trim();
		} else {
			this.beginTime = null;
		}
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		if (!Validator.isBlank(endTime)) {
			this.endTime = endTime.trim();
		} else {
			this.endTime = null;
		}
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		if (!Validator.isBlank(account)) {
			this.account = account.trim();
		} else {
			this.account = null;
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getServerMoney() {
		return serverMoney;
	}

	public void setServerMoney(Double serverMoney) {
		this.serverMoney = serverMoney;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = Arith.intercept(money, 2);
	}

}
