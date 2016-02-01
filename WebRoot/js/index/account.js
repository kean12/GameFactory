
function doPhoneNum(obj) {
	var txt_phoneNum = $("txt_phoneNum").value;
	account.doPhoneNum(txt_phoneNum, function (data) {
		closeDiv(obj);
		if (data == "true") {
			alertDialog("\u66f4\u6539\u6210\u529f\uff01");
			$("phoneNum").innerHTML = txt_phoneNum;
		} else {
			if (data == "false") {
				alertDialog("\u66f4\u6539\u5931\u8d25\uff01");
				$("txt_phoneNum").value = $("phoneNum").innerHTML;
			} else {
				alertDialog(data);
				$("txt_phoneNum").value = $("phoneNum").innerHTML;
			}
		}
	});
}
function doQq(obj) {
	var txt_qq = $("txt_qq").value;
	account.doQq(txt_qq, function (data) {
		closeDiv(obj);
		if (data == "true") {
			alertDialog("\u66f4\u6539\u6210\u529f\uff01");
			$("qq").innerHTML = txt_qq;
		} else {
			if (data == "false") {
				alertDialog("\u66f4\u6539\u5931\u8d25\uff01");
				$("txt_qq").value = $("qq").innerHTML;
			} else {
				alertDialog(data);
				$("txt_qq").value = $("qq").innerHTML;
			}
		}
	});
}
function doIdentity(obj) {
	var txt_identity = $("txt_identity").value;
	var txt_realName = $("txt_realName").value;
	account.doIdentity(txt_identity, txt_realName, function (data) {
		closeDiv(obj);
		if (data == "true") {
			alertDialog("\u66f4\u6539\u6210\u529f\uff01");
			$("identity").innerHTML = txt_identity.substring(0, 4) + "******" + txt_identity.substring(15, 18);
			$("realName").innerHTML = txt_realName;
			showDiv("updateRealName");
			closeDiv("identity_edit");
		} else {
			if (data == "false") {
				alertDialog("\u66f4\u6539\u5931\u8d25\uff01");
				$("txt_identity").value = $("identity").innerHTML;
				$("txt_realName").value = $("realName").innerHTML;
			} else {
				alertDialog(data);
				$("txt_identity").value = $("identity").innerHTML;
				$("txt_realName").value = $("realName").innerHTML;
			}
		}
	});
}
function doEmail(obj) {
	var txt_email = $("txt_email").value;
	account.doEmail(txt_email, function (data) {
		closeDiv(obj);
		if (data == "true") {
			alertDialog("\u66f4\u6539\u6210\u529f\uff01");
			$("email").innerHTML = txt_email;
		} else {
			if (data == "false") {
				alertDialog("\u66f4\u6539\u5931\u8d25\uff01");
				$("txt_email").value = $("email").innerHTML;
			} else {
				alertDialog(data);
				$("txt_email").value = $("email").innerHTML;
			}
		}
	});
}
function doApplyPwd(obj) {
	var txt_o_applyPwd = $("txt_o_applyPwd").value;
	var txt_applyPwd1 = $("txt_applyPwd1").value;
	var txt_applyPwd2 = $("txt_applyPwd2").value;
	account.doApplyPwd(txt_o_applyPwd, txt_applyPwd1, txt_applyPwd2, function (data) {
		closeDiv(obj);
		if (data == "true") {
			alertDialog("\u66f4\u6539\u6210\u529f\uff01");
		} else {
			if (data == "false") {
				alertDialog("\u66f4\u6539\u5931\u8d25\uff01");
			} else {
				alertDialog(data);
			}
		}
	});
	$("txt_o_applyPwd").value = "";
	$("txt_applyPwd1").value = "";
	$("txt_applyPwd2").value = "";
}
function setApplyPwd(obj) {
	var txt_setApplyPwd1 = $("txt_setApplyPwd1").value;
	var txt_setApplyPwd2 = $("txt_setApplyPwd2").value;
	account.setApplyPwd(txt_setApplyPwd1, txt_setApplyPwd2, function (data) {
		if (data == "true") {
			alertDialog("\u66f4\u6539\u6210\u529f\uff01");
			closeDiv(obj);
		} else {
			if (data == "false") {
				alertDialog("\u66f4\u6539\u5931\u8d25\uff01");
			} else {
				if (data == "NO_LOGIN") {
					alertDialog("\u767b\u5f55\u72b6\u6001\u5df2\u7ecf\u5931\u6548\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\uff01");
					window.location.href = AP + "/user/login.shtml";
				} else {
					alertDialog(data);
				}
			}
		}
	});
	$("txt_setApplyPwd1").value = "";
	$("txt_setApplyPwd2").value = "";
}
function doPassword(obj) {
	var txt_o_password = $("txt_o_password").value;
	var txt_password1 = $("txt_password1").value;
	var txt_password2 = $("txt_password2").value;
	account.doPassword(txt_o_password, txt_password1, txt_password2, function (data) {
		closeDiv(obj);
		if (data == "true") {
			alertDialog("\u66f4\u6539\u6210\u529f\uff01");
		} else {
			if (data == "false") {
				alertDialog("\u66f4\u6539\u5931\u8d25\uff01");
			} else {
				alertDialog(data);
			}
		}
	});
	$("txt_o_password").value = "";
	$("txt_password1").value = "";
	$("txt_password2").value = "";
}
function doHint(obj) {
	var sel_o_hint = $("sel_o_hint");
	var o_answer = $("o_answer").value;
	var sel_hint = $("sel_hint");
	var answer = $("answer").value;
	var txt_hint = sel_hint.options[sel_hint.selectedIndex].value;
	account.doHint(sel_o_hint.options[sel_o_hint.selectedIndex].value, o_answer, txt_hint, answer, function (data) {
		closeDiv(obj);
		if (data == "true") {
			alertDialog("\u66f4\u6539\u6210\u529f\uff01");
			$("hint").innerHTML = txt_hint;
		} else {
			if (data == "false") {
				alertDialog("\u66f4\u6539\u5931\u8d25\uff01");
			} else {
				alertDialog(data);
			}
		}
	});
	sel_o_hint.options[0].selected = "true";
	$("o_answer").value = "";
	sel_hint.options[0].selected = "true";
	$("answer").value = "";
}
function doPostAddr(obj) {
	var txt_postAddr = $("txt_postAddr").value;
	account.doPostAddr(txt_postAddr, function (data) {
		closeDiv(obj);
		if (data == "true") {
			alertDialog("\u66f4\u6539\u6210\u529f\uff01");
			$("postAddr").innerHTML = txt_postAddr;
		} else {
			if (data == "false") {
				alertDialog("\u66f4\u6539\u5931\u8d25\uff01");
				$("txt_postAddr").value = "";
			} else {
				alertDialog(data);
				$("txt_postAddr").value = "";
			}
		}
	});
}

