<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="mainlogin bbs_orange">
		<div class="title tl">
			<a href="${ctx}">返回</a><span class="ico_7">&nbsp;</span> 用户登录
		</div>
		<form name="loginform" action="${ctx}/user/login!carry.shtml" method="post">
			<ul>
				<li>
					用户名：<input id="j_username" name="user.username" style="width: 132px" tabindex="1" type="text" size="12" /><a href="#this" onclick="javascript:window.location='${ctx}/user/register.shtml'" class="blue_u">注册新用户</a>
				</li>
				<li>
					密 码： <input id= "j_password" name="user.password" style="width: 132px" tabindex="2" type="password" size="14" /><a href="${ctx}/findpsw/findpsw_index.shtml" class="blue_u">忘记密码？</a>
				</li>
				<li>
					验证码：<input id="j_validate_code" name="vercode" onfocus="firstfocus(true,'vercodeimg');" style="width: 132px" tabindex="3" type="text" size="12" />
					<a href="javascript:relImg('vercodeimg')">
						<img id="vercodeimg" alt="" src="${ctx}/authImg" align="middle" border="0" />
					</a>
					<a href="javascript:relImg('vercodeimg');" class="blue_u">换一个</a>
				</li>
			</ul>
			<div id="errorMessage">
				<s:if test="!flag">
					<div class="Attentionbox">
						<div class="Attention">
							${session.errorMessage}
						</div>
					</div>
				</s:if>
			</div>
			<dl>
				<dt>
					<button type="submit" class="loginbutton" onclick="return chkloginform();">登&nbsp;&nbsp;录</button>&nbsp;&nbsp;
					<button type="button" class="loginbutton" onclick="javascript:window.location='${ctx}/user/register.shtml'">立即注册</button>
				</dt>
				<dd>为了保障您的帐户安全，您可以先<a href="http://sd.beike.cn/" class="orange" target="_blank">免费查找病毒木马</a></dd>
			</dl>
		</form>
		<script type="text/javascript">
			function chkloginform(){
				var j_username=document.getElementById("j_username").value.Trim();
				var j_password=document.getElementById("j_password").value.Trim();
				var j_validate_code=document.getElementById("j_validate_code").value.Trim();
				if(j_username.length==0){
					$("errorMessage").innerHTML='<div class="Attentionbox"><div class="Attention">请输入用户名！</div></div>'
					document.getElementById("j_username").select();
					return false;
				}
				if(j_password.length==0){
					$("errorMessage").innerHTML='<div class="Attentionbox"><div class="Attention">请输入密码！</div></div>'
					document.getElementById("j_password").select();
					return false;
				}
				if(j_validate_code.length==0){
					$("errorMessage").innerHTML='<div class="Attentionbox"><div class="Attention">请输入验证码！</div></div>'
					document.getElementById("j_validate_code").select();
					return false;
				}
			}
		</script>
		
	</div>
</body>
</html>
