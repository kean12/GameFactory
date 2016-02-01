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
					<strong>当前位置：</strong>查看前台用户
				</dt>
			</dl>
		</div>
		<div class="blank0"></div>
		<form action="${ctx}/admin/user/user.shtml" method="post">
		<table>
			<tr>
				<td>用户ID号:<input type="text" name="user.id"  value="${user.id}"/></td>
				<td>用户名:<input type="text" name="user.username"  value="${user.username}"/></td>
				<td>真实姓名:<input type="text" name="user.realName" value="${user.realName}"/></td>
			</tr>
			<tr>
				<td>QQ:<input type="text" name="user.qq"/></td>
				<td>电子邮箱:<input type="text" name="user.email" value="${user.email}"/>
				<td><input type="submit" value="搜 索" /></td>
			</tr>
		</table>
		</form>
		<hr color="red"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr>
				<th width="10%">用户ID</th>
				<th width="10%">用户登录名</th>
				<th width="10%">真实姓名</th>
				<th width="10%">QQ</th>
				<th width="10%">电话</th>
				<th width="10%">电子邮箱</th>
				<th width="12%">注册时间</th>
				<th width="12%">账户是否锁定</th>
				<th width="10%">账号是否启用</th>
				<th width="10%">&nbsp;</th>
			</tr>
			
			<s:iterator value="page.resultlist">
				<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
					<td><s:property value="id" /></td>
					<td><s:property value="username" /></td>
					<td><s:property value="realName" />&nbsp;</td>
					<td><s:property value="qq" />&nbsp;</td>
					<td><s:property value="phoneNum" />&nbsp;</td>
					<td><s:property value="email" />&nbsp;</td>
					<td><s:property value="regTime" />&nbsp;</td>
					<td><s:if test='lock == "Y"'>锁定</s:if>
					<s:elseif test='lock == "N"'>不锁定</s:elseif>
					<s:else><font color="red" size="2">异常状态</font></s:else>
					</td>
					<td><s:if test="isUse == 0">禁用</s:if><s:elseif test="isUse == 1">启用</s:elseif>
					<s:else><font color="red" size="2">异常状态</font></s:else></td>
					<td>
						<input type="button" value="查看" onclick="location.href='${ctx}/admin/user/user!checkUser.shtml?id=${id}'" />
					</td>
					
				</tr>
			</s:iterator>
		</table>
		<div class="manu">
			<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
			<form name="pageForm" action="${ctx}/admin/user/user.shtml" method="get">
				<input type="hidden" id="goPage" name="goPage" />
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/js/tool.js" charset="gbk"></script>
	</body>
</html>
