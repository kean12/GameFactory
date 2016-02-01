package com.game.order.action;

import com.game.util.domain.BizInfo;
import com.game.util.domain.Order;
import com.game.util.web.Validator;

public class VerifyOrder {
	public static String verify(Order order, BizInfo bizInfo) {
		if (order == null) {
			return "";
		}
		if (bizInfo.getSellModel() != 3) {

			if (!Validator.isIntNumber(order.getBuyBum())) {
				return "购买数量必须是一个正整数";
			}

			if (Validator.isBlank(order.getPlayerRole())) {
				return "请填写收获角色名";
			} else {
				order.setPlayerRole(order.getPlayerRole().trim());
			}

			if (!Validator.isIntNumber(order.getPlayerGrade() + "")) {
				return "请填写收获角色等级";
			}
		} else {
			order.setBuyBum("1");
		}

		if (!Validator.isQq(order.getPlayQQ())) {
			return "请正确输入纯数字的QQ号码";
		} else {
			order.setPlayQQ(order.getPlayQQ().trim());
		}

		if (!Validator.isMobilePhone(order.getPlayPhoneNum())) {
			return "请填写正确的手机号码";
		} else {
			order.setPlayPhoneNum(order.getPlayPhoneNum().trim());
		}
		return null;
	}
}
