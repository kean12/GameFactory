<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>角色管理</title>
	</head>
	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>角色管理
				</dt>
				<dd class="p1">
					<a href="${ctx}/admin/role/role!add.shtml">添加角色</a>
				</dd>
			</dl>
		</div>
		<div class="blank0"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr>
				<th width="25%">名称	</th>
				<th width="35%">描述	</th>
				<th width="10%">状态	</th>
				<th width="10%">操作	</th>
			</tr>
			<s:iterator value="roleList">
				<tr onMouseOver="this.style.background = '#d6e5ff';" onMouseOut="this.style.background ='#ffffff';">
					<td><s:property value="name" /></td>
					<td><s:property value="description" /></td>
					<td><s:if test="state==1">启用</s:if><s:if test="state==0">禁用</s:if></td>
					<td>
						<input type="button" value="编辑" onclick="location.href='${ctx}/admin/role/role!add.shtml?id=${id}'" />
						<input type="button" value="删除" onclick="location.href='${ctx}/admin/role/role!remove.shtml?id=${id}'" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
