package com.game.bizinfo.action;

import com.game.util.domain.BizInfo;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class VerifyPublish {
	public static boolean verify(BizInfo bizInfo) {
		boolean flag = true;
		PublishErrorInfo publishErrorInfo = new PublishErrorInfo();
		if (bizInfo.getBizKind() == null || bizInfo.getGame() == null && bizInfo.getServer() == null) {
			publishErrorInfo.setBizKind("请重新选择分类");
			flag = false;
		}
		// 标题
		if (Validator.isGtLength(bizInfo.getTitle().trim(), 60)) {
			publishErrorInfo.setTitle("商品标题必须填写");
			flag = false;
		} else {
			bizInfo.setTitle(bizInfo.getTitle().trim());
		}
		// 价格
		if (!Validator.isPrice(bizInfo.getPrice())) {
			publishErrorInfo.setPrice("商品价格必须填写");
			flag = false;
		} else {
			bizInfo.setPrice(bizInfo.getPrice().trim());
		}
		// 库存量

		if (!Validator.isIntNumber(bizInfo.getStock())) {
			publishErrorInfo.setStock("剩余件数必须是一个整数");
			flag = false;
		} else {
			bizInfo.setStock(bizInfo.getStock().trim());
		}
		// 数量
		if (!Validator.isIntNumber(bizInfo.getAmount())) {
			publishErrorInfo.setAmount("请填写数量");
			flag = false;
		}

		// 收获地址
		if (bizInfo.getSellModel() == 1) {
			if (Validator.isBlank(bizInfo.getSite())) {
				publishErrorInfo.setSite("请设置交易地点");
				flag = false;
			} else {
				bizInfo.setSite(bizInfo.getSite().trim());
			}
		} else {
			bizInfo.setSite(null);
		}

		// 有效期
		if (bizInfo.getTerm_of_validity() == null || bizInfo.getTerm_of_validity() != 7 && bizInfo.getTerm_of_validity() != 15 && bizInfo.getTerm_of_validity() != 30) {
			publishErrorInfo.setTerm_of_validity("请选择有效期");
			flag = false;
		}

		// 交易时间
		if (Validator.isBlank(bizInfo.getTradeStart()) || Validator.isBlank(bizInfo.getTradeEnd()) || bizInfo.getTradeStart().compareTo(bizInfo.getTradeEnd()) >= 0) {
			publishErrorInfo.setTradeTime("设置正确的交易时间");
			flag = false;
		}
		// 交易账号

		if (Validator.isBlank(bizInfo.getAccount())) {
			publishErrorInfo.setAccount("输入游戏账号");
			flag = false;
		} else {
			bizInfo.setAccount(bizInfo.getAccount().trim());
		}
		// 交易账号密码
		if (Validator.isBlank(bizInfo.getPassword())) {
			publishErrorInfo.setPassword("输入游戏账号密码");
			flag = false;
		} else {
			bizInfo.setPassword(bizInfo.getPassword());
		}
		// 账号角色名
		if (Validator.isBlank(bizInfo.getRoleName())) {
			publishErrorInfo.setRoleName("输入游戏账号角色名");
			flag = false;
		} else {
			bizInfo.setRoleName(bizInfo.getRoleName().trim());
		}
		// 密码锁

		// 物品存放处
		if (Validator.isBlank(bizInfo.getPlace())) {
			publishErrorInfo.setPlace("输入寄售物品存放处");
			flag = false;
		} else {
			bizInfo.setPlace(bizInfo.getPlace().trim());
		}

		// 联系QQ
		if (!Validator.isQq(bizInfo.getQq())) {
			publishErrorInfo.setQq("输入正确的QQ号码");
			flag = false;
		} else {
			bizInfo.setQq(bizInfo.getQq().trim());
		}

		// 联系电话
		if (!Validator.isMobilePhone(bizInfo.getPhoneNum())) {
			publishErrorInfo.setPhoneNum("输入正确的手机号");
			flag = false;
		} else {
			bizInfo.setPhoneNum(bizInfo.getPhoneNum().trim());
		}
		// 宝贝介绍
		if (Validator.isBlank(bizInfo.getInfo())) {
			publishErrorInfo.setInfo("商品介绍必须填写");
			flag = false;
		} else {
			bizInfo.setInfo(bizInfo.getInfo());
		}

		if (!flag) {
			Struts2Util.setSession("publishErrorInfo", publishErrorInfo);
			Struts2Util.setSession("bizInfo", bizInfo);
		}
		return flag;
	}
}
