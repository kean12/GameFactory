<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>游戏买卖网管理员后台登录</title>
		<META content="text/html; charset=utf-8" http-equiv=Content-Type>
		<META name="GENERATOR" content="MSHTML 8.00.7600.16466">
		
		<link rel="stylesheet" type="text/css" href="${ctx}/css/admin/User_Login.css">
		
		<script language=JavaScript>
			function reloadImg() {
				var img = document.getElementById("identifyingCode");
				img.src = "${ctx}/authImg?h="+Math.random();
			}
		
			function checkForm() {
				var username = document.getElementById("username").value;
				var password = document.getElementById("password").value;
				var vercode = document.getElementById("vercode").value;
				if (username == null || username == "") {
					alert("请输入用户名！");
					document.getElementById("username").focus();
					return false;
				}
				if (password == null || password == "") {
					alert("请输入密码！");
					document.getElementById("password").focus();
					return false;
				}
				if (vercode == null || vercode == "") {
					alert("请输入验证码！");
					document.getElementById("vercode").focus();
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body id="userlogin_body">
		<form id="loginForm" method="post" action="${ctx}/admin/login.shtml">
			<div id="user_login">
				<dl>
					<dd id="user_top">
						<ul>
							<li class="user_top_l"></li>
							<li class="user_top_c"></li>
							<li class="user_top_r"></li>
						</ul>
					</dd>
					<dd id="user_main">
						<ul>
							<li class="user_main_l"></li>
							<li class="user_main_c">
								<div class="user_main_box">
									<ul>
										<li class="user_main_text">
											用户名：
										</li>
										<li class="user_main_input">
											<input id="username" name="manage.username" class="TxtUserNameCssClass" maxLength="20" />
										</li>
									</ul>
									<ul>
										<li class="user_main_text">
											密 码：
										</li>
										<li class="user_main_input">
											<input id="password" name="manage.password" class="TxtPasswordCssClass" type="password" />
										</li>
									</ul>
									<ul>
										<li class="user_main_text">
											验证码：
										</li>
										<li class="user_main_input">
											<input id="vercode" name="vercode" class="TxtValidateCodeCssClass" size="4" />
											<a href="javascript:reloadImg()">
												<IMG id="identifyingCode" class="identifyingCode" border="0" align="middle" src="${ctx}/authImg">
											</a>
										</li>
									</ul>
									<ul>
										<li class="error">${error}</li>
									</ul>
								</div>
							</li>
							<li class="user_main_r">
								<input
									style="BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px"
									id=IbtnEnter class=IbtnEnterCssClass
									onclick="return checkForm();" src="${ctx}/images/admin/user_botton.gif" type="image" name="IbtnEnter">
							</li>
						</ul>
					</dd>
					<dd id="user_bottom">
						<ul>
							<li class="user_bottom_l"></li>
							<li class="user_bottom_c">
								<span style="MARGIN-TOP: 40px">Copyright@ 2010 gamemaimai.com copyright</span>
							</li>
							<li class="user_bottom_r"></li>
						</ul>
					</dd>
				</dl>
			</div>
		</form>
	</body>
</html>

