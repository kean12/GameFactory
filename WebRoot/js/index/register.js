
var flag_username = false;
var flag_password = false;
var flag_password2 = false;
var flag_applyPwd = false;
var flag_applyPwd2 = false;
var flag_hint = false;
var flag_answer = false;
var flag_email = false;
var flag_qq = false;
var flag_phoneNum = false;
var flag_vercode = false;

//验证用户
function chkusername() {
	var username = document.getElementById("username").value.Trim();
	if (!username.match(/^[\w]{6,20}$/)) {
		flag_username = false;
		document.getElementById("usernameMess").style.color = "red";
		document.getElementById("usernameMess").innerHTML = "6-20\u4e2a\u5b57\u7b26\uff0c\u4ec5\u9650\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u4e0b\u5212\u7ebf";
		return false;
	} else {
		document.getElementById("username").value = username;
		chkUser(username);
	}
}
function chkUser(username) {
	ajaxUser.checkname(username, callBackUser);
}
function callBackUser(date) {
	if (date == "true") {
		document.getElementById("usernameMess").style.color = "green";
		document.getElementById("usernameMess").innerHTML = "\u7528\u6237\u540d\u53ef\u4ee5\u6ce8\u518c";
		flag_username = true;
	} else {
		flag_username = false;
		document.getElementById("usernameMess").style.color = "red";
		document.getElementById("usernameMess").innerHTML = date;
	}
}

//验证密码
function chkpassword() {
	var password = document.getElementById("password").value;
	if (password.match(/^[\w]{6,20}$/)) {
		document.getElementById("passwordMess").style.color = "green";
		document.getElementById("passwordMess").innerHTML = "\u901a\u8fc7";
		flag_password = true;
		chkpassword2();
	} else {
		flag_password = false;
		document.getElementById("passwordMess").style.color = "red";
		document.getElementById("passwordMess").innerHTML = "\u5bc6\u7801\u957f\u5ea6\u4e3a6-20";
		chkpassword2();
	}
}
//确认密码
function chkpassword2() {
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("password2").value;
	if (password == password2) {
		document.getElementById("password2Mess").style.color = "green";
		document.getElementById("password2Mess").innerHTML = "\u901a\u8fc7";
		flag_password2 = true;
	} else {
		flag_password2 = false;
		document.getElementById("password2Mess").style.color = "red";
		document.getElementById("password2Mess").innerHTML = "\u4e24\u6b21\u5bc6\u7801\u8f93\u5165\u4e0d\u4e00\u81f4";
	}
}

//验证支付密码
function chkapplyPwd() {
	var applyPwd = document.getElementById("applyPwd").value;
	if (applyPwd.match(/^[\w]{6,20}$/)) {
		document.getElementById("applyPwdMess").style.color = "green";
		document.getElementById("applyPwdMess").innerHTML = "\u901a\u8fc7";
		flag_applyPwd = true;
		chkapplyPwd2();
	} else {
		flag_applyPwd = false;
		document.getElementById("applyPwdMess").style.color = "red";
		document.getElementById("applyPwdMess").innerHTML = "\u5bc6\u7801\u957f\u5ea6\u4e3a6-20";
		chkapplyPwd2();
	}
}
//确认支付密码
function chkapplyPwd2() {
	var applyPwd = document.getElementById("applyPwd").value;
	var applyPwd2 = document.getElementById("applyPwd2").value;
	if (applyPwd == applyPwd2) {
		document.getElementById("applyPwd2Mess").style.color = "green";
		document.getElementById("applyPwd2Mess").innerHTML = "\u901a\u8fc7";
		flag_applyPwd2 = true;
	} else {
		flag_applyPwd2 = false;
		document.getElementById("applyPwd2Mess").style.color = "red";
		document.getElementById("applyPwd2Mess").innerHTML = "\u4e24\u6b21\u5bc6\u7801\u8f93\u5165\u4e0d\u4e00\u81f4";
	}
}

