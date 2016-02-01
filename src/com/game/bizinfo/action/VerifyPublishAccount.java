package com.game.bizinfo.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.game.util.domain.Details;
import com.game.util.web.Constant;
import com.game.util.web.Help;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class VerifyPublishAccount {
	public static boolean verify(AccountInfo accountInfo, Iterator<Details> it) throws Exception {
		boolean flag = true;
		Map<String, String> map = new HashMap<String, String>();// 表单值
		Map<String, Boolean> error = new HashMap<String, Boolean>();// 错误信息
		// 账号是否为空
		if (Validator.isBlank(accountInfo.getAccount())) {
			flag = false;
			error.put("account", false);
		} else {
			error.put("account", true);
			accountInfo.setAccount(accountInfo.getAccount().trim());
		}
		// 确认账号是否为空
		if (Validator.isBlank(accountInfo.getR_account())) {
			flag = false;
			error.put("r_account", false);
		} else {
			error.put("r_account", true);
			accountInfo.setR_account(accountInfo.getR_account().trim());
		}
		// 2 次输入是否相同
		if (!accountInfo.getAccount().equals(accountInfo.getR_account())) {
			flag = false;
			error.put("r_account", false);
		} else {
			error.put("r_account", true);
		}
		// 密码是否为空
		if (Validator.isBlank(accountInfo.getPassword())) {
			flag = false;
			error.put("password", false);
		} else {
			error.put("password", true);
			accountInfo.setPassword(accountInfo.getPassword());
		}
		// 确认密码是否为空
		if (Validator.isBlank(accountInfo.getR_password())) {
			flag = false;
			error.put("r_password", false);
		} else {
			error.put("r_password", true);
			accountInfo.setR_password(accountInfo.getR_password());
		}
		// 2次输入密码是否相同
		if (!accountInfo.getPassword().equals(accountInfo.getR_password())) {
			flag = false;
			error.put("r_password", false);
		} else {
			error.put("r_password", true);
		}

		// 身份证是否上传
		Help.removeTempImage(Constant.USER_UPLOAD_PATH + "/" + (Struts2Util.getUserSession()).getUsername() + "/bizInfo/identity/temp");
		if (accountInfo.getIdentityType() == 1) {// 1代
			if (accountInfo.getFile() == null || accountInfo.getFile().size() != 1) {
				error.put("file", false);
			} else {
				error.put("file", true);
				Help.chkImage(accountInfo.getFile(), accountInfo.getFileFileName(), Constant.IMAGE_SIZE);//图片验证
				accountInfo.setFileFileName(Help.uploadImageToUserPath(accountInfo.getFile(), accountInfo.getFileFileName(), (Struts2Util.getUserSession()).getUsername() + "/bizInfo/identity/temp"));
				accountInfo.setFile(null);
			}
		} else if (accountInfo.getIdentityType() == 2) {// 2代
			if (accountInfo.getFile() == null || accountInfo.getFile().size() != 2) {
				error.put("file", false);
			} else {
				error.put("file", true);
				Help.chkImage(accountInfo.getFile(), accountInfo.getFileFileName(), Constant.IMAGE_SIZE);//图片验证
				accountInfo.setFileFileName(Help.uploadImageToUserPath(accountInfo.getFile(), accountInfo.getFileFileName(), (Struts2Util.getUserSession()).getUsername() + "/bizInfo/identity/temp"));
				accountInfo.setFile(null);
			}
		} else {// 不上传
			error.put("file", true);
			accountInfo.setFile(null);
			accountInfo.setFileFileName(null);
		}

		if (!Validator.isBlank(accountInfo.getContent())) {
			String[] arr = accountInfo.getContent().split(";;");
			String[] str = null;
			for (int i = 0; i < arr.length; i++) {
				str = arr[i].split("::");
				map.put(str[0], str[1]);
			}
		}

		if (it.hasNext()) {
			Details details = null;
			while (it.hasNext()) {
				details = it.next();
				if (details.getParent().getIsUser() == 0 || details.getParent().getParent().getIsUser() == 0) {
					break;
				}
				if (details.getIsUser() == 1) {
					if (Validator.isBlank(map.get(details.getAttributeName()))) {
						flag = false;
						error.put(details.getAttributeName(), false);
					} else {
						error.put(details.getAttributeName(), true);
						map.put(details.getAttributeName(), map.get(details.getAttributeName()).trim());
					}
				}
			}
			accountInfo.setMap(map);
		}
		accountInfo.setError(error);
		Struts2Util.setSession("accountInfo", accountInfo);

		return flag;
	}
}
