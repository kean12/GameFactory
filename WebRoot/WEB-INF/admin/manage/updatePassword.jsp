﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
	</head>
	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>用户管理&nbsp;>&nbsp;密码修改
				</dt>
				<dd class="p9"><a href="#" onclick="history.back();">回上一级</a></dd>
			</dl>
		</div>
		<form action="${ctx}/admin/manage/manage!savePassword.shtml" method="post" onsubmit="return check(this)">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
				<tr>
					<th width="10%">原始密码:</th>
					<td width="81%">
						<input type="password" size="50" name="rawPwd"><span class="red" id="rawPwdTip">*</span>
					</td>
				</tr>
				<tr>
					<th width="10%">新密码:</th>
					<td width="81%">
						<input type="password" size="50" name="updatePwd"><span class="red" id="updatePwdTip">*</span>
					</td>
				</tr>
				<tr>
					<th width="10%">新密码确认:</th>
					<td width="81%">
						<input type="password" size="50" name="confirmPwd"><span class="red" id="confirmPwdTip">*</span>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="确认修改"/>&nbsp;&nbsp;<input type="reset" value="重 置"/>
						<span class="red"><s:property value="errorMessage"/></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script type="text/javascript">
		function check(f){
			if(f.rawPwd.value == ''){
				$('rawPwdTip').innerText = '* 原始密码格式错误';
				return false ;
			}else{
				$('rawPwdTip').innerText = '*';
			}
			
			if(f.updatePwd.value == ''){
				$('updatePwdTip').innerText = '* 新密码格式错误';
				return false ;
			}else{
				$('updatePwdTip').innerText = '* ';
			}
			
			if(f.confirmPwd.value == '' || f.confirmPwd.value != f.updatePwd.value){
				$('confirmPwdTip').innerText = '* 新密码格式错误或俩次密码不匹配 ';
				return false ;
			}else{
				$('confirmPwdTip').innerText = '* ';
			}

			if(f.rawPwd.value == f.updatePwd.value ){
				$('rawPwdTip').innerText = '* 无需修改 '; return false ;
			}
			return true ;
		}
	</script>
</html>
