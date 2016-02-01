<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>菜单管理</title>
	</head>
	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>查看后台用户
				</dt>
				<dd class="p1"><a href="${ctx}/admin/manage/manage!addManage.shtml">添加管理用户</a></dd>
			</dl>
		</div>
		<div class="blank0"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr>
				<th width="8%">用户名</th>
				<th width="10%">名称	</th>
				<th width="8%">权限	</th>
				<th width="8%">联系QQ</th>
				<th width="10%">添加时间	</th>
				<th width="10%">添加地址	</th>
				<th width="12%">登录时间	</th>
				<th width="10%">登录地址	</th>
				<th width="12%">上一次登录时间</th>
				<th width="5%">是否启用</th>
				<th width="15%">&nbsp;</th>
			</tr>
			
			<s:iterator value="page.resultlist">
				<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
					<td><s:property value="username" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="role.description" /></td>
					<td><s:property value="qq" /></td>
					<td><s:property value="registerTime" /></td>
					<td><s:property value="regip" /></td>
					<td><s:property value="loginTime" />&nbsp;</td>
					<td><s:property value="ip" />&nbsp;</td>
					<td><s:property value="tmpTime" />&nbsp;</td>
					<td><s:if test="isuse == 0">禁用</s:if><s:if test="isuse == 1">启用</s:if></td>
					<td>
						<input type="button" value="编辑" onclick="location.href='${ctx}/admin/manage/manage!addManage.shtml?id=${id}'" />
						<%--
						<input type="button" value="删除" onclick="location.href='${ctx}/admin/manage/manage!removeManage.shtml?id=${id}'" />
						--%>
					</td>
				</tr>
			</s:iterator>
		</table>
		<div class="manu">
			<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
			<form name="pageForm" action="${ctx}/admin/manage/manage.shtml" method="get">
				<input type="hidden" id="goPage" name="goPage" />
			</form>
		</div>
	</body>
</html>
