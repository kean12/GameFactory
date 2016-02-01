package com.game.util.admin.extract.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.util.admin.extract.services.ExtractService;
import com.game.util.base.action.BaseAction;
import com.game.util.domain.Extract;
import com.game.util.domain.Manage;
import com.game.util.domain.User;
import com.game.util.user.services.UserService;
import com.game.util.web.Arith;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.MessageUtil;
import com.game.util.web.Page;
import com.game.util.web.Record;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class ExtractAction extends BaseAction {
	private static final long serialVersionUID = 4424733998698294742L;
	private UserService userService;
	private ExtractService extractService;
	private Extract extract;
	private List<Extract> extractList;
	private Page<Extract> page;

	private String username, beginTime, endTime, p_beginTime, p_endTime, moneyMin, moneyMax, extractNum, bank, account;
	private Integer state;
	private Long userID;

	private Integer process;// 处理状态
	private Long extractID;

	private Map<String, Object> setListExtractParam() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("p_beginTime", p_beginTime);
		map.put("p_endTime", p_endTime);
		map.put("moneyMin", moneyMin);
		map.put("moneyMax", moneyMax);
		map.put("extractNum", extractNum);
		map.put("bank", bank);
		map.put("account", account);
		map.put("state", state);
		return map;
	}

	/**
	 * 后台管理——提现记录
	 */
	public String listExtract() throws Exception {
		page = extractService.searchExtract(setListExtractParam(), 1, super.getGoPage());
		extractList = page.getResultlist();
		return "listExtract";
	}

	/**
	 * 后台管理——提现记录--处理
	 */
	public String process() throws Exception {
		if (process != null && extractID != null) {
			extract = extractService.getEntity(Extract.class, extractID);
			if (!extract.getState().equals(process)) {
				Manage manage = Struts2Util.getManageSession();
				User user = null;
				if (process == 1) {// 同意提现
					if (extract.getState() == 2) {// 当接受处理订单状态下才能执行此操作
						if (!manage.getRole().getName().equals("admin") && !extract.getManagerName().equals(manage.getName())) {
							throw new Exception("对不起，你没有此权限");
						}
						extract.setState(1);
						extract.setManagerName(manage.getName());
						extract.setProcessTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS));
						// 用户金额处理
						user = extract.getUser();
						double total = Arith.add(extract.getMoney(), extract.getCharge());
						double freemoney = Arith.sub(user.getUserInfo().getFreemoney(), "" + total);
						if (freemoney < 0) {
							throw new Exception("此用户冻结金额异常");
						}
						user.getUserInfo().setFreemoney("" + freemoney);

						String synopsis = "提现：" + extract.getMoney() + "元，手续费：" + extract.getCharge() + "元";
						String tolMoney = "" + Arith.add(user.getUserInfo().getMoney(), "" + freemoney);
						Record.set(user, extract.getExtractNum(), null, extract.getBank(), 5, null, "" + total, tolMoney, synopsis);
						extractService.updateEntity(extract);
						userService.updateUser(user);

						// 发送站内信--同意提现
						MessageUtil.toMessage(4, extract, extract.getProcessTime(), extract.getUser());
					}
				} else if (process == -1) {// 拒绝提现
					if (extract.getState() == 2) {// 当接受处理订单状态下才能执行此操作
						if (!manage.getRole().getName().equals("admin") && !extract.getManagerName().equals(manage.getName())) {
							throw new Exception("对不起，你没有此权限");
						}
						extract.setState(-1);
						extract.setManagerName(manage.getName());
						extract.setProcessTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS));
						// 用户金额处理
						user = extract.getUser();
						double total = Arith.add(extract.getMoney(), extract.getCharge());
						double freemoney = Arith.sub(user.getUserInfo().getFreemoney(), "" + total);
						if (freemoney < 0) {
							throw new Exception("此用户冻结金额异常");
						}
						user.getUserInfo().setFreemoney("" + freemoney);
						user.getUserInfo().setMoney("" + Arith.add(user.getUserInfo().getMoney(), "" + total));
						extractService.updateEntity(extract);
						userService.updateUser(user);
						// 发送站内信--同意提现
						MessageUtil.toMessage(5, extract, extract.getProcessTime(), extract.getUser());
					}
				} else if (process == 2) {
					if (extract.getState() == 0) {
						extract.setState(2);
						extract.setManagerName(manage.getName());
						extractService.updateEntity(extract);
					}
				}
			}
		}
		return listExtract();
	}

	// *****************get/set******************
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

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

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (!Validator.isBlank(username)) {
			this.username = username.trim();
		} else {
			this.username = null;
		}
	}

	public String getP_beginTime() {
		return p_beginTime;
	}

	public void setP_beginTime(String pBeginTime) {
		if (!Validator.isBlank(pBeginTime)) {
			this.p_beginTime = pBeginTime.trim();
		} else {
			this.p_beginTime = null;
		}
	}

	public String getP_endTime() {
		return p_endTime;
	}

	public void setP_endTime(String pEndTime) {
		if (!Validator.isBlank(pEndTime)) {
			this.p_endTime = pEndTime.trim();
		} else {
			this.p_endTime = null;
		}
	}

	public String getMoneyMin() {
		return moneyMin;
	}

	public void setMoneyMin(String moneyMin) {
		if (!Validator.isBlank(moneyMin)) {
			this.moneyMin = moneyMin.trim();
		} else {
			this.moneyMin = null;
		}
	}

	public String getMoneyMax() {
		return moneyMax;
	}

	public void setMoneyMax(String moneyMax) {
		if (!Validator.isBlank(moneyMax)) {
			this.moneyMax = moneyMax.trim();
		} else {
			this.moneyMax = null;
		}
	}

	public String getExtractNum() {
		return extractNum;
	}

	public void setExtractNum(String extractNum) {
		if (!Validator.isBlank(extractNum)) {
			this.extractNum = extractNum.trim();
		} else {
			this.extractNum = null;
		}
	}

	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}

	public Long getExtractID() {
		return extractID;
	}

	public void setExtractID(Long extractID) {
		this.extractID = extractID;
	}

}