//密码提示问题
function chkhint() {
	var hint = document.getElementById("hint");
	if (hint.options[hint.selectedIndex].value != "") {
		document.getElementById("hintMess").style.color = "green";
		document.getElementById("hintMess").innerHTML = "\u901a\u8fc7";
		flag_hint = true;
	} else {
		flag_hint = false;
		document.getElementById("hintMess").style.color = "red";
		document.getElementById("hintMess").innerHTML = "\u8bf7\u9009\u62e9\u63d0\u793a\u95ee\u9898";
	}
}
//提示问题答案
function chkanswer() {
	var answer = document.getElementById("answer").value.Trim();
	if (answer.getByteLength() >= 4) {
		document.getElementById("answerMess").style.color = "green";
		document.getElementById("answerMess").innerHTML = "\u901a\u8fc7";
		document.getElementById("answer").value = answer;
		flag_answer = true;
	} else {
		flag_answer = false;
		document.getElementById("answerMess").style.color = "red";
		document.getElementById("answerMess").innerHTML = "\u81f3\u5c112\u4e2a\u6c49\u5b57\u62164\u4e2a\u5b57\u7b26";
	}
}

//验证电子邮件
function chkemail() {
	var email = document.getElementById("email").value.Trim();
	if (email.match(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/)) {
		document.getElementById("emailMess").style.color = "green";
		document.getElementById("emailMess").innerHTML = "\u901a\u8fc7";
		document.getElementById("email").value = email;
		flag_email = true;
	} else {
		flag_email = false;
		document.getElementById("emailMess").style.color = "red";
		document.getElementById("emailMess").innerHTML = "\u7535\u5b50\u90ae\u4ef6\u683c\u5f0f\u4e0d\u6b63\u786e ";
	}
}

//验证qq
function chkqq() {
	var qq = document.getElementById("qq").value.Trim();
	if (qq.match(/^\d{5,}$/)) {
		document.getElementById("qqMess").style.color = "green";
		document.getElementById("qqMess").innerHTML = "\u901a\u8fc7";
		document.getElementById("qq").value = qq;
		flag_qq = true;
	} else {
		flag_qq = false;
		document.getElementById("qqMess").style.color = "red";
		document.getElementById("qqMess").innerHTML = "\u8bf7\u6b63\u786e\u8f93\u5165\u7eaf\u6570\u5b57\u7684QQ\u53f7\u7801 ";
	}
}

//验证手机号码
function chkphoneNum() {
	var phoneNum = document.getElementById("phoneNum").value.Trim();
	if (phoneNum.match(/^1[3,5]\d{9}$/)) {
		document.getElementById("phoneNumMess").style.color = "green";
		document.getElementById("phoneNumMess").innerHTML = "\u901a\u8fc7";
		document.getElementById("phoneNum").value = phoneNum;
		flag_phoneNum = true;
	} else {
		flag_phoneNum = false;
		document.getElementById("phoneNumMess").style.color = "red";
		document.getElementById("phoneNumMess").innerHTML = "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u624b\u673a\u53f7\u7801 ";
	}
}
//验证码
function chkvercode() {
	var vercode = document.getElementById("reg_vercode").value;
	ajaxUser.checkvercode(vercode, callBackVercode);
}
function callBackVercode(date) {
	if (date) {
		document.getElementById("vercodeMess").style.color = "green";
		document.getElementById("vercodeMess").innerHTML = "\u901a\u8fc7";
		flag_vercode = true;
	} else {
		flag_vercode = false;
		document.getElementById("vercodeMess").style.color = "red";
		document.getElementById("vercodeMess").innerHTML = "\u9a8c\u8bc1\u7801\u8f93\u5165\u4e0d\u6b63\u786e";
	}
}
function chkform() {
	chkusername();
	chkpassword();
	chkhint();
	chkanswer();
	chkemail();
	chkqq();
	chkphoneNum(); //chkapplyPwd(); chkvercode(); && flag_applyPwd && flag_applyPwd2
	if (flag_username && flag_password && flag_password2 && flag_hint && flag_answer && flag_email && flag_qq && flag_phoneNum && flag_vercode) {
		document.regForm.submit();
	}
}

