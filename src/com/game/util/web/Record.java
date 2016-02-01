package com.game.util.web;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.game.util.domain.Particulars;
import com.game.util.domain.User;
import com.game.util.user.services.ParticularsService;

/**
 * 由于多处用到账户明细添加，增加一个公共方法
 */

public class Record {
	private static ParticularsService particularsService;

	/**
	 * @param user 用户外键
	 * @param orderNum 商户订单号
	 * @param runningNum 业务流水号
	 * @param bank 交易场所
	 * @param type 交易类型 1.交易付款 2.交易收款 3.在线充值 4.退款 5.提现 6.差额入账 7.超时赔付
	 * @param income 收入
	 * @param expense 支出
	 * @param money 账户总额
	 * @param synopsis 交易详情 return 交易流水号
	 */
	public static String set(User user, String orderNum, String runningNum,
			String bank, Integer type, String income, String expense,
			String money, String synopsis) throws Exception {
		
		if (particularsService == null) {
			particularsService = (ParticularsService) SpringUtil.getBean("particularsService");
		}
		Particulars particulars = new Particulars();
		particulars.setUser(user);
		if (orderNum == null) {
			particulars.setOrderNum(getOrderNum(type));
		} else {
			particulars.setOrderNum(orderNum);
		}
		if (runningNum == null) {
			particulars.setRunningNum(getRunningNum());
		} else {
			particulars.setRunningNum(runningNum);
		}
		particulars.setTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS_MMM));
		if (StringUtils.isBlank(bank)) {
			particulars.setBank("游戏买卖网");
		} else {
			particulars.setBank(bank);
		}
		particulars.setType(type);
		particulars.setIncome(income);
		particulars.setExpense(expense);
		particulars.setMoney(money);
		particulars.setSynopsis(synopsis);
		particulars.setRemark(null);
		particularsService.createParticulars(particulars);
		return particulars.getRunningNum();
	}

	/**
	 * 更具商户订单号和业务流水号校验
	 * @param orderNum 商户订单号
	 * @param runningNum 业务流水号
	 * @return 是否存在此记录
	 * @throws Exception
	 */
	public static boolean chk_Recoed(String orderNum, String runningNum) throws Exception {
		if (particularsService == null) {
			particularsService = (ParticularsService) SpringUtil.getBean("particularsService");
		}
		boolean flag = false;
		Particulars particulars = particularsService.getParticularsNum(orderNum, runningNum);
		if (particulars != null) {
			flag = true;
		}

		return flag;
	}

	/**
	 * 1.交易付款 2.交易收款 3.在线充值 4.退款 5.提现 6.差额入账 7.超时赔付
	 */
	public static String getOrderNum(int type) {
		String orderNum = null;
		Random rd = new java.util.Random(System.currentTimeMillis());
		long num = Math.abs(rd.nextLong());
		switch (type) {
		case 1: {
			orderNum = "JY" + String.valueOf(num).substring(9);
			break;
		}
		case 2: {
			orderNum = "JY" + String.valueOf(num).substring(9);
			break;
		}
		case 3: {
			orderNum = "CZ" + String.valueOf(num).substring(9);
			break;
		}
		case 4: {
			orderNum = "TK" + String.valueOf(num).substring(9);
			break;
		}
		case 5: {
			orderNum = "TX" + String.valueOf(num).substring(9);
			break;
		}
		case 6: {
			orderNum = "CE" + String.valueOf(num).substring(9);
			break;
		}
		case 7: {
			orderNum = "PF" + String.valueOf(num).substring(9);
			break;
		}
		default:
			break;
		}
		return orderNum;
	}

	public static String getRunningNum() {
		String runningNum = null;
		String riqi = DateUtil.nowDate(Constant.YYYYMMDDSS);
		Random rd = new java.util.Random(System.currentTimeMillis());
		long num = Math.abs(rd.nextLong());
		runningNum = riqi + String.valueOf(num).substring(12);
		return runningNum;
	}
}