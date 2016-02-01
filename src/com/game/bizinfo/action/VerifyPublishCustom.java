package com.game.bizinfo.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.util.domain.BizInfo;
import com.game.util.web.Arith;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class VerifyPublishCustom {
	public static boolean verify(BizInfo bizInfo, CustomInfo customInfo, List<File> file, List<String> fileFileName) {
		boolean flag = true;
		Map<String, String> map = new HashMap<String, String>();// 表单值
		Map<String, Boolean> error = new HashMap<String, Boolean>();// 错误信息
		if (customInfo == null) {
			customInfo = new CustomInfo();
		}

		// 标题
		if (Validator.isGtLength(bizInfo.getTitle(), 60)) {
			flag = false;
			error.put("title", false);
		} else {
			error.put("title", true);
			bizInfo.setTitle(bizInfo.getTitle().trim());
		}

		// 价格
		if (!Validator.isPrice(bizInfo.getPrice())) {
			flag = false;
			error.put("price", false);
		} else {
			try {
				double price = Arith.round(Double.parseDouble(bizInfo.getPrice()), 2);
				if (price >= 0) {
					bizInfo.setPrice("" + price);
					error.put("price", true);
				} else {
					flag = false;
					error.put("price", false);
				}
			} catch (Exception e) {
				flag = false;
				error.put("price", false);
			}
		}

		// 库存量
		if (!Validator.isIntNumber(bizInfo.getStock())) {
			flag = false;
			error.put("stock", false);
		} else {
			try {
				int stock = Integer.parseInt(bizInfo.getStock());
				if (stock > 0) {
					bizInfo.setStock(stock + "");
					error.put("stock", true);
				} else {
					flag = false;
					error.put("stock", false);
				}
			} catch (Exception e) {
				flag = false;
				error.put("stock", false);
			}
		}

		// 收获地址
		if (bizInfo.getSellModel() == 1) {
			if (Validator.isBlank(bizInfo.getSite())) {
				flag = false;
				error.put("site", false);
			} else {
				error.put("site", true);
				bizInfo.setSite(bizInfo.getSite().trim());
			}
		} else {
			bizInfo.setSite(null);
		}

		// 交易账号
		if (Validator.isBlank(bizInfo.getAccount())) {
			flag = false;
			error.put("account", false);
		} else {
			error.put("account", true);
			bizInfo.setAccount(bizInfo.getAccount().trim());
		}

		// 交易账号密码
		if (Validator.isBlank(bizInfo.getPassword())) {
			flag = false;
			error.put("password", false);
		} else {
			error.put("password", true);
			bizInfo.setPassword(bizInfo.getPassword());
		}

		// 账号角色名
		if (Validator.isBlank(bizInfo.getRoleName())) {
			flag = false;
			error.put("roleName", false);
		} else {
			error.put("roleName", true);
			bizInfo.setRoleName(bizInfo.getRoleName().trim());
		}
		// 密码锁

		// 物品存放处
		if (Validator.isBlank(bizInfo.getPlace())) {
			flag = false;
			error.put("place", false);
		} else {
			error.put("place", true);
			bizInfo.setPlace(bizInfo.getPlace().trim());
		}

		// QQ
		if (!Validator.isQq(bizInfo.getQq())) {
			flag = false;
			error.put("qq", false);
		} else {
			error.put("qq", true);
			bizInfo.setQq(bizInfo.getQq().trim());
		}

		// 联系电话
		if (!Validator.isMobilePhone(bizInfo.getPhoneNum())) {
			flag = false;
			error.put("phoneNum", false);
		} else {
			error.put("phoneNum", true);
			bizInfo.setPhoneNum(bizInfo.getPhoneNum().trim());
		}

		// 是否上传商品图片
		if (customInfo.getType() != null && customInfo.getType() == 1) {
			if (customInfo.getFile() == null || customInfo.getFile().size() < 1) {
				flag = false;
				error.put("type", false);
			} else {
				error.put("type", true);
			}
		} else {
			error.put("type", true);
			customInfo.setType(null);
			customInfo.setFile(null);
			customInfo.setFileFileName(null);
		}

		// 是否上传密保卡
		if (customInfo.getPwdType() != null && customInfo.getPwdType() == 1) {
			if (file == null || file.size() < 1) {
				flag = false;
				error.put("pwdType", false);
			} else {
				error.put("pwdType", true);
			}
		} else {
			error.put("pwdType", true);
			customInfo.setPwdType(null);
		}

		// 自定义属性内容
		if (!Validator.isBlank(customInfo.getContent())) {
			String[] arr = customInfo.getContent().split(";;");
			String[] str = null;
			for (int i = 0; i < arr.length; i++) {
				str = arr[i].split("::");
				try {
					if (!Validator.isBlank(str[0]) && !Validator.isBlank(str[1].trim())) {
						map.put(str[0], str[1]);
					}
				} catch (Exception e) {
				}
			}
			customInfo.setMap(map);
		}

		customInfo.setBizInfo(bizInfo);
		customInfo.setError(error);
		Struts2Util.setSession("customInfo", customInfo);

		return flag;
	}
}
