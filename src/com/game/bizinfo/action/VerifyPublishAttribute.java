package com.game.bizinfo.action;

import java.util.HashMap;
import java.util.Map;

import com.game.util.web.Arith;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class VerifyPublishAttribute {
	public static boolean verify(AttributeInfo attributeInfo) {
		boolean flag = true;
		Map<String, String> map = new HashMap<String, String>();// 表单值
		Map<String, Boolean> error = new HashMap<String, Boolean>();// 错误信息

		// 职业是否已选
		if (Validator.isBlank(attributeInfo.getProfessionName())) {
			flag = false;
			error.put("professionName", false);
		} else {
			error.put("professionName", true);
			attributeInfo.setProfessionName(attributeInfo.getProfessionName()
					.trim());
		}

		// 性别是否已选
		if (Validator.isBlank(attributeInfo.getSex())) {
			flag = false;
			error.put("sex", false);
		} else {
			error.put("sex", true);
			attributeInfo.setSex(attributeInfo.getSex().trim());
		}

		// 等级是否已填写
		if (Validator.isBlank(attributeInfo.getGrade())) {
			flag = false;
			error.put("grade", false);
		} else {
			try {
				int grade = Integer.parseInt(attributeInfo.getGrade());
				if (grade > 0) {
					attributeInfo.setGrade(grade + "");
					error.put("grade", true);
				} else {
					flag = false;
					error.put("grade", false);
				}

			} catch (Exception e) {
				flag = false;
				error.put("grade", false);
			}
		}

		// 标题2是否已填
		if (Validator.isBlank(attributeInfo.getTitle2())) {
			flag = false;
			error.put("title2", false);
		} else {
			if (Validator.isGtLength(attributeInfo.getTitle2().trim(), 60)) {
				flag = false;
				error.put("title2", false);
			} else {
				error.put("title2", true);
				attributeInfo.setTitle2(attributeInfo.getTitle2().trim());
			}
		}

		// 价格
		if (!Validator.isPrice(attributeInfo.getPrice())) {
			flag = false;
			error.put("price", false);
		} else {
			try {
				double price = Arith.round(Double.parseDouble(attributeInfo.getPrice().trim()), 2);
				if (price >= 15) {
					attributeInfo.setPrice("" + price);
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

		// QQ
		if (!Validator.isQq(attributeInfo.getQq())) {
			flag = false;
			error.put("qq", false);
		} else {
			error.put("qq", true);
			attributeInfo.setQq(attributeInfo.getQq().trim());
		}

		// 联系电话
		if (!Validator.isMobilePhone(attributeInfo.getPhoneNum())) {
			flag = false;
			error.put("phoneNum", false);
		} else {
			error.put("phoneNum", true);
			attributeInfo.setPhoneNum(attributeInfo.getPhoneNum().trim());
		}

		// 自定义属性内容
		if (!Validator.isBlank(attributeInfo.getContent())) {
			String[] arr = attributeInfo.getContent().split(";;");
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
			attributeInfo.setMap(map);
		}
		// 是否上传商品图片
		if (attributeInfo.getType() != null && attributeInfo.getType() == 1) {
			if (attributeInfo.getFile() == null || attributeInfo.getFile().size() < 1) {
				flag = false;
				error.put("file", false);
			} else {
				error.put("file", true);
			}
		} else {
			error.put("file", true);
			attributeInfo.setFile(null);
			attributeInfo.setFileFileName(null);
		}
		attributeInfo.setError(error);
		Struts2Util.setSession("attributeInfo", attributeInfo);
		return flag;
	}
}
