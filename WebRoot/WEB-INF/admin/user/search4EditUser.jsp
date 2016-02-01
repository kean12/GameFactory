<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>搜索客户条件</title>
	</head>
	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>搜索客户条件
				</dt>
			</dl>
		</div>
		<div class="blank0"></div>
		<form action="${ctx}/admin/user/user!searchForEditUser.shtml" method="get">
		<table>
			<tr>
				<td>用户ID号:<input type="text" name="user.id"/></td>
				<td>用户名:<input type="text" name="user.username" /></td>
				<td><input type="submit" value="搜 索" /></td>
			</tr>
		</table>
		</form>
		<font color="red" size="2"><s:property value="errorMessage"/></font>
</html>
